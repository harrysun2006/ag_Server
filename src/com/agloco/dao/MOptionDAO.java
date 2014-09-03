package com.agloco.dao;

import java.util.List;

import com.agloco.model.VBMOption;

/**
 * @author author E-mail:zhaon12@gmail.com
 * @version createDate:Apr 5, 2007 3:22:20 PM
 * content
 */
public interface MOptionDAO {

	public void createMOption(VBMOption mOption);
	public void updateMOption(VBMOption mOption);
	public VBMOption getMOption(Long memberId,String name);
	public List listMOpton(Long memberId);
}
