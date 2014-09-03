/*
 * zhiliang.wang coding  zhaon12@gmail.com fixed
 */
package com.sohospace.dictionary.support.merging;

import java.util.LinkedList;
import java.util.ListIterator;

/**
 * @author zhiliang.wang@yahoo.com.cn
 * @since 1.0
 * 
 */
public class Merger {

	public static void merge(LinkedList<String> a, LinkedList<String> b) {
		ListIterator<String> aIter = (ListIterator<String>) a.iterator();
		ListIterator<String> bIter = (ListIterator<String>) b.iterator();
		while (aIter.hasNext() && bIter.hasNext()) {
			String aWord = aIter.next();
			boolean bGoOn = true;
			while (bGoOn && bIter.hasNext()) {
				String bWord = bIter.next();
				int r = bWord.compareTo(aWord);
				if (r == 0) {
					continue;
				}
				if (r < 0) {
					aIter.previous();
					aIter.add(bWord);
					aIter.next();
				} else {
					bIter.previous();
					bGoOn = false;
				}
			}
		}
		while (bIter.hasNext()) {
			a.add(bIter.next());
		}
	}

	public static void remove(LinkedList<String> a, LinkedList<String> b) {
		ListIterator<String> aIter = (ListIterator<String>) a.iterator();
		ListIterator<String> bIter = (ListIterator<String>) b.iterator();
		while (aIter.hasNext() && bIter.hasNext()) {
			String aWord = aIter.next();
			boolean bGoOn = true;
			while (bGoOn && bIter.hasNext()) {
				String bWord = bIter.next();
				int r = bWord.compareTo(aWord);
				if (r == 0) {
					aIter.remove();
					if (aIter.hasNext()) {
						aWord = aIter.next();
					}
				} else if (r < 0) {
					continue;
				} else {
					bIter.previous();
					bGoOn = false;
				}
			}
		}
	}
}
