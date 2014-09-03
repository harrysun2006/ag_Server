package com.agloco.service.impl;

import com.agloco.dao.util.ConfigDAOUtil;
import com.agloco.model.VBConfig;
import com.agloco.service.ConfigService;

/**
 * @author author E-mail:zhaon12@gmail.com
 * @version 1.0
 * @createDate createDate:2007-8-8 下午01:47:58
 * @content
 */
public class ConfigServiceImpl implements ConfigService {

	public VBConfig getVBConfig(String name, String value) {
		return ConfigDAOUtil.getVBConfig(name, value);
	}
	
	public VBConfig getVBConfig(String name){
		return ConfigDAOUtil.getVBConfig(name);
	}
	
	public void updateVBConfig(String name, String value) {
		ConfigDAOUtil.updateVBConfig(name, value);
	}

}
