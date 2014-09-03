package com.agloco.util;

import com.agloco.ip.Ip2CountryService;
import com.agloco.ip.IpQueryLocalDbImpl;
import com.agloco.ip.IpQueryWeb1Impl;

public class Ip2CountryUtil {

	private final static Ip2CountryService IP2COUNTRY_MAIN_SERVICE = new IpQueryLocalDbImpl();
	private final static Ip2CountryService[] IP2COUNTRY_ALT_SERVICES = new Ip2CountryService[] {
		new IpQueryWeb1Impl(),
	};
	
	public final static String getCountryCode(String ip) {
		String countryCode = null;
		try {
			countryCode = IP2COUNTRY_MAIN_SERVICE.ip2Country(ip);
		} catch(Exception e) {
			countryCode = null;
		}
		if (countryCode == null) {
			for (int i = 0; i < IP2COUNTRY_ALT_SERVICES.length; i++) {
				try {
					countryCode = IP2COUNTRY_ALT_SERVICES[i].ip2Country(ip);
					IP2COUNTRY_MAIN_SERVICE.updateIpCountry(ip, countryCode);
					break;
				} catch(Exception e) {
				}
			}
		}
		return countryCode;
	}
}
