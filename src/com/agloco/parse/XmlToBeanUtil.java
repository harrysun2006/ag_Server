package com.agloco.parse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.exolab.castor.mapping.MappingException;
import org.exolab.castor.xml.MarshalException;
import org.exolab.castor.xml.Unmarshaller;
import org.exolab.castor.xml.ValidationException;

import com.agloco.web.exception.XmlToBeanException;

/**
 * 
 * @author Erick Kong
 * @see XmlToBeanUtil.java
 * @createDate: 2007-4-3
 * @version 1.0
 */

public class XmlToBeanUtil
{
	private final static Log log = LogFactory.getLog(XmlToBeanUtil.class);
	static
	{
		if (CastorUtil.getMapping() == null)
			CastorUtil.init();
	}

	public static Object parseByCastor(String xml, Class c) throws XmlToBeanException
	{
		return parseByCastor(xml, c, false);
	}

	public static Object parseByCastor(String xml) throws XmlToBeanException
	{
		return parseByCastor(xml, null, true);
	}

	public static Object parseByCastor(InputStream in) throws XmlToBeanException
	{
		Object obj = null;
		StringBuffer xml = new StringBuffer();
		try
		{
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String tmp = br.readLine();
			while(tmp!=null)
			{
				xml.append(tmp);
				tmp = br.readLine();
			}
			Reader reader = new StringReader(xml.toString());
			obj = parseByCastor(reader, null, true);
			br.close();
			reader.close();
		}
		catch (Exception e)
		{
			log.error("XmlToBean Error:\n"+	xml.toString(),e);
			// TODO: handle exception
			throw new XmlToBeanException();
		}
		return obj;
	}

	public static Object parseByCastor(InputStream in, Class c)
			throws XmlToBeanException
	{
		Object obj = null;
		StringBuffer xml = new StringBuffer();
		try
		{
//			Reader reader = new InputStreamReader(in, Charset.defaultCharset());
			
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String tmp = br.readLine();
			while(tmp!=null)
			{
				xml.append(tmp);
				tmp = br.readLine();
			}
			Reader reader = new StringReader(xml.toString());
			obj = parseByCastor(reader, c, false);
			br.close();
			reader.close();
		}
		catch (Exception e)
		{
			log.error("XmlToBean Error:\n"+	xml.toString(),e);
			// TODO: handle exception
			throw new XmlToBeanException();
		}
		return obj;
	}

	private static Object parseByCastor(String xml, Class c, boolean flag)
			throws XmlToBeanException
	{
		Object obj = null;
		try
		{
			Reader reader = new StringReader(xml);
			obj = parseByCastor(reader, c, flag);
			reader.close();
		}
		catch (Exception e)
		{
			log.error("XmlToBean Error:\n"+xml,e);
			// TODO: handle exception
			throw new XmlToBeanException();
		}
		return obj;
	}

	private static Object parseByCastor(Reader reader, Class c, boolean flag)
			throws MappingException, MarshalException, ValidationException,
			IOException
	{
		Object obj;
		if (c == null || flag == true)
		{
			obj = CastorUtil.getUnmarshaller().unmarshal(reader);
		}
		else
		{
			obj = Unmarshaller.unmarshal(c, reader);
		}
		reader.close();
		return obj;
	}

}
