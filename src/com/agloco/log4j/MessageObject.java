package com.agloco.log4j;

import java.io.Serializable;

/**
 * 
 * @author Erick Kong
 * @see MessageObject.java
 * @createDate: 2007-4-3
 * @version 1.0
 */

public class MessageObject extends MessageModel implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3057323748353899643L;

	private Long logId;

	private Long memberId;

	private String operate;

	private String serverIp;

	private String ipAddr;

	private String macAddr;

	private String computerName;

	private String loginUser;

	private String domainName;

	private String osVersion;

	private String viewbarId;

	private String timeZone;

	private String otherInfo;

	private String sessionId;

	private String description;

	public String toString()
	{
		StringBuffer buffer = new StringBuffer();

		buffer.append("mebmerId=").append(memberId)
			  .append(" operate=").append(operate)
			  .append(" ipAddr=").append(ipAddr)
			  .append(" sessionId=").append(sessionId);
		return buffer.toString();
	}

	public String getComputerName()
	{
		return computerName;
	}

	public void setComputerName(String computerName)
	{
		this.computerName = computerName;
	}

	public String getDescription()
	{
		return description;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}

	public String getDomainName()
	{
		return domainName;
	}

	public void setDomainName(String domainName)
	{
		this.domainName = domainName;
	}

	public String getIpAddr()
	{
		return ipAddr;
	}

	public void setIpAddr(String ipAddr)
	{
		this.ipAddr = ipAddr;
	}

	public String getLoginUser()
	{
		return loginUser;
	}

	public void setLoginUser(String loginUser)
	{
		this.loginUser = loginUser;
	}

	public String getMacAddr()
	{
		return macAddr;
	}

	public void setMacAddr(String macAddr)
	{
		this.macAddr = macAddr;
	}

	public Long getMemberId()
	{
		return memberId;
	}

	public void setMemberId(Long memberId)
	{
		this.memberId = memberId;
	}

	public String getOperate()
	{
		return operate;
	}

	public void setOperate(String operate)
	{
		this.operate = operate;
	}

	public String getOsVersion()
	{
		return osVersion;
	}

	public void setOsVersion(String osVersion)
	{
		this.osVersion = osVersion;
	}

	public String getOtherInfo()
	{
		return otherInfo;
	}

	public void setOtherInfo(String otherInfo)
	{
		this.otherInfo = otherInfo;
	}

	public String getServerIp()
	{
		return serverIp;
	}

	public void setServerIp(String serverIp)
	{
		this.serverIp = serverIp;
	}

	public String getSessionId()
	{
		return sessionId;
	}

	public void setSessionId(String sessionId)
	{
		this.sessionId = sessionId;
	}

	public String getTimeZone()
	{
		return timeZone;
	}

	public void setTimeZone(String timeZone)
	{
		this.timeZone = timeZone;
	}

	public String getViewbarId()
	{
		return viewbarId;
	}

	public void setViewbarId(String viewbarId)
	{
		this.viewbarId = viewbarId;
	}

	public Long getLogId()
	{
		return logId;
	}

	public void setLogId(Long logId)
	{
		this.logId = logId;
	}

}
