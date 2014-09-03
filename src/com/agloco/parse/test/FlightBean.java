package com.agloco.parse.test;

public class FlightBean
{
	private String m_carrier;

	private int m_number;

	private String m_departure;

	private String m_arrival;

	public FlightBean()
	{
	}

	public void setCarrier(String carrier)
	{
		m_carrier = carrier;
	}

	public String getCarrier()
	{
		return m_carrier;
	}

	public void setNumber(int number)
	{
		m_number = number;
	}

	public int getNumber()
	{
		return m_number;
	}

	public void setDepartureTime(String time)
	{
		m_departure = time;
	}

	public String getDepartureTime()
	{
		return m_departure;
	}

	public void setArrivalTime(String time)
	{
		m_arrival = time;
	}

	public String getArrivalTime()
	{
		return m_arrival;
	}

	public String toString()
	{
		StringBuffer sb = new StringBuffer();
		
		if(this.getArrivalTime()!=null)
			sb.append("ArrivalTime: "+this.getArrivalTime());
		if(this.getCarrier()!=null)
			sb.append("  Carrier: "+this.getCarrier());
		if(this.getDepartureTime()!=null)
			sb.append("  DepartureTime: "+this.getDepartureTime());
		sb.append("  Number: "+this.getNumber());
		
		// TODO Auto-generated method stub
		return sb.toString();
	}
	
	
}