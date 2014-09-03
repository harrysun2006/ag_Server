package com.agloco.model;

import java.util.Calendar;

public class VBAFDetail {

	private Long afDetailId;
	private Long affiliateId;
	private Calendar createDate;
	private String pattern;
	private String agPattern;
	private String category;
	private Double rebate;
	private String status;
	private String info;
	private String description;
	
	public Long getAfDetailId() {
		return afDetailId;
	}
	public void setAfDetailId(Long afDetailId) {
		this.afDetailId = afDetailId;
	}
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
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	public String getPattern() {
		return pattern;
	}
	public void setPattern(String pattern) {
		this.pattern = pattern;
	}
	public Double getRebate() {
		return rebate;
	}
	public void setRebate(Double rebate) {
		this.rebate = rebate;
	}
	public String getAgPattern()
	{
		return this.agPattern;
	}
	public void setAgPattern(String agPattern)
	{
		this.agPattern = agPattern;
	}
	public String getStatus()
	{
		return this.status;
	}
	public void setStatus(String status)
	{
		this.status = status;
	}
	
}
