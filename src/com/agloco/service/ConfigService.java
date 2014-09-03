package com.agloco.service;

import com.agloco.model.VBConfig;

/**
 * @author author E-mail:zhaon12@gmail.com
 * @version 1.0
 * @createDate createDate:2007-8-8 下午01:47:10
 * @content
 */
public interface ConfigService {
	
	public VBConfig getVBConfig(String name, String value);
	public VBConfig getVBConfig(String name);
	public void updateVBConfig(String name, String value);

}
