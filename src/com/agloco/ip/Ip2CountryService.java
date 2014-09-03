package com.agloco.ip;

public interface Ip2CountryService {

	public String ip2Country(String ip) throws IpMappingException;

	public void updateIpCountry(String ip, String country) throws Exception;

}
