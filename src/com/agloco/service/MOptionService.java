package com.agloco.service;

import java.util.List;

import com.agloco.model.VBMOption;

/**
 * @author author E-mail:zhaon12@gmail.com
 * @version createDate:Apr 5, 2007 3:43:42 PM
 * content
 */
public interface MOptionService extends BaseService {
	
	public void createMOption(VBMOption mOption);
	public void updateMOption(VBMOption mOption);
	public VBMOption getMOption(Long memberId,String name);
	public List listMOpton(Long memberId);
	
}
