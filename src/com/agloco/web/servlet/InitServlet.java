package com.agloco.web.servlet;

import java.util.TimeZone;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.exolab.castor.mapping.MappingException;
import org.exolab.castor.xml.Unmarshaller;

import com.agloco.parse.CastorUtil;
import com.agloco.spring.SpringUtil;

public class InitServlet extends HttpServlet
{
	private static Log _log = LogFactory.getLog(InitServlet.class);

	public void init() throws ServletException
	{
		synchronized (InitServlet.class)
		{
			TimeZone UTC = TimeZone.getTimeZone("UTC");
			TimeZone.setDefault(UTC);
			_log.info("TimeZone has been set to " + UTC.getID());

			// Loading castor mapping file
			CastorUtil.init();
			
			// Init others
			SpringUtil.getContext();
		}
	}

	public InitServlet()
	{

	}
}
