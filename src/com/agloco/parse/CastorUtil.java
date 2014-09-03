
package com.agloco.parse;

import java.io.IOException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.exolab.castor.mapping.Mapping;
import org.exolab.castor.mapping.MappingException;
import org.exolab.castor.xml.Unmarshaller;

/**
 * 
 * @author Erick Kong
 * @see CastorUtil.java
 * @createDate: 2007-4-3
 * @version 1.0
 */

public class CastorUtil
{
	private static Mapping map;
	private static Unmarshaller unmarshaller;
	private static Log _log = LogFactory.getLog(CastorUtil.class);

	public static void init()
	{
		map = new Mapping();
		try
		{

			String path = CastorUtil.class.getClassLoader().getResource("").toString();
			map.loadMapping(path + "/META-INF/xml-bean-mapping/xml-bean-mapping.xml");
			unmarshaller = new Unmarshaller(map);
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			_log.error("File \"xml-bean-mapping.xml\" is not exists!", e);
		}
		catch (MappingException e)
		{
			// TODO Auto-generated catch block
			_log.error("XML-BEAN mapping error!", e);
		}
	}
	
	public static Mapping getMapping()
	{
		return map;
	}

	public static Unmarshaller getUnmarshaller()
	{
		return unmarshaller;
	}

}

