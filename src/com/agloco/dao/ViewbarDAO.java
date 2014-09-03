package com.agloco.dao;

import com.agloco.model.VBViewbar;

public interface ViewbarDAO {
	public void createViewbar(VBViewbar viewbar);
	public VBViewbar getLatestViewbar(String viewbarShortId);
	public VBViewbar getViewbar(String viewbarId);
	public VBViewbar getViewbar(String viewbarId,String osVersion);
	public void updateViewbar(VBViewbar viewbar);
}
