package com.agloco.web.service.model;

import java.io.Serializable;

public class AdKeywords implements Serializable {

	private Long adKeywordId;
	private String content;
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
}
