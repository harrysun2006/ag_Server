package com.agloco.web.service.model;

import java.io.Serializable;

public class URLTrackRes implements Serializable 
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int result;
	
	private long nextTime;

	public long getNextTime()
	{
		return nextTime;
	}

	public void setNextTime(long nextTime)
	{
		this.nextTime = nextTime;
	}

	public int getResult()
	{
		return result;
	}

	public void setResult(int result)
	{
		this.result = result;
	}
	
	
}
