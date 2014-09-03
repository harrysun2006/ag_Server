/*
 * zhiliang.wang coding  zhaon12@gmail.com fixed
 */
package com.sohospace.lucene.analysis.xanalyzer;

import com.sohospace.paoding.Knife;
/**
 * @author zhiliang.wang@yahoo.com.cn
 * @since 1.1
 */
public final class XWriterAnalyzer extends XAnalyzer {

	public XWriterAnalyzer() {
		super.setMode(WRITER_MODE);
	}

	public XWriterAnalyzer(Knife knife) {
		super.setMode(WRITER_MODE);
		setKnife(knife);
	}

	public final void setMode(int mode) {
		throw new IllegalStateException("this is a writer mode, cound not change it.");
	}
}
