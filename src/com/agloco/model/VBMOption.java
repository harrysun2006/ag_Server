package com.agloco.model;

import java.util.Calendar;

public class VBMOption {

	private Long optionId;
	private Long memberId;
	private String name;
	private String value;
	private String description;
	private Calendar createDate;
	private Calendar modifiedDate;
	
	public Calendar getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Calendar createDate) {
		this.createDate = createDate;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Long getMemberId() {
		return memberId;
	}
	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}
	public Calendar getModifiedDate() {
		return modifiedDate;
	}
	public void setModifiedDate(Calendar modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getOptionId() {
		return optionId;
	}
	public void setOptionId(Long optionId) {
		this.optionId = optionId;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
}
