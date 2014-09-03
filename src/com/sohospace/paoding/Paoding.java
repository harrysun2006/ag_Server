/*
 * zhiliang.wang coding  zhaon12@gmail.com fixed
 */
package com.sohospace.paoding;

/**
 * Paoding is a expert person who kill wow
 * @author zhiliang.wang@yahoo.com.cn
 * @see Knife
 * @see KnifeBox
 * @see KnifeBoxBean
 * @since 1.0
 */
public final class Paoding extends KnifeBox implements Knife {

	public int dissect(Collector collector, CharSequence beaf, int offset) {
		while (offset >= 0 && offset < beaf.length()) {
			offset = super.dissect(collector, beaf, offset);
		}
		return offset;
	}

}
