package com.agloco.web.service.model;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

public class SearchKeywordReq implements Serializable {

	private Long memberId;
	private Long searchEngineId;
	private boolean needAdvertise;
	private String[] keywords;
	private String browser;
	
	public String[] getKeywords() {
		return keywords;
	}
	public void setKeywords(String[] adKeywords) {
		if(adKeywords == null || adKeywords.length < 1){
			return;
		}
		keywords = new String[adKeywords.length];
		for (int i = 0; i < adKeywords.length; i++) {
			try {
				keywords[i] = URLDecoder.decode(adKeywords[i], "UTF-8");
			} catch (UnsupportedEncodingException e) {
				keywords[i] =  adKeywords[i];
			}
		}
//		this.keywords = adKeywords;
	}
	public Long getMemberId() {
		return memberId;
	}
	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}
	public Long getSearchEngineId() {
		return searchEngineId;
	}
	public void setSearchEngineId(Long searchEngineId) {
		this.searchEngineId = searchEngineId;
	}
	public String getBrowser() {
		return browser;
	}
	public void setBrowser(String browser) {
		this.browser = browser;
	}
	public boolean getNeedAdvertise() {
		return needAdvertise;
	}
	public void setNeedAdvertise(boolean needAdvertise) {
		this.needAdvertise = needAdvertise;
	}
	
}
