package com.agloco.model;

import java.util.Calendar;

public class VBAffiliate {

	private Long affiliateId;
	private String name;
	private Calendar createDate;
	private String category;
	private String description;
	
	public Long getAffiliateId() {
		return affiliateId;
	}
	public void setAffiliateId(Long affiliateId) {
		this.affiliateId = affiliateId;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
