/*
 * zhiliang.wang code
 */
package com.sohospace.paoding.cjk;

import com.sohospace.dictionary.BinaryDictionary;
import com.sohospace.dictionary.Dictionary;
import com.sohospace.dictionary.HashBinaryDictionary;

/**
 * chinese dictionary cache Factory
 * read all chinese word in resources
 * @author zhiliang.wang@yahoo.com.cn
 * @see CJKKnife
 * @since 1.0
 */
public class CJKDictionaryFactory {

	/**
	 * read word from target direct 
	 */
	private WordsLoader wordsLoader;

	/**
	 * words dictionary
	 */
	private Dictionary vocabulary;

	/**
	 * confucianFamily dictionary
	 */
	private Dictionary confucianFamilyNames;

	/**
	 * x
	 */
	private Dictionary xchars;

	/**
	 * hulv dictionary
	 */
	private Dictionary xwords;

	/**
	 * units dictionary
	 */
	private Dictionary units;

	public CJKDictionaryFactory() {
	}

	public CJKDictionaryFactory(WordsLoader wordsLoader) {
		this.wordsLoader = wordsLoader;
	}

	public WordsLoader getWordsLoader() {
		return wordsLoader;
	}

	public void setWordsLoader(WordsLoader wordsLoader) {
		this.wordsLoader = wordsLoader;
	}
	/**
	 * dictionary
	 * @return
	 */
	public Dictionary getVocabulary() {
		if (vocabulary == null) {
			synchronized (this) {
				if (vocabulary == null) {
					// about have 5639 words. so: 0x2fff=x^13>8000>8000*0.75=6000>5639
					vocabulary = new HashBinaryDictionary(wordsLoader
							.loadCJKVocabulary().toArray(new String[0]), 0x2fff, 0.75f);
				}
			}
		}
		return vocabulary;
	}

	/**
	 * ConfucianFamily
	 * @return
	 */
	public Dictionary getConfucianFamilyNames() {
		if (confucianFamilyNames == null) {
			synchronized (this) {
				if (confucianFamilyNames == null) {
					confucianFamilyNames = new BinaryDictionary(wordsLoader
							.loadCJKConfucianFamilyNames().toArray(
									new String[0]));
				}
			}
		}
		return confucianFamilyNames;
	}

	/**
	 * hulv
	 * @return
	 */
	public Dictionary getXchars() {
		if (xchars == null) {
			synchronized (this) {
				if (xchars == null) {
					xchars = new HashBinaryDictionary(wordsLoader.loadCJKXchars()
							.toArray(new String[0]), 256, 0.75f);
				}
			}
		}
		return xchars;
	}

	/**
	 * hulv
	 * @return
	 */
	public Dictionary getXwords() {
		if (xwords == null) {
			synchronized (this) {
				if (xwords == null) {
					xwords = new BinaryDictionary(wordsLoader.loadCJKXwords()
							.toArray(new String[0]));
				}
			}
		}
		return xwords;
	}

	/**
	 * units
	 * @return
	 */
	public Dictionary getUnits() {
		if (units == null) {
			synchronized (this) {
				if (units == null) {
					units = new HashBinaryDictionary(wordsLoader.loadCJKUnit()
							.toArray(new String[0]), 1024, 0.75f);
				}
			}
		}
		return units;
	}

}
