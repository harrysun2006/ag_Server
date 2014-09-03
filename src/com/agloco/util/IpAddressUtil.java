package com.agloco.util;

import java.net.InetAddress;

public class IpAddressUtil {

	private final static byte IPV4 = 4;
	private final static int IPV4_DIG_MASK = 0x00ff;

//	private final static Pattern IPV4_PATTERN = Pattern.compile("(?:\\d+\\.){3}\\d+");
//	private final static Pattern IPV4_DIG_PATTERN = Pattern.compile("(\\d+)");

	public static long ip2Long(String ip) throws Exception {
		byte[] b = ip2Bytes(ip);
		long l = 0l;
		if (b != null && b.length == IPV4) {
			for (int i = 0; i < b.length; i++) {
				l |= (long)(IPV4_DIG_MASK & b[i]) << 8 * (IPV4 - i - 1);
			}
			return l;
		} else {
			throw new Exception("Invalid ip address!");
		}
	}

	public static byte[] ip2Bytes(String ip) throws Exception {
		return InetAddress.getByName(ip).getAddress();
		/*
		Matcher m = IPV4_PATTERN.matcher(ip);
		if (m.matches()) {
			m = IPV4_DIG_PATTERN.matcher(ip);
			byte[] b = new byte[IPV4];
			long l;
			int i = 0;
			while (m.find()) {
				if (i >= IPV4) throw new Exception("Invalid ip address!");
				l = Long.parseLong(m.group(1));
				if (l > IPV4_DIG_MASK) throw new Exception("Invalid ip address!");
				b[i++] = (byte)l;
			}
			return b;
		} else {
			throw new Exception("Invalid ip address!");
		}
		*/
	}

	public static String long2Ip(long l) throws Exception {
		byte[] b = new byte[IPV4];
		for (int i = 0; i < b.length; i++) {
			b[i] = (byte)(l >> 8 * (IPV4 - i - 1));
		}
		return bytes2Ip(b);
	}

	public static String bytes2Ip(byte[] b) throws Exception {
		if (b != null && b.length == IPV4) {
			StringBuffer sb = new StringBuffer(20);
			for (int i = 0; i < b.length; i++) {
				sb.append((int)(IPV4_DIG_MASK & b[i])).append('.');
			}
			sb.deleteCharAt(sb.length() - 1);
			return sb.toString();
		} else {
			throw new Exception("Invalid ip address!");
		}
	}
}
