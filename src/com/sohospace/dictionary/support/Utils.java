/*
 * zhiliang.wang coding  zhaon12@gmail.com fixed
 */
package com.sohospace.dictionary.support;


/**
 * @author zhiliang.wang@yahoo.com.cn
 * @since 1.0
 * 
 */
public class Utils {

	public static int compare(CharSequence one, int begin, int count,
			CharSequence theOther) {
		for (int i = begin, j = 0; i < one.length()
				&& j < Math.min(theOther.length(), count); i++, j++) {
			if (one.charAt(i) > theOther.charAt(j)) {
				return 1;
			} else if (one.charAt(i) < theOther.charAt(j)) {
				return -1;
			}
		}
		return count - theOther.length();
	}

	public static boolean outb(char c) {
		return true;
	}

	/**
	 * @param input
	 * @return
	 */
	public static char toDbcCase(char src) {
		if (src == 12288) {
			src = (char) 32;
		} else if (src > 65280 && src < 65375) {
			src = (char) (src - 65248);
		}
		return src;
	}

}
