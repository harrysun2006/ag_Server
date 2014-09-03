package com.agloco.web.service.model;


public class Affiliate {

	private Long affiliateId;
	private String name;
	private Long createDate;
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
	public Long getCreateDate()
	{
		return this.createDate;
	}
	public void setCreateDate(Long createDate)
	{
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
