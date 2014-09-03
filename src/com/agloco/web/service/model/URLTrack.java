package com.agloco.web.service.model;

import java.io.Serializable;

/**
 * 
 * @author Erick Kong
 * @see URLTrack.java
 * @createDate: 2007-4-3
 * @version 1.0
 */

public class URLTrack implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String domainName;

	private String completeURL;

	private String title;

	private String keywords;

	private String errorDescription;

	private String errorStatus;

	private String browser;

	private String description;

	/**
	 * 
	 */
	public URLTrack()
	{
	}

	public String getBrowser()
	{
		return browser;
	}

	public void setBrowser(String browser)
	{
		this.browser = browser;
	}

	public String getCompleteURL()
	{
		return completeURL;
	}

	public void setCompleteURL(String completeURL)
	{
		this.completeURL = completeURL;
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

	public String getErrorDescription()
	{
		return errorDescription;
	}

	public void setErrorDescription(String errorDescription)
	{
		this.errorDescription = errorDescription;
	}

	public String getErrorStatus()
	{
		return errorStatus;
	}

	public void setErrorStatus(String errorStatus)
	{
		this.errorStatus = errorStatus;
	}

	public String getTitle()
	{
		return title;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	public String getKeywords()
	{
		return keywords;
	}

	public void setKeywords(String keywords)
	{
		this.keywords = keywords;
	}

}
