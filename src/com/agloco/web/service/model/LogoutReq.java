package com.agloco.web.service.model;

import java.io.Serializable;

public class LogoutReq implements Serializable {

	private Long memberId;
	private String viewbarId;
	private String ipAddr;
	private String macAddr;
	private String computerName;
	private String domainName;
	private String osVersion;
	public Long getMemberId() {
		return memberId;
	}

	public void setMemberId(Long memberId) {
		this.memberId = memberId;
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

	public String getViewbarId() {
		return viewbarId;
	}

	public void setViewbarId(String viewbarId) {
		this.viewbarId = viewbarId;
	}
	
	
}
