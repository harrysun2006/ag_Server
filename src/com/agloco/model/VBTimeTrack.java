package com.agloco.model;

import java.util.Calendar;

public class VBTimeTrack {

	private Long trackId;
	private Long memberId;
	private Long submitId;
	private Calendar beginTime;
	private Calendar endTime;
	private Double point;
	private String status;
	
	public Calendar getBeginTime() {
		return beginTime;
	}
	public void setBeginTime(Calendar beginTime) {
		this.beginTime = beginTime;
	}
	public Calendar getEndTime() {
		return endTime;
	}
	public void setEndTime(Calendar endTime) {
		this.endTime = endTime;
	}
	public Long getMemberId() {
		return memberId;
	}
	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}
	public Double getPoint() {
		return point;
	}
	public void setPoint(Double point) {
		this.point = point;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Long getTrackId() {
		return trackId;
	}
	public void setTrackId(Long trackId) {
		this.trackId = trackId;
	}
	public Long getSubmitId()
	{
		return this.submitId;
	}
	public void setSubmitId(Long submitId)
	{
		this.submitId = submitId;
	}
}
