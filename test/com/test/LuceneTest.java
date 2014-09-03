package com.test;

import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.lucene.analysis.Token;

import com.sohospace.lucene.analysis.xanalyzer.XAnalyzer;
import com.sohospace.lucene.analysis.xanalyzer.XFactory;
import com.sohospace.lucene.analysis.xanalyzer.XTokenizer;

/**
 * @author author E-mail:zhaon12@gmail.com
 * @version 1.0
 * @createDate createDate:Apr 12, 2007 10:30:56 AM
 * @content
 */
public class LuceneTest {

	public static void main(String[] arg) throws Exception {
		testOne();
		testTwo();
		testThree();
	}

	private static void testOne() throws Exception {
		XAnalyzer analyzer = XFactory.getQueryAnalyzer();// 搜索时应使用的分析器
		String testString = "中华人民共和国在1949年建立，从此开始了新中国的伟大篇章";
		Reader r = new StringReader(testString);
		XTokenizer ts = (XTokenizer) analyzer.tokenStream("", r);

		Token t;
		while ((t = ts.next()) != null) {
			System.out.println(t);
		}
	}

	private static void testTwo() throws Exception {
		XAnalyzer analyzer = XFactory.getWriterAnalyzer();// 建立索引时应使用的分析器
		String testString = "中华人民共和国在1949年建立，从此开始了新中国的伟大篇章"; // "当经销商品味茶叶时，看出问题了。";
		Reader r = new StringReader(testString);
		XTokenizer ts = (XTokenizer) analyzer.tokenStream("", r);
		List<String> testList = new ArrayList<String>();

		Token t;
		while ((t = ts.next()) != null) {
			String[] tempArray = t.toString().split(",");
			for (int i = 0; i < tempArray.length; i++) {
				if (i == 0) {
					tempArray[i] = tempArray[i].substring(1);
					testList.add(tempArray[i]);
				}
				// System.out.println(tempArray[i]);
			}
		}
		;
		Object[] testArray = testList.toArray();
		System.out.println(Arrays.toString(testList.toArray(new String[]{})));
		for (int i = 0; i < testArray.length; i++) {
			System.out.println(testArray[i].toString());
		}
	}

	private static void testThree() throws Exception {
		long begin = System.currentTimeMillis();
		XAnalyzer analyzer = XFactory.getWriterAnalyzer();// 建立索引时应使用的分析器
		String testString = "一亿世纪中华人民共和国 word wild";// "当经销商品味茶叶时，看出问题了。";
		Reader r = new StringReader(testString);
		XTokenizer ts = (XTokenizer) analyzer.tokenStream("", r);
		List<String> testList = new ArrayList<String>();

		Token t;
		while ((t = ts.next()) != null) {
			String[] tempArray = t.toString().split(",");
			for (int i = 0; i < tempArray.length; i++) {
				if (i == 0) {
					tempArray[i] = tempArray[i].substring(1);
					testList.add(tempArray[i]);
				}
				// System.out.println(tempArray[i]);
			}
		}

		Object[] testArray = testList.toArray();
		for (int i = 0; i < testArray.length; i++) {
			System.out.println(testArray[i]);
		}
		long end = System.currentTimeMillis();
		System.out.println("共用时:" + (end - begin) + "毫秒;");
	}
}
