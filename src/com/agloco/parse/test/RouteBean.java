package com.agloco.parse.test;

import java.util.ArrayList;

public class RouteBean
{
	private String m_from;

	private String m_to;

	private ArrayList m_flights;

	public RouteBean()
	{
		m_flights = new ArrayList();
	}

	public void setFrom(String from)
	{
		m_from = from;
	}

	public String getFrom()
	{
		return m_from;
	}

	public void setTo(String to)
	{
		m_to = to;
	}

	public String getTo()
	{
		return m_to;
	}

	public ArrayList getFlights()
	{
		return m_flights;
	}

	public void addFlight(FlightBean flight)
	{
		m_flights.add(flight);
	}
}