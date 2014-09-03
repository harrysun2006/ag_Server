/*
 * zhiliang.wang coding  zhaon12@gmail.com fixed
 */
package com.sohospace.lucene.analysis.xanalyzer.collector;

import java.util.Iterator;
import java.util.LinkedList;

import org.apache.lucene.analysis.Token;

import com.sohospace.lucene.analysis.xanalyzer.TokenCollector;
/**
 * @author zhiliang.wang@yahoo.com.cn
 * @since 1.1
 */
public class WriterTokenCollector implements TokenCollector {

	/**
	 * store Token (analyzer by knife)
	 */
	private LinkedList<Token> tokens;

	/**
	 * Collector interface<br>
	 * construct Token. and add to tokens
	 */
	public void collect(String word, int begin, int end) {
		if (tokens == null) {
			this.tokens = new LinkedList<Token>();
		}
		this.tokens.add(new Token(word, begin, end));
	}

	public Iterator<Token> iterator() {
		if (this.tokens == null) {
			this.tokens = new LinkedList<Token>();
		}
		Iterator<Token> iter = this.tokens.iterator();
		this.tokens = null;
		return iter;
	}

}
