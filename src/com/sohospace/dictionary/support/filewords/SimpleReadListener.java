/*
 * zhiliang.wang coding  zhaon12@gmail.com fixed
 */
package com.sohospace.dictionary.support.filewords;

import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Map;


/**
 * @author zhiliang.wang@yahoo.com.cn
 * @since 1.0
 */
public class SimpleReadListener implements ReadListener {
	private Map<String, LinkedList<String>> dics = new Hashtable<String, LinkedList<String>>();
	private LinkedList<String> sortedWords;
	
	public boolean onFileBegin(String file) {
		if (!file.endsWith(".dic")){
			return false;
		}
		sortedWords = new LinkedList<String>();
		return true;
	}

	public void onFileEnd(String file) {
		String name = file.substring(0, file.length() - 4);
		dics.put(name, sortedWords);
	}

	public void onWord(String word) {
		word = word.trim().toLowerCase();
		if (word.length() == 0 
				|| word.charAt(0) == '#'
				|| word.charAt(0) == '-') {
			return;
		}
		sortedWords.add(word);
	}
	public Map<String, LinkedList<String>> getResult(){
		return dics;
	}
	
}