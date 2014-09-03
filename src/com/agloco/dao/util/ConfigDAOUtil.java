package com.agloco.dao.util;

import org.springframework.context.ApplicationContext;

import com.agloco.dao.ConfigDAO;
import com.agloco.model.VBConfig;
import com.agloco.spring.SpringUtil;

/**
 * @author author E-mail:zhaon12@gmail.com
 * @version 1.0
 * @createDate createDate:2007-8-8 下午01:26:46
 * @content
 */
public class ConfigDAOUtil {

	private ConfigDAO configDAO;

	public static VBConfig getVBConfig(String name, String value) {
		return getConfigDAO().getVBConfig(name, value);
	}

	public static VBConfig getVBConfig(String name) {
		return getConfigDAO().getVBConfig(name);
	}

	public static void updateVBConfig(String name, String value) {
		getConfigDAO().updateVBConfig(name, value);
	}

	public static ConfigDAO getConfigDAO() {
		ApplicationContext ctx = SpringUtil.getContext();
		ConfigDAOUtil util = (ConfigDAOUtil) ctx.getBean(ConfigDAOUtil.class
				.getName());
		return util.configDAO;
	}

	public void setConfigDAO(ConfigDAO configDAO) {
		this.configDAO = configDAO;
	}



//	public static ConfigDAO getConfigDao() {
//		ApplicationContext ctx = SpringUtil.getContext();
//		ConfigDAOUtil util = (ConfigDAOUtil) ctx.getBean(ConfigDAOUtil.class
//				.getName());
//		return util.configDao;
//	}
//
//	public void setConfigDao(ConfigDAO configDao) {
//		this.configDao = configDao;
//	}

}
