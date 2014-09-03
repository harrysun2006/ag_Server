/*
 * zhiliang.wang coding  zhaon12@gmail.com fixed
 */
package com.sohospace.lucene.analysis.xanalyzer;

import java.io.IOException;
import java.io.Reader;
import java.util.Iterator;

import org.apache.lucene.analysis.Token;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.Tokenizer;

import com.sohospace.lucene.analysis.xanalyzer.collector.QueryTokenCollector;
import com.sohospace.lucene.analysis.xanalyzer.collector.WriterTokenCollector;
import com.sohospace.paoding.Beef;
import com.sohospace.paoding.Collector;
import com.sohospace.paoding.Knife;
import com.sohospace.paoding.Paoding;

/**
 * XTokenizer implements TokenStream, invoked by XAnalyzer
 * 
 * @author zhiliang.wang@yahoo.com.cn
 * @see Beef
 * @see Knife
 * @see Paoding
 * @see Tokenizer
 * @see XAnalyzer
 * @see Collector
 * @see TokenCollector
 * @see QueryTokenCollector
 * @see WriterTokenCollector
 * 
 * @since 1.0
 */
public final class XTokenizer extends TokenStream implements Collector {

	/**
	 * the word source
	 * 
	 * @see #next()
	 */
	private final Reader input;

	/**
	 * 
	 */
	private static final int bufferLength = 128;

	/**
	 * buffer the reader word
	 * 
	 * @see #next()
	 */
	private final char[] buffer = new char[bufferLength];

	/**
	 * {@link buffer}[0 in {@link #input} offset
	 * 
	 * @see #collect(String, int, int)
	 * @see #next()
	 */
	private int offset;

	/**
	 * 
	 */
	private final Beef beef = new Beef(buffer, 0, 0);

	/**
	 */
	private int dissected;

	/**
	 * analyzer buffer word
	 * 
	 * @see #next()
	 */
	private Knife knife;

	/**
	 * 
	 */
	private TokenCollector tokenCollector;

	/**
	 * tokens Iterator, use next() mothed to read the word
	 * 
	 * @see #tokens
	 * @see #next()
	 */
	private Iterator<Token> tokenIteractor;

	/**
	 * 
	 * @param input
	 * @param knife
	 * @param tokenCollector
	 */
	public XTokenizer(Reader input, Knife knife, TokenCollector tokenCollector) {
		this.input = input;
		this.knife = knife;
		this.tokenCollector = tokenCollector;
	}

	public TokenCollector getTokenCollector() {
		return tokenCollector;
	}

	public void setTokenCollector(TokenCollector tokenCollector) {
		this.tokenCollector = tokenCollector;
	}

	public void collect(String word, int offset, int end) {
		tokenCollector.collect(word, this.offset + offset, this.offset + end);
	}

	@Override
	public Token next() throws IOException {
		//
		while (tokenIteractor == null || !tokenIteractor.hasNext()) {
			int read = 0;
			int remainning = -1;// the remaining word in buffer."-1" means that
								// no word in buffer
			if (dissected >= beef.length()) {
				remainning = 0;
			} else if (dissected < 0) {
				remainning = bufferLength + dissected;
			}
			if (remainning != -1) {
				if (remainning > 0) {
					System.arraycopy(buffer, -dissected, buffer, 0, remainning);
				}
				read = input
						.read(buffer, remainning, bufferLength - remainning);
				int charCount = remainning + read;
				if (charCount < 0) {
					// the reader in complete. return
					return null;
				}
				if (charCount < bufferLength) {
					buffer[charCount++] = 0;
				}
				// construct the dictionary. use knife to analyzer
				beef.set(0, charCount);
				offset -= remainning;
				dissected = 0;
			}
			dissected = knife.dissect((Collector) this, beef, dissected);
			offset += read;// !!!
			tokenIteractor = tokenCollector.iterator();
		}
		// return tokensIteractor's next Token
		return tokenIteractor.next();
	}

	@Override
	public void close() throws IOException {
		super.close();
		input.close();
	}

}
