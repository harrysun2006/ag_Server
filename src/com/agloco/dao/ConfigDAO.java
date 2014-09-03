package com.agloco.dao;

import com.agloco.model.VBConfig;


/**
 * @author 		author E-mail:zhaon12@gmail.com
 * @version 		1.0
 * @createDate 	createDate:2007-8-8 下午01:19:52
 * @content		
 */
public interface ConfigDAO {

	public VBConfig getVBConfig(String name,String value);
	public VBConfig getVBConfig(String name);
	public void updateVBConfig(String name,String value);

}
