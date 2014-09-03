package com.agloco.ip;

import com.agloco.dao.util.IpCountryDAOUtil;

/**
 * @author Eric_Zhang
 * 
 */

public class IpQueryLocalDbImpl implements Ip2CountryService {

	public String ip2Country(String ip) throws IpMappingException {
		String countryCode = null;
		try{
			countryCode = IpCountryDAOUtil.getIpCountryCode(ip);
		} catch(Exception e) {
			throw new IpMappingException(e);
		}
		if (countryCode == null) throw new IpMappingException("NOT found country mapping to this ip!");
		else return countryCode;
	}

	public void updateIpCountry(String ip, String country) throws Exception {
		// TODO: update the ip2country entries in db
	}
}