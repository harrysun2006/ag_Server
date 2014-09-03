
package com.viewbar.servicetest;

import com.viewbar.httpclient.HttpClientRequest;

/**
 * 
 * @author Erick Kong
 * @see LoginServiceTest.java
 * @createDate: 2007-4-20
 * @version 1.0
 */

public class LoginServiceTest
{
	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		for(int i=0;i<1000;i++)
		{
			StringBuffer request = new StringBuffer("<?xml version=\"1.0\" encoding=\"UTF-8\"?><loginReq userName=\"BBBB"+format(i+1)+"\" password=\"test\" viewbarId=\"windows.98.1.0.1\" searchEngineHash=\"579570362\" ipAddr=\"119.119.115.16\" macAddr=\"00-17-31-80-D5-A0\" computerName=\"terry-zhao\" domainName=\"amaxgs.local\" osVersion=\"windows.2k\"/>");

			HttpClientRequest loginService = new HttpClientRequest(request,"loginService");
			loginService.start();
//			try
//			{
//				Thread.sleep(50);
//			}
//			catch (InterruptedException e)
//			{
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
		}
		
		System.out.println("All test cases have started!");

	}
	
	private static String format(int i)
	{
		if(i<1)
			return "0000";
		else if(i<10)
			return "000"+i;
		else if(i<100)
			return "00"+i;
		else if(i<1000)
			return "0"+i;
		else
			return i+"";
	}
	
}

