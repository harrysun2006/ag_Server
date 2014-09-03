package com.agloco.web.service.model;

import java.io.Serializable;

public class ViewbarCheckRes  implements Serializable {

	private static final long serialVersionUID = -4467511736893927146L;
	private String latestViewbarId="";
	private boolean forceUpdate;
	private int result;
	
	public int getResult() {
		return result;
	}
	public void setResult(int result) {
		this.result = result;
	}
	public boolean getForceUpdate() {
		return forceUpdate;
	}
	public void setForceUpdate(boolean forceUpdate) {
		this.forceUpdate = forceUpdate;
	}
	public String getLatestViewbarId() {
		return latestViewbarId;
	}
	public void setLatestViewbarId(String latestViewbarId) {
		this.latestViewbarId = latestViewbarId;
	}
}
