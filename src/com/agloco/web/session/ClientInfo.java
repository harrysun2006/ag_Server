package com.agloco.web.session;

import java.io.Serializable;

/**
 * 
 * @author terry_zhao
 * @version 1.0
 */

public class ClientInfo implements Serializable {

	private Long memberId;
	private String viewbarId;
	private String ip;
	

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

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
}
