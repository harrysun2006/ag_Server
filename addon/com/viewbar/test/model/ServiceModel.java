package com.viewbar.test.model;

/**
 * 
 * @author Erick Kong
 * @see ServiceModel.java
 * @createDate: 2007-4-23
 * @version 1.0
 */

public class ServiceModel
{
	private String serviceName = "";

	private StringBuffer requestXml = new StringBuffer();
	
	private long startTime = 0;
	
	private long endTime = 0;

	private long totalTime = 0;

	private String result = "";

	/**
	 * @param serviceName
	 * @param times
	 * @param requestXml
	 */
	public ServiceModel(String serviceName, StringBuffer requestXml)
	{
		this.serviceName = serviceName;
		this.requestXml = requestXml;
	}

	public String getResult()
	{
		return this.result;
	}

	public void setResult(String result)
	{
		this.result = result;
	}

	public StringBuffer getRequestXml()
	{
		return this.requestXml;
	}

	public void setRequestXml(StringBuffer requestXml)
	{
		this.requestXml = requestXml;
	}

	public String getServiceName()
	{
		return this.serviceName;
	}

	public void setServiceName(String serviceName)
	{
		this.serviceName = serviceName;
	}

	public long getTotalTime()
	{
		return this.totalTime;
	}

	public void setTotalTime(long totalTime)
	{
		this.totalTime = totalTime;
	}

	public long getEndTime()
	{
		return this.endTime;
	}

	public void setEndTime(long endTime)
	{
		this.endTime = endTime;
	}

	public long getStartTime()
	{
		return this.startTime;
	}

	public void setStartTime(long startTime)
	{
		this.startTime = startTime;
	}

}
