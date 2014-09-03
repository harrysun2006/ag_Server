package com.viewbar.httpclient;

import java.util.ArrayList;
import java.util.Iterator;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.multipart.ByteArrayPartSource;
import org.apache.commons.httpclient.methods.multipart.FilePart;
import org.apache.commons.httpclient.methods.multipart.MultipartRequestEntity;
import org.apache.commons.httpclient.methods.multipart.Part;
import org.apache.commons.httpclient.methods.multipart.StringPart;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.viewbar.test.model.ServiceModel;
import com.viewbar.test.util.GenerateXmlUtil;

/**
 * 
 * @author Erick Kong
 * @see UserThread.java
 * @createDate: 2007-4-23
 * @version 1.0
 */

public class UserThread extends Thread
{
	private static final String SERVERNAME = "serviceName";

	private static final String FILENAME = "filename";

	private static final String SERVERPATH = "https://119.119.115.40:8443/servlet";

	private int threadIndex;

	private ArrayList<String> params;
	
	private int countPerThread;

	private HttpClient httpClient;

	/**
	 * @param memberCode
	 * @param serviceMap
	 */
	public UserThread(ArrayList<String> params,int threadIndex,int countPerThread)
	{
		this.threadIndex = threadIndex;
		this.countPerThread = countPerThread;
		this.params = params;
	}

	@Override
	public void run()
	{
		// TODO Auto-generated method stub
		for (int i = 0; i < countPerThread; i++)
		{
			int userIndex = threadIndex + i;
			String memberCode = GenerateXmlUtil.format(userIndex);
			try
			{
				ArrayList<ServiceModel> services = getServiceModel(params, memberCode, userIndex);
				for (Iterator iter = services.iterator(); iter.hasNext();)
				{
					ServiceModel sm = (ServiceModel) iter.next();
					if(sm == null)
						continue;
					
					PostMethod method = new PostMethod(SERVERPATH);
					
					FilePart filePart = new FilePart(FILENAME,
							new ByteArrayPartSource(sm.getServiceName()+".xml", sm
									.getRequestXml().toString().getBytes()));

					Part[] parts = {
							new StringPart(SERVERNAME, sm.getServiceName()),
							filePart };
					MultipartRequestEntity multiRequest = new MultipartRequestEntity(
							parts, method.getParams());
					method.setRequestEntity(multiRequest);

					long startTime = System.currentTimeMillis();
					int statusCode = httpClient.executeMethod(method);
					long endTime = System.currentTimeMillis();
					long totalTime = endTime - startTime;
					if (statusCode == HttpStatus.SC_OK)
					{
						sm.setResult("S");
					}
					else
					{
						sm.setResult("F");
					}
					sm.setTotalTime(totalTime);
					sm.setStartTime(startTime);
					sm.setEndTime(endTime);
					System.out.println(method.getResponseBodyAsString());
					
				}

				for (Iterator iter = services.iterator(); iter.hasNext();)
				{
					ServiceModel sm = (ServiceModel) iter.next();
					if(sm == null)
						continue;
					StringBuffer result = new StringBuffer();
					result.append(memberCode);
					result.append("," + sm.getServiceName());
					result.append("," + sm.getResult());
					result.append("," + sm.getStartTime());
					result.append("," + sm.getEndTime());
					result.append("," + sm.getTotalTime());
					_log.info(result.toString());
				}
			}
			catch (Exception e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}
	
	private ArrayList<ServiceModel> getServiceModel(
			ArrayList<String> params, String memberCode, int userIndex )
	{
		ArrayList<ServiceModel> services = new ArrayList<ServiceModel>();
		int id = 0;

		while (true)
		{
			// System.out.print("Please input the service name:");
			String serviceName = params.get(id++);

			if (serviceName.equalsIgnoreCase("end"))
				break;
			else
			{
				if (serviceName.equals("loginService"))
				{
					String searchEngineHash = params.get(id++);
					String favoriteHash = params.get(id++);
					services.add(new ServiceModel(serviceName, GenerateXmlUtil
							.generateLoginXml(memberCode, searchEngineHash,
									favoriteHash)));
				}
				else if (serviceName.equals("timeTrackService"))
				{
					String records = params.get(id++);
					if (records == null || records.length() < 1)
						records = "1";
					services.add(new ServiceModel(serviceName, GenerateXmlUtil
							.generateTimeTrackXml(new Long(userIndex), Integer
									.parseInt(records))));
				}
				else if (serviceName.equals("urlTrackService"))
				{
					String records = params.get(id++);
					if (records == null || records.length() < 1)
						records = "1";
					services.add(new ServiceModel(serviceName, GenerateXmlUtil
							.generateURLTrackXml(new Long(userIndex), Integer
									.parseInt(records))));
				} else if (serviceName.equals("viewbarCheckService")) {
					services.add(new ServiceModel(serviceName,
							GenerateXmlUtil.generateViewbarCheckXML()));
				} else if (serviceName.equals("performanceService")) {
					services.add(new ServiceModel(serviceName,
							new StringBuffer("")));
				} else if (serviceName.equals("autoUpdateService")) {
					services.add(new ServiceModel(serviceName,
							GenerateXmlUtil.generateAutoUpdateXML()));
				} else if (serviceName.equals("logoutService")) {
					services.add(new ServiceModel(serviceName,
							GenerateXmlUtil.generateLogoutXML()));
				} else if (serviceName.equals("searchKeywordsService")) {
					services.add(new ServiceModel(serviceName,
							GenerateXmlUtil.generateSearchKeywordsXML(new Long(userIndex))));
				} else if (serviceName.equals("affiliateService")) {
					services.add(new ServiceModel(serviceName,
							GenerateXmlUtil.generateAffiliateXML()));
				}
				// Others

			}
		}

		return services;

	}


	@Override
	public synchronized void start()
	{
		// TODO Auto-generated method stub
		httpClient = new HttpClient();
//		httpClient.setTimeout(30*60*1000);
		// httpClient.getHostConfiguration().setProxy("119.119.119.9",8080);
		super.start();

	}

	Log _log = LogFactory.getLog(UserThread.class);
}
