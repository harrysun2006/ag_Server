package com.agloco.web.service.model;

import java.io.Serializable;

/**
 * 
 * @author Erick Kong
 * @see TimeTrack.java
 * @createDate: 2007-4-3
 * @version 1.0
 */

public class TimeTrack implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long beginPoint;

	private Long endPoint;
	
	private Double point;

	private String status;

	public Double getPoint()
	{
		return point;
	}

	public void setPoint(Double point)
	{
		this.point = point;
	}

	public String getStatus()
	{
		return status;
	}

	public void setStatus(String status)
	{
		this.status = status;
	}

	public Long getBeginPoint()
	{
		return beginPoint;
	}

	public void setBeginPoint(Long beginPoint)
	{
		this.beginPoint = beginPoint;
	}

	public Long getEndPoint()
	{
		return endPoint;
	}

	public void setEndPoint(Long endPoint)
	{
		this.endPoint = endPoint;
	}

	public TimeTrack()
	{
		
	}
}
