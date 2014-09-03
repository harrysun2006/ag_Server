/*
 * zhiliang.wang coding  zhaon12@gmail.com fixed
 */
package com.sohospace.paoding;

import com.sohospace.paoding.cjk.CJKKnife;

/**
 * Kinfe define how to cut text to words.and invoe the Collentor interface
 * @author zhiliang.wang@yahoo.com.cn
 * @see Collector
 * @see Paoding
 * @see CJKKnife
 * @see CharKnife
 * @see NumberKnife
 * @see LetterKnife
 * @since 1.0
 * 
 */
public interface Knife {
	/**
	 * @param beaf
	 * @param index
	 * @return
	 */
	public boolean assignable(CharSequence beaf, int index);

	/**
	 * cut word
	 * start cut from offset 
	 * @param collector gain cut word invoke the Collector interface
	 * @param beaf input text
	 * @param offset start cut position
	 * @return not minus Number 
	 */
	public int dissect(Collector collector, CharSequence beaf, int offset);
}
