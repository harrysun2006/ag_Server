/*
 * zhiliang code
 */
package com.sohospace.dictionary;

/**
 * Dictionary is a Dictionary. finding if contains a word and other info
 * 
 * @author zhiliang.wang@yahoo.com.cn
 * @see BinaryDictionary
 * @see HashBinaryDictionary
 * @since 1.0
 * 
 */
public interface Dictionary {

	/**
	 * return the word count in Dictionary>=0
	 * @return
	 */
	public int size();

	/**
	 * return the word position
	 * @param index:   0,1,2,...,size-1
	 * @return
	 */
	public String get(int index);

	/**
	 * search the dictionary to find if it contains 
	 * the words (from input[offset] to input[offset+count-1])
	 * the result will put in Hit to return
	 * <p>
	 * @param input: the half of the needed to searching
	 * @param offset: the offset of the word in the search words
	 * @param count the number of search words
	 * @return not null Hits
	 * 
	 * @see Hit
	 */
	public Hit search(CharSequence input, int offset, int count);
}
