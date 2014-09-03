package com.agloco.model;

import java.util.Calendar;


public class VBTimeSubTotal {

	private Long subtotalId;
	private Long memberId;
	private Long second;
	private Integer year;
	private Integer month;
	private Integer day;
	private Calendar surfDate;
	public Integer getDay()
	{
		return day;
	}
	public void setDay(Integer day)
	{
		this.day = day;
	}
	public Long getMemberId()
	{
		return memberId;
	}
	public void setMemberId(Long memberId)
	{
		this.memberId = memberId;
	}
	public Integer getMonth()
	{
		return month;
	}
	public void setMonth(Integer month)
	{
		this.month = month;
	}
	public Long getSecond()
	{
		return second;
	}
	public void setSecond(Long second)
	{
		this.second = second;
	}
	public Long getSubtotalId()
	{
		return subtotalId;
	}
	public void setSubtotalId(Long subtotalId)
	{
		this.subtotalId = subtotalId;
	}
	public Calendar getSurfDate()
	{
		return surfDate;
	}
	public void setSurfDate(Calendar surfDate)
	{
		this.surfDate = surfDate;
	}
	public Integer getYear()
	{
		return year;
	}
	public void setYear(Integer year)
	{
		this.year = year;
	}
	

}
