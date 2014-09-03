/*
 * zhiliang.wang coding  zhaon12@gmail.com fixed
 */
package com.sohospace.dictionary;

/**
 * the search result will be returned by a not null Hits;
 * Hit contains two determination conditons:
 * <li>if the word exsits in dictionary:{@link #isHit()}</li>
 * <li>The dictioary is have the words starting with current search word's header:{@link #isUnclosed()}</li>
 * if the two conditions are both wrong. will return true. else will return false
 * 
 * if isHit() return true. the getWord() will return the search result. getNext() will return next word.
 * if isHit() return false. but the isUnclosed() return true, the getNext() will return the word at the beginning of words start with the current search word
 * 
 * @author zhiliang.wang@yahoo.com.cn
 * @see Dictionary
 * @see BinaryDictionary
 * @see HashBinaryDictionary
 * @since 1.0
 * 
 */
public class Hit {

	public final static int UNCLOSED_INDEX = -1;
	public final static int UNDEFINED_INDEX = -2;
	public final static Hit UNDEFINED = new Hit(UNDEFINED_INDEX, null, null);

	/**
	 * the index of the target word in the dictionary.or "-1" (not in the dictionary)
	 */
	private int index;

	/**
	 * search success word
	 */
	private String word;

	/**
	 * the next word of hit word. or when the isUnclosed() is true. the nighest word
	 */
	private String next;

	/**
	 * 
	 * @param index index of word in dictionary
	 * @param word hit word
	 * @param next next hit word
	 */
	public Hit(int index, String word, String next) {
		this.index = index;
		this.word = word;
		this.next = next;
	}

	/**
	 *  hit word
	 */
	public String getWord() {
		return word;
	}

	/**
	 * index
	 * @return
	 */
	public int getIndex() {
		return index;
	}

	/**
	 * next hit word
	 * @return
	 */
	public String getNext() {
		return next;
	}
	
	/**
	 * if exsits the hit word
	 * @return
	 */
	public boolean isHit() {
		return this.index >= 0;
	}

	/**
	 * if have the word with the search word
	 * @return
	 */
	public boolean isUnclosed() {
		return UNCLOSED_INDEX == this.index
				|| (this.next != null
						&& this.next.length() >= this.word.length() && this.next
						.startsWith(word));
	}

	/**
	 * the dictionary has no search word or the word start with the search word
	 * @return
	 */
	public boolean isUndefined() {
		return UNDEFINED.index == this.index;
	}

	void setIndex(int index) {
		this.index = index;
	}

	void setWord(String key) {
		this.word = key;
	}

	void setNext(String next) {
		this.next = next;
	}

	@Override
	public int hashCode() {
		final int PRIME = 31;
		int result = 1;
		result = PRIME * result + ((word == null) ? 0 : word.hashCode());
		result = PRIME * result + index;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final Hit other = (Hit) obj;
		if (word == null) {
			if (other.word != null)
				return false;
		} else if (!word.equals(other.word))
			return false;
		if (index != other.index)
			return false;
		return true;
	}

	public String toString() {
		if (isUnclosed()) {
			return "[UNCLOSED]";
		} else if (isUndefined()) {
			return "[UNDEFINED]";
		}
		return "[" + index + ']' + word;
	}

}
