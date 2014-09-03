package com.agloco.model;

import java.util.Calendar;

public class VBSearchTrack {

	private Long trackId;
	private Long memberId;
	private Long engineId;
	private String keywords;
	private String browser;
	private Calendar createDate;
	
	public String getBrowser() {
		return browser;
	}
	public void setBrowser(String browser) {
		this.browser = browser;
	}
	public Calendar getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Calendar createDate) {
		this.createDate = createDate;
	}
	public Long getEngineId() {
		return engineId;
	}
	public void setEngineId(Long engineId) {
		this.engineId = engineId;
	}
	public String getKeywords() {
		return keywords;
	}
	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}
	public Long getMemberId() {
		return memberId;
	}
	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}
	public Long getTrackId() {
		return trackId;
	}
	public void setTrackId(Long trackId) {
		this.trackId = trackId;
	}
}
