package com.agloco.web.service.model;

import java.io.Serializable;

public class SearchEngineHash implements Serializable {

	private Long searchEngineId;
	private String hashcode;
	
	public String getHashcode() {
		return hashcode;
	}
	public void setHashcode(String hashcode) {
		this.hashcode = hashcode;
	}
	public Long getSearchEngineId() {
		return searchEngineId;
	}
	public void setSearchEngineId(Long searchEngineId) {
		this.searchEngineId = searchEngineId;
	}
}
