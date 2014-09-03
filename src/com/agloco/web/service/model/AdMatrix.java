package com.agloco.web.service.model;

import java.io.Serializable;

public class AdMatrix implements Serializable {

	private Long adKeywordId;
	private String adId;
	private Double weight;
	
	public String getAdId() {
		return adId;
	}
	public void setAdId(String adId) {
		this.adId = adId;
	}
	public Long getAdKeywordId() {
		return adKeywordId;
	}
	public void setAdKeywordId(Long adKeywordId) {
		this.adKeywordId = adKeywordId;
	}
	public Double getWeight() {
		return weight;
	}
	public void setWeight(Double weight) {
		this.weight = weight;
	}
}
