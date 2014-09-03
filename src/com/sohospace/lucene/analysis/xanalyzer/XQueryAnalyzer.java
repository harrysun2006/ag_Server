/*
 * zhiliang.wang coding  zhaon12@gmail.com fixed
 */
package com.sohospace.lucene.analysis.xanalyzer;

import com.sohospace.paoding.Knife;

/**
 * @author zhiliang.wang@yahoo.com.cn
 * @since 1.1
 */
public final class XQueryAnalyzer extends XAnalyzer {

	public XQueryAnalyzer() {
		super.setMode(QUERY_MODE);
	}

	public XQueryAnalyzer(Knife knife) {
		super.setMode(QUERY_MODE);
		setKnife(knife);
	}
	
	public final void setMode(int mode) {
		throw new IllegalStateException("this is a query mode, cound not change it.");
	}

}
