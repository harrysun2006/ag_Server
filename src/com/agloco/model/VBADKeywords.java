package com.agloco.model;

import java.util.Calendar;

public class VBADKeywords {

	private Long adKeywordId;
	private String content;
	private Long searchCount;
	private Calendar createDate;
	private String description;
	
	public Long getAdKeywordId() {
		return adKeywordId;
	}
	public void setAdKeywordId(Long adKeywordId) {
		this.adKeywordId = adKeywordId;
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
	public Long getSearchCount() {
		return searchCount;
	}
	public void setSearchCount(Long serachCount) {
		this.searchCount = serachCount;
	}
	
}
