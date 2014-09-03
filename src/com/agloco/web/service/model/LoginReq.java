package com.agloco.web.service.model;

import java.io.Serializable;

public class LoginReq implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5147903349010442420L;

	private String userName;
	private String password;
	private String viewbarId;
	private String searchEngineHash;
	private String favoriteHash;
	private String ipAddr;
	private String macAddr;
	private String computerName;
	private String domainName;
	private String osVersion;


	public String getViewbarId() {
		return viewbarId;
	}

	public void setViewbarId(String viewbarVersion) {
		this.viewbarId = viewbarVersion;
	}

	public String getPassword()
	{
		return password;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}

	public String getUserName()
	{
		return userName;
	}

	public void setUserName(String userName)
	{
		this.userName = userName;
	}

	public String getComputerName() {
		return computerName;
	}

	public void setComputerName(String computerName) {
		this.computerName = computerName;
	}

	public String getDomainName() {
		return domainName;
	}

	public void setDomainName(String domainName) {
		this.domainName = domainName;
	}

	public String getIpAddr() {
		return ipAddr;
	}

	public void setIpAddr(String ipAddr) {
		this.ipAddr = ipAddr;
	}

	public String getMacAddr() {
		return macAddr;
	}

	public void setMacAddr(String macAddr) {
		this.macAddr = macAddr;
	}

	public String getOsVersion() {
		return osVersion;
	}

	public void setOsVersion(String osVersion) {
		this.osVersion = osVersion;
	}

	public String getSearchEngineHash() {
		return searchEngineHash;
	}

	public void setSearchEngineHash(String searchEngineHash) {
		this.searchEngineHash = searchEngineHash;
	}

	public String getFavoriteHash() {
		return favoriteHash;
	}

	public void setFavoriteHash(String favoriteHash) {
		this.favoriteHash = favoriteHash;
	}


}
