package com.agloco.util;

import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.lucene.analysis.Token;

import com.sohospace.lucene.analysis.xanalyzer.XAnalyzer;
import com.sohospace.lucene.analysis.xanalyzer.XFactory;
import com.sohospace.lucene.analysis.xanalyzer.XTokenizer;

/**
 * @author author E-mail:zhaon12@gmail.com
 * @version 1.0
 * @createDate createDate:Apr 12, 2007 4:29:19 PM
 * @content
 */
public class KeywordUtil {

	public static XAnalyzer analyzer;
	static {
		analyzer = XFactory.getWriterAnalyzer();
	}

	public static String[] getKeywordsArray(String primiKeyword) {

		try {
			// XAnalyzer analyzer = XFactory.getWriterAnalyzer();
			Reader r = new StringReader(primiKeyword);
			XTokenizer ts = (XTokenizer) analyzer.tokenStream(StringUtil.BLANK,
					r);
			List<String> keywordList = new ArrayList<String>();

			Token t;
			while ((t = ts.next()) != null) {
				String[] tempArray = t.toString().split(StringUtil.COMMA);
				tempArray[0] = tempArray[0].substring(1);
				keywordList.add(tempArray[0]);
			}
			r.close();
			return (String[])keywordList.toArray(new String[keywordList.size()]);
		} catch (Exception e) {
			return null;
		}

	}

}
