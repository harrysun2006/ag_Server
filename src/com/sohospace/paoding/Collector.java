/*
 * zhiliang.wang coding  zhaon12@gmail.com fixed
 */
package com.sohospace.paoding;

/**
 * Collector store the word cut by Knife
 * @author zhiliang.wang@yahoo.com.cn
 * @see Knife
 * @since 1.0
 * 
 */
public interface Collector {

	/**
	 * when Knife gain the first word from input text. this mothed be invoked
	 * @param word gain word
	 * @param offset word offset in text 
	 * @param end this word's end in input text
	 *         
	 */
	public void collect(String word, int offset, int end);
}
