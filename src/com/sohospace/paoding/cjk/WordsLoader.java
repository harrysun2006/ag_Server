/*
 * zhiliang.wang code
 */
package com.sohospace.paoding.cjk;

import java.util.LinkedList;
/**
 * 
 * @author zhiliang.wang@yahoo.com.cn
 *
 */
public interface WordsLoader {

	public LinkedList<String> loadCJKVocabulary();

	public LinkedList<String> loadCJKConfucianFamilyNames();

	public LinkedList<String> loadCJKXwords();

	public LinkedList<String> loadCJKXchars();

	public LinkedList<String> loadCJKUnit();
}
