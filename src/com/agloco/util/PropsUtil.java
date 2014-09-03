package com.agloco.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 
 * @author terry_zhao
 * @date Apr 23, 2007
 */
public class PropsUtil {

	public static boolean containsKey(String key) {
		return getProperties().containsKey(key);
	}

	public static String get(String key) {
		return getProperties().getProperty(key);
	}
	public static String get(String key,String defaultValue) {
		return getProperties().getProperty(key,defaultValue);
	}
	
	public static String[] getArray(String key) {
		String value = get(key);
		if(StringUtils.isBlank(value)){
			return null;
		}
		return value.split(StringUtil.COMMA);
	}
	
	public static int getInt(String key){
		return getInt(key,0);
	}
	
	public static int getInt(String key,int defaultValue){
		if(StringUtils.isBlank(get(key))){
			return defaultValue;	
		}
		return Integer.parseInt(get(key));
	}
	
	public static double getDouble(String key){
		return getDouble(key, 0);
	}
	
	public static double getDouble(String key,int defaultValue){
		if(StringUtils.isBlank(get(key))){
			return defaultValue;	
		}
		return Double.parseDouble(get(key));
	}
	
	public static void set(String key, String value) {
		getProperties().setProperty(key, value);
	}

	private static Properties getProperties(){
		if(props != null){
			return props;
		}
		
		props = new Properties();
		InputStream is = PropsUtil.class.getClassLoader().getResourceAsStream("viewbar.properties");
		try {
			props.load(is);
			System.out.println("properties file:viewbar.properties load success...");
		} catch (IOException e) {
			System.out.println("read file:viewbar.properties error!!!");
			if(log.isErrorEnabled()){
				log.error("read file:viewbar.properties error!", e);
			}
		}
		return props;
		
	}
	
	private static Properties props = null;
	private final static Log log = LogFactory.getLog(PropsUtil.class);

}