package com.agloco.web.service.model;

import java.io.Serializable;

public class DownloadReq implements Serializable{

	private static final long serialVersionUID = -2806923959256521483L;
	private String viewbarId;
	private String operate;
	
	public String getOperate() {
		return operate;
	}
	public void setOperate(String operate) {
		this.operate = operate;
	}
	public String getViewbarId() {
		return viewbarId;
	}
	public void setViewbarId(String viewbarId) {
		this.viewbarId = viewbarId;
	} 
	
}
