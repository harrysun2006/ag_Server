package com.viewbar.httpclient;

import java.io.IOException;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.multipart.ByteArrayPartSource;
import org.apache.commons.httpclient.methods.multipart.FilePart;
import org.apache.commons.httpclient.methods.multipart.MultipartRequestEntity;
import org.apache.commons.httpclient.methods.multipart.Part;
import org.apache.commons.httpclient.methods.multipart.StringPart;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 
 * @author Erick Kong
 * @see HttpClientRequest.java
 * @createDate: 2007-4-20
 * @version 1.0
 */

public class HttpClientRequest extends Thread
{
	private static final String SERVERNAME = "serviceName";

	private static final String FILENAME = "filename";

	private static final String SERVERPATH = "https://119.119.115.102:8443/servlet";

	private StringBuffer inputFile;

	private String serverName = "";

	private HttpClient httpClient;

	private PostMethod method;

	public HttpClientRequest(StringBuffer inputFile, String serverName)
	{
		this.serverName = serverName;
		this.inputFile = inputFile;
	}

	@Override
	public void run()
	{
		// TODO Auto-generated method stub
		FilePart filePart = null;
		filePart = new FilePart(FILENAME, new ByteArrayPartSource(
				"request.xml", inputFile.toString().getBytes()));

		Part[] parts = { new StringPart(SERVERNAME, serverName), filePart };
		MultipartRequestEntity multiRequest = new MultipartRequestEntity(parts,
				method.getParams());
		method.setRequestEntity(multiRequest);

		try
		{
			long startTime = System.currentTimeMillis();
			int statusCode = httpClient.executeMethod(method);
			long endTime = System.currentTimeMillis();
			long totalTime = endTime - startTime;
			if (statusCode == HttpStatus.SC_OK)
			{
				_log.info("S:"+totalTime);
			}
			else
			{
				_log.info("F:"+totalTime);
			}
		}
		catch (HttpException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public synchronized void start()
	{
		// TODO Auto-generated method stub
		httpClient = new HttpClient();
		// httpClient.getHostConfiguration().setProxy("119.119.119.9",8080);
		method = new PostMethod(SERVERPATH);
		super.start();

	}

	public StringBuffer getInputFile()
	{
		return this.inputFile;
	}

	public void setInputFile(StringBuffer inputFile)
	{
		this.inputFile = inputFile;
	}

	public String getServerName()
	{
		return this.serverName;
	}

	public void setServerName(String serverName)
	{
		this.serverName = serverName;
	}

	Log _log = LogFactory.getLog(HttpClientRequest.class);
}
