/*
 * zhiliang.wang coding  zhaon12@gmail.com fixed
 */
package com.sohospace.dictionary.support.filewords;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Map;

/**
 * @author zhiliang.wang@yahoo.com.cn
 * @since 1.0
 * 
 */
public class FileWordsReader {
	
	public static Map<String, LinkedList<String>> readWords(String fileOrDirectory) throws IOException {
		SimpleReadListener l = new SimpleReadListener();
		readWords(fileOrDirectory, l);
		return l.getResult();
	}

	public static void readWords(String fileOrDirectory, ReadListener l) throws IOException {
		File file = new File(fileOrDirectory);
		File[] files = new File[]{file};
		if (file.isDirectory()) {
			files = file.listFiles();
		}
		for (int i = 0; i < files.length; i++) {
			if (!l.onFileBegin(files[i].getName())) {
				continue;
			}
			BufferedReader in = new BufferedReader(new InputStreamReader(
					new FileInputStream(files[i]),"UTF-8"));
			String word;
			while ((word = in.readLine()) != null) {
				l.onWord(word);
			}
			l.onFileEnd(files[i].getName());
			in.close();
		}
	}

}
