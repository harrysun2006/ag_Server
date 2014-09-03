/*
 * zhiliang.wang coding  zhaon12@gmail.com fixed
 */
package com.sohospace.dictionary.support.filewords;


/**
 * @author zhiliang.wang@yahoo.com.cn
 * @since 1.0
 */
public interface ReadListener {
	public boolean onFileBegin(String file);
	public void onFileEnd(String file);
	public void onWord(String word);
}
