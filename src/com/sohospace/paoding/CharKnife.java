/*
 * zhiliang.wang coding  zhaon12@gmail.com fixed
 */
package com.sohospace.paoding;

import java.util.Arrays;
import java.util.HashSet;
/**
 * @author zhiliang.wang@yahoo.com.cn
 * @since 1.0
 * 
 */
public abstract class CharKnife implements Knife {

	private HashSet<String> noiseTable;

	public CharKnife() {
	}

	public CharKnife(String[] noiseWords) {
		setNoiseWords(noiseWords);
	}

	public void setNoiseWords(String[] noiseWords) {
		Arrays.sort(noiseWords);
		noiseTable = new HashSet<String>((int)(noiseWords.length * 1.5));
		for (int i = 0; i < noiseWords.length; i++) {
			noiseTable.add(noiseWords[i]);
		}
		
	}

	public int dissect(Collector collector, CharSequence beaf, int offset) {
		int end = offset + 1;
		for (; end < beaf.length()
				&& isTokenChar(beaf, offset, end); end++) {
		}
		if (end == beaf.length() && offset > 0) {
			return -offset;
		}
		String word = beaf.subSequence(offset, end).toString();
		if (noiseTable != null && noiseTable.contains(word)) {
			word = null;
		}
		if (word != null) {
			collect(collector, beaf, offset, end, word);
		}
		return end;
	}

	protected void collect(Collector collector, CharSequence beaf, int offset, int end,String word) {
		collector.collect(word, offset, end);
	}

	protected abstract boolean isTokenChar(CharSequence beaf, int history, int index);

}
