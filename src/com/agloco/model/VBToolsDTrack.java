package com.agloco.model;

import java.io.Serializable;
import java.util.Calendar;

/**
 * tools download track
 * 
 */
public class VBToolsDTrack implements Serializable {

	private Long id;
	private Long memberId;
	private Long toolId;
	private Calendar createDate;
	
	public Calendar getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Calendar createDate) {
		this.createDate = createDate;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getMemberId() {
		return memberId;
	}
	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}
	public Long getToolId() {
		return toolId;
	}
	public void setToolId(Long toolId) {
		this.toolId = toolId;
	}
	
}
