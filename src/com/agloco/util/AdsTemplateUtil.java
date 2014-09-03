package com.agloco.util;

import org.apache.commons.lang.StringUtils;

import com.agloco.Constants;
import com.agloco.PropertiesKeys;
import com.agloco.model.VBConfig;
import com.agloco.service.util.ConfigServiceUtil;

public class AdsTemplateUtil {

	public final static String getBaseName(String countryCode) {
		String baseName = null;
		if (countryCode != null) {
			VBConfig config = ConfigServiceUtil.getVBConfig(Constants.ADVERTISE_TEMPLATE_BASENAME_COUNTRY + countryCode);
			if(config != null) baseName = config.getValue();
		} 
		if (StringUtils.isBlank(baseName)) {
			baseName = PropsUtil.get(PropertiesKeys.ADVERTISE_TEMPLATE_BASENAME, Constants.DEFAULT_ADVERTISE_TEMPLATE_BASENAME);
		}
		return baseName;
	}

	public final static String getJsName(String countryCode) {
		String jsName = null;
		if (countryCode != null) {
			VBConfig config = ConfigServiceUtil.getVBConfig(Constants.ADVERTISE_TEMPLATE_JSNAME_COUNTRY + countryCode);
			if(config != null) jsName = config.getValue();
		} 
		if (StringUtils.isBlank(jsName)) {
			jsName = PropsUtil.get(PropertiesKeys.ADVERTISE_TEMPLATE_JSNAME, Constants.DEFAULT_ADVERTISE_TEMPLATE_JSNAME);
		}
		return jsName;
	}

}
