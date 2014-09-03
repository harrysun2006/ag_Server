package com.agloco.parse;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.exolab.castor.xml.Marshaller;

import com.agloco.web.exception.BeanToXmlException;

/**
 * 
 * @author Erick Kong
 * @see BeanToXmlUtil.java
 * @createDate: 2007-4-3
 * @version 1.0
 */

public class BeanToXmlUtil
{
	private final static Log log = LogFactory.getLog(BeanToXmlUtil.class);
	static{
		if(CastorUtil.getMapping() == null)
			CastorUtil.init();
	}
	
	public static String parseByCastor(Object obj) throws BeanToXmlException
	{
		return parseByCastor(obj, true);
	}

	public static String parseByCastor(Object obj, boolean flag)
			throws BeanToXmlException
	{
		Writer writer = new StringWriter();
		String result = "";
		
		try
		{
			if (flag == true)
			{
				Marshaller marshaller = new Marshaller(writer);
				marshaller.setMapping(CastorUtil.getMapping());
				marshaller.marshal(obj);
				result = writer.toString();
			}
			else
			{
				Marshaller.marshal(obj, writer);
				result = writer.toString();
			}
		}
		catch (Exception e)
		{
			log.error("",e);
			// TODO: handle exception
			throw new BeanToXmlException();
		}
		finally
		{
			try
			{
				writer.close();
			}
			catch (IOException e)
			{
				// TODO Auto-generated catch block
			}
		}
		return result;
	}

}
