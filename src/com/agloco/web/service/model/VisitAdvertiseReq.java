package com.agloco.web.service.model;

import java.io.Serializable;

public class VisitAdvertiseReq implements Serializable {

	private Long memberId;
	private Long[] advertiseId;
	private String viewbarId; 

	public Long getMemberId() {
		return memberId;
	}
	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}
	public String getViewbarId() {
		return viewbarId;
	}
	public void setViewbarId(String viewbarId) {
		this.viewbarId = viewbarId;
	}
	public Long[] getAdvertiseId() {
		return advertiseId;
	}
	public void setAdvertiseId(Long[] advertiseId) {
		this.advertiseId = advertiseId;
	}
}
