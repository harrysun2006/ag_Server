/*
 * zhiliang.wang coding  zhaon12@gmail.com fixed
 */
package com.sohospace.lucene.analysis.xanalyzer;

import java.io.Reader;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;

import com.sohospace.lucene.analysis.xanalyzer.collector.QueryTokenCollector;
import com.sohospace.lucene.analysis.xanalyzer.collector.WriterTokenCollector;
import com.sohospace.paoding.Knife;
import com.sohospace.paoding.Paoding;
import com.sohospace.paoding.cjk.CJKKnife;

/**
 * XAnalyzer is a analyzer on Lucene. is paoding framwork's adapter XAnalyzer is
 * thread safe.<br>
 * XAnalyzer can reuse when many time invoke; can set the special instance over
 * knife or construct
 * 
 * @author zhiliang.wang@yahoo.com.cn
 * @see XWriterAnalyzer
 * @see XQueryAnalyzer
 * @see XTokenizer
 * @see Knife
 * @see Paoding
 * @see CJKKnife
 * @see TokenCollector
 * @since 1.0
 * 
 */
public class XAnalyzer extends Analyzer {

	/**
	 * this mode used when craete index. can create index on every word
	 */
	public static final int WRITER_MODE = 1;

	/**
	 * this mode used when search word, can optimization the result
	 */
	public static final int QUERY_MODE = 2;

	/**
	 * analyzer chinese word
	 * 
	 * @see XTokenizer#next()
	 * 
	 */
	private Knife knife;

	/**
	 * @see #WRITER_MODE
	 * @see #QUERY_MODE
	 */
	private int mode = WRITER_MODE;

	public XAnalyzer() {
	}

	public XAnalyzer(Knife knife) {
		this.knife = knife;
	}

	// -------------------------------------------------

	public Knife getKnife() {
		return knife;
	}

	public void setKnife(Knife knife) {
		this.knife = knife;
	}

	public int getMode() {
		return mode;
	}

	/**
	 * set analyzer mode. can use WRITER_MODE or QUERY_MODE. the default mode is
	 * WRITER_MODE WRITER_MODE is used in create index QUERY_MODE use in search
	 * word
	 * 
	 * @param mode
	 */
	public void setMode(int mode) {
		this.mode = mode;
	}

	@Override
	public TokenStream tokenStream(String fieldName, Reader reader) {
		// use DEFAULT_KNIFE
		if (knife == null) {
			throw new NullPointerException("knife should be set before token");
		}
		// XTokenizer implements TokenStream, use knife analyzer reader word
		return new XTokenizer(reader, knife, createTokenCollector());
	}

	protected TokenCollector createTokenCollector() {
		switch (mode) {
		case WRITER_MODE:
			return new WriterTokenCollector();
		case QUERY_MODE:
			return new QueryTokenCollector();
		default:
			throw new IllegalArgumentException("wrong mode");
		}
	}

}
