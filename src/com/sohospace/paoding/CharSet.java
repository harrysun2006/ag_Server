/*
 * zhiliang.wang coding  zhaon12@gmail.com fixed
 */
package com.sohospace.paoding;

/** 
 * @author zhiliang.wang@yahoo.com.cn
 * @since 1.0
 * 
 */
public class CharSet {
	
	public static boolean isArabianNumber(char ch) {
		return ch >= '0' && ch <= '9';
	}

	public static boolean isLetter(char ch) {
		return ch >= 'a' && ch <= 'z' || ch >= 'A' && ch <= 'Z';
	}

	public static boolean isCjkUnifiedIdeographs(char ch) {
		return ch >= 0x4E00 && ch < 0xA000;
	}

}
