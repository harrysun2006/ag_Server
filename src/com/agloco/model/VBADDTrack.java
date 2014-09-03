package com.agloco.model;

import java.util.Calendar;

public class VBADDTrack {

	private Long trackId;
	private Long memberId;
	private String adId;
	private Calendar createDate;

	public String getAdId() {
		return adId;
	}
	public void setAdId(String adId) {
		this.adId = adId;
	}
	public Calendar getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Calendar createDate) {
		this.createDate = createDate;
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
