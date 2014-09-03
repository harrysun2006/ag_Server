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
public class QueryTokenCollector implements TokenCollector {

	/**
	 * store the Token (analyzer by Knife)
	 */
	private LinkedList<Token> tokens = new LinkedList<Token>();

	private Token candidate;

	private Token last;

	public Iterator<Token> iterator() {
		if (candidate != null) {
			this.tokens.add(candidate);
			candidate = null;
		}
		Iterator<Token> iter = this.tokens.iterator();
		this.tokens = new LinkedList<Token>();
		return iter;
	}

	public void collect(String word, int offset, int end) {
		Token c = candidate != null ? candidate : last;
		if (c == null) {
			candidate = new Token(word, offset, end);
		} else if (offset == c.startOffset()) {
			if (end > c.endOffset()) {
				candidate = new Token(word, offset, end);
			}
		} else if (offset > c.startOffset()) {
			if (candidate != null) {
				select(candidate);
			}
			if (end > c.endOffset()) {
				candidate = new Token(word, offset, end);
			} else {
				candidate = null;
			}
		} else if (end >= c.endOffset()) {
			if (last != null && last.startOffset() >= offset
					&& last.endOffset() <= end) {
				for (Iterator iter = tokens.iterator(); iter.hasNext();) {
					last = (Token) iter.next();
					if (last.startOffset() >= offset && last.endOffset() <= end) {
						iter.remove();
					}
				}
			}
			last = null;
			candidate = new Token(word, offset, end);
		}
	}

	protected void select(Token t) {
		this.tokens.add(t);
		this.last = t;
	}

}
