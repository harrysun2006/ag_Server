/*
 * zhiliang code
 */
package com.sohospace.dictionary;

import java.util.HashMap;
import java.util.Map;

/**
 * HashBinaryDictionary
 * <p>
 * this dictionary used when the search words is very big and the header of the
 * search words is same. the search speed is faster than BinaryDictionary in
 * this case
 * <p>
 * the input of HashBinaryDictionary is an arraged words.the words having same
 * header will be a dictionary (BinaryDictionary).
 * 
 * @author zhiliang.wang@yahoo.com.cn
 * @see BinaryDictionary
 * @since 1.0
 */
public class HashBinaryDictionary implements Dictionary {

	/**
	 * all dictionary words, can easily use link and get(int) method
	 */
	private String[] ascWords;

	/**
	 * the first char to dictionary
	 */
	private Map<Integer, SubDictionaryWrap> subs;

	/**
	 * 
	 */
	private final int hashIndex;

	private final int start;

	private final int end;

	private final int count;

	// -------------------------------------------------

	/**
	 * 
	 * @param ascWords
	 * @param initialCapacity
	 * @param loadFactor
	 */
	public HashBinaryDictionary(String[] ascWords, int initialCapacity,
			float loadFactor) {
		this(ascWords, 0, 0, ascWords.length, initialCapacity, loadFactor);
	}

	public HashBinaryDictionary(String[] ascWords, int hashIndex, int start,
			int end, int initialCapacity, float loadFactor) {
		this.ascWords = ascWords;
//		if (end - start > 20000) {
//			System.out.println("ascWords.length=" + ascWords.length);
//		}
		this.start = start;
		this.end = end;
		this.count = end - start;
		this.hashIndex = hashIndex;
		subs = new HashMap<Integer, SubDictionaryWrap>(initialCapacity,
				loadFactor);
		createSubDictionaries();
	}

	/**
	 * create the sub dictionary. can be invoked by construct method
	 */
	protected void createSubDictionaries() {
		// find the begin and end of the words having same harder to init
		// dictionary
		int beginIndex = this.start;
		int endIndex = this.start + 1;

		char beginHashChar = getChar(ascWords[start], hashIndex);
		char endHashChar;
		for (; endIndex < this.end; endIndex++) {
			endHashChar = getChar(ascWords[endIndex], hashIndex);
			if (endHashChar != beginHashChar) {
				addSubDictionary(beginHashChar, beginIndex, endIndex);
				beginIndex = endIndex;
				beginHashChar = endHashChar;
			}
		}
		addSubDictionary(beginHashChar, beginIndex, this.end);
	}

	protected char getChar(String s, int index) {
		if (index >= s.length()) {
			return (char) 0;
		}
		return s.charAt(index);
	}

	/**
	 * create sub dictionary with words in beginIndex and endIdex
	 * 
	 * @param hashChar
	 * @param beginIndex
	 * @param endIndex
	 */
	protected void addSubDictionary(char hashChar, int beginIndex, int endIndex) {
		SubDictionaryWrap subDic = new SubDictionaryWrap(hashChar,
				createSubDictionary(ascWords, beginIndex, endIndex), beginIndex);
		Integer key = keyOf(hashChar);
		if (subs.containsKey(key)) {
//			System.out.println("some word has the wrong arrange,please confirm the word arrange>>>>>>>"
//					+ hashChar);
		}
		subs.put(key, subDic);
	}

	protected Dictionary createSubDictionary(String[] ascWords, int beginIndex,
			int endIndex) {
		int count = endIndex - beginIndex;
		if (count < 16) {
			return new BinaryDictionary(ascWords, beginIndex, endIndex);
		} else {
			return new HashBinaryDictionary(ascWords, hashIndex + 1,
					beginIndex, endIndex, getCapacity(count), 0.75f);
		}
	}

	protected static final int[] capacityCandiate = { 16, 32, 64, 128, 256,
			512, 1024, 2048, 4096, 10192 };

	protected int getCapacity(int count) {
		int capacity = -1;
		count <<= 2;
		count /= 3;
		count += 1;
		for (int i = 0; i < capacityCandiate.length; i++) {
			if (count <= capacityCandiate[i]) {
				capacity = capacityCandiate[i];
				break;
			}
		}
		if (capacity == -1) {
			capacity = capacityCandiate[capacityCandiate.length - 1];
		}
		return capacity;
	}

	public String get(int index) {
		return ascWords[start + index];
	}

	public Hit search(CharSequence input, int begin, int count) {
		SubDictionaryWrap subDic = subs.get(keyOf(input.charAt(hashIndex
				+ begin)));
		if (subDic == null) {
			return Hit.UNDEFINED;
		}
		Dictionary dic = subDic.dic;
		// count==hashIndex + 1
		if (count == hashIndex + 1) {
			String header = dic.get(0);
			if (header.length() == hashIndex + 1) {
				if (subDic.wordIndexOffset + 1 < this.ascWords.length) {
					return new Hit(subDic.wordIndexOffset, header,
							this.ascWords[subDic.wordIndexOffset + 1]);
				} else {
					return new Hit(subDic.wordIndexOffset, header, null);
				}
			} else {
				return new Hit(Hit.UNCLOSED_INDEX, null, header);
			}
		}
		// count > hashIndex + 1
		Hit word = dic.search(input, begin, count);
		if (word.isHit()) {
			int index = subDic.wordIndexOffset + word.getIndex();
			word.setIndex(index);
			if (word.getNext() == null && index < size()) {
				word.setNext(get(index + 1));
			}
		}
		return word;
	}

	public int size() {
		return count;
	}

	/**
	 * 
	 * @param theChar
	 * @return
	 */
	protected int keyOf(char theChar) {
		// return theChar - 0x4E00;// '一'==0x4E00
		return theChar;
	}

	/**
	 *  
	 */
	static class SubDictionaryWrap {
		/**
		 *	subDictionary header char
		 */
		char hashChar;

		/**
		 * subDictionary
		 */
		Dictionary dic;

		/**
		 * 
		 */
		int wordIndexOffset;

		public SubDictionaryWrap(char hashChar, Dictionary dic,
				int wordIndexOffset) {
			this.hashChar = hashChar;
			this.dic = dic;
			this.wordIndexOffset = wordIndexOffset;
		}
	}

}
