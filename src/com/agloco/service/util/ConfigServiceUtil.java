package com.agloco.service.util;

import org.springframework.context.ApplicationContext;

import com.agloco.model.VBConfig;
import com.agloco.service.ConfigService;
import com.agloco.spring.SpringUtil;

/**
 * @author author E-mail:zhaon12@gmail.com
 * @version 1.0
 * @createDate createDate:2007-8-8 下午01:49:49
 * @content
 */
public class ConfigServiceUtil {

	private ConfigService configService;

	public static VBConfig getVBConfig(String name, String value) {
		return getConfigService().getVBConfig(name, value);
	}

	public static VBConfig getVBConfig(String name) {
		return getConfigService().getVBConfig(name);
	}

	public static void updateVBConfig(String name, String value) {
		getConfigService().updateVBConfig(name, value);
	}

	public static ConfigService getConfigService() {
		ApplicationContext ctx = SpringUtil.getContext();
		ConfigServiceUtil util = (ConfigServiceUtil) ctx
				.getBean(ConfigServiceUtil.class.getName());
		return util.configService;
	}

	public void setConfigService(ConfigService configService) {
		this.configService = configService;
	}

	// public static VBConfigService getConfigService() {
	// ApplicationContext ctx = SpringUtil.getContext();
	// VBConfigServiceUtil util = (VBConfigServiceUtil) ctx
	// .getBean(VBConfigServiceUtil.class.getName());
	// return util.configService;
	// }
	//
	// public void setConfigService(VBConfigService configService) {
	// this.configService = configService;
	// }
}
