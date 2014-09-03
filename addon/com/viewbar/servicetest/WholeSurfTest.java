package com.viewbar.servicetest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import com.agloco.parse.CastorUtil;
import com.viewbar.httpclient.UserThread;

/**
 * 
 * @author Erick Kong
 * @see WholeSurfTest.java
 * @createDate: 2007-4-23
 * @version 1.0
 */

public class WholeSurfTest
{
	@SuppressWarnings("unchecked")
	public static void main(String[] args)
	{
		CastorUtil.init();

		// TODO Auto-generated method stub
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader in = new BufferedReader(isr);

		System.out.print("Please input the thread count:");
		int threadCount = readNumber(in);
		System.out.print("Please input the count per thread:");
		int countPerThread = readNumber(in);

		ArrayList<String> params = new ArrayList<String>();
		params = readParams();

		for (int i = 0; i < threadCount; i++)
		{
			int threadIndex = i*countPerThread+1;
			UserThread surf = new UserThread(params, threadIndex, countPerThread);
			surf.start();
		}

		System.out.println("All test cases have started!");

	}
	
	private static int readNumber(BufferedReader in)
	{
		int count = 1;
		while (true)
		{
			try
			{
				String tmp = in.readLine();
				if (tmp == null || tmp.length() < 1)
					break;
				count = Integer.parseInt(tmp);
				break;
			}
			catch (IOException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			catch (NumberFormatException e)
			{
				// TODO: handle exception
				System.err.print("Please input the count as a number:");
			}
		}

		return count;
	}

	private static ArrayList<String> readParams()
	{
		ArrayList<String> params = new ArrayList<String>();
		params.add("loginService");
		params.add("");
		params.add("");
		params.add("timeTrackService");
		params.add("5");
		params.add("urlTrackService");
		params.add("7");
		params.add("performanceService");
		params.add("viewbarCheckService");
		params.add("autoUpdateService");
		params.add("searchKeywordsService");
		params.add("affiliateService");
		params.add("logoutService");
		params.add("end");
		return params;
	}

	private static ArrayList<String> readParams(BufferedReader in)
	{
		ArrayList<String> params = new ArrayList<String>();

		System.err.println("Input \"End\" to start the thread!");
		
		boolean isLogin = false;

		while (true)
		{
			try
			{
				System.out.print("Please input the service name:");
				String serviceName = in.readLine();
				params.add(serviceName);

				if (serviceName.equalsIgnoreCase("end"))
				{
					if(isLogin)
					{
						params.remove(serviceName);
						params.add("logoutService");
						params.add("end");
					}
					break;
				}
				else
				{
					if (serviceName.equals("loginService"))
					{
						isLogin = true;
						System.out
								.print("Please input the search engine hash code:");
						String searchEngineHash = in.readLine();
						params.add(searchEngineHash);
						System.out
								.print("Please input the favorite hash code:");
						String favoriteHash = in.readLine();
						params.add(favoriteHash);
					}
					else if (serviceName.equals("timeTrackService"))
					{
						System.out
								.print("Please input the time track records:");
						String records = in.readLine();
						if (records == null || records.length() < 1)
							records = "1";
						params.add(records);
					}
					else if (serviceName.equals("urlTrackService"))
					{
						System.out.print("Please input the url track records:");
						String records = in.readLine();
						if (records == null || records.length() < 1)
							records = "1";
						params.add(records);
					} else if (serviceName.equals("viewbarCheckService")) {

					} else if (serviceName.equals("performanceService")) {

					} else if (serviceName.equals("autoUpdateService")) {

					} else if (serviceName.equals("logoutService")) {
						if(!isLogin)
						{
							System.err.println("Must login first");
							params.remove(serviceName);
						}
						isLogin = false;

					} else {
						params.remove(serviceName);
						System.err.println("Please input a right sercice name");
					}
					// Others

				}
			}
			catch (IOException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			catch (NumberFormatException e)
			{
				// TODO: handle exception
				System.err.print("Please input the thread count as a number:");
			}
		}
		return params;

	}

}
