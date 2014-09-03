package com.agloco.service.impl;

import java.util.List;

import com.agloco.dao.util.MOptionDAOUtil;
import com.agloco.model.VBMOption;
import com.agloco.service.MOptionService;

/**
 * @author author E-mail:zhaon12@gmail.com
 * @version createDate:Apr 5, 2007 3:45:12 PM content
 */
public class MOptionServiceImpl extends BaseServiceImpl implements
		MOptionService {

	public VBMOption getMOption(Long memberId, String name) {
		return MOptionDAOUtil.getMOption(memberId, name);
	}

	public void createMOption(VBMOption mOption) {
		MOptionDAOUtil.createMOption(mOption);
	}

	public List listMOpton(Long memberId) {
		return MOptionDAOUtil.listMOpton(memberId);
	}

	public void updateMOption(VBMOption mOption) {
		MOptionDAOUtil.updateMOption(mOption);
	}

}
