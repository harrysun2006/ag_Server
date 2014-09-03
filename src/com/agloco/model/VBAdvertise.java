package com.agloco.model;

import java.util.Calendar;

public class VBAdvertise {
	
	private String adId;
	private String content;
	private String banner;
	private String category;
	private String type;
	private String pattern;
	private String sponsor;
	private Long downloadCount;
	private Long visitCount;
	private String description;
	private Calendar createDate;
	
	public String getBanner() {
		return banner;
	}
	public void setBanner(String banner) {
		this.banner = banner;
	}
	public String getAdId() {
		return adId;
	}
	public void setAdId(String adId) {
		this.adId = adId;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
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
	public Long getDownloadCount() {
		return downloadCount;
	}
	public void setDownloadCount(Long downloadCount) {
		this.downloadCount = downloadCount;
	}
	public String getPattern() {
		return pattern;
	}
	public void setPattern(String pattern) {
		this.pattern = pattern;
	}
	public String getSponsor() {
		return sponsor;
	}
	public void setSponsor(String sponsor) {
		this.sponsor = sponsor;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Long getVisitCount() {
		return visitCount;
	}
	public void setVisitCount(Long visitCount) {
		this.visitCount = visitCount;
	}
	
}
