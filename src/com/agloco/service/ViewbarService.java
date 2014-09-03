package com.agloco.service;

import com.agloco.model.VBViewbar;

public interface ViewbarService extends BaseService{
	
	public void createViewbar(VBViewbar viewbar);
	public void updateViewbar(VBViewbar viewbar);
	public VBViewbar getLatestViewbar(String viewbarShortId);
	public VBViewbar getViewbar(String viewbarId);
	public VBViewbar getViewbar(String viewbarId,String osVersion);
	
}
