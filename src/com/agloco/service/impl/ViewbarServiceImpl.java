package com.agloco.service.impl;

import com.agloco.dao.util.ViewbarDAOUtil;
import com.agloco.model.VBViewbar;

public class ViewbarServiceImpl extends BaseServiceImpl implements
		com.agloco.service.ViewbarService {

	public void createViewbar(VBViewbar viewbar) {
		ViewbarDAOUtil.createViewbar(viewbar);
	}

	public void updateViewbar(VBViewbar viewbar) {
		ViewbarDAOUtil.updateViewbar(viewbar);
	}

	public VBViewbar getLatestViewbar(String viewbarShortId) {
		return ViewbarDAOUtil.getNewestViewbar(viewbarShortId);
	}

	public VBViewbar getViewbar(String viewbarId) {
		return ViewbarDAOUtil.getViewbar(viewbarId);
	}

	public VBViewbar getViewbar(String viewbarId, String osVersion) {
		return ViewbarDAOUtil.getViewbar(viewbarId, osVersion);
	}

}
