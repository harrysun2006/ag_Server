package com.agloco.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.agloco.PropertiesKeys;
import com.agloco.util.PropsUtil;

/**
 * 
 * @author terry_zhao
 *
 */
public class SpringUtil {

	static String[] configs = PropsUtil.getArray(PropertiesKeys.SPRING_CONFIG_FILES);

	private static ApplicationContext _ctx = new ClassPathXmlApplicationContext(configs);
	
	public static ApplicationContext getContext() {
		return _ctx;
	}

	
}
