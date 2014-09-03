package com.agloco.web.service.model;

import java.io.Serializable;

public class Advertise implements Serializable {

	private String adId;
	private String adCategory;
	private String adType;
	private String adBanner;
	private String adContent;
	private String adDescription;
	
	public String getAdDescription() {
		return adDescription;
	}
	public void setAdDescription(String adDescription) {
		this.adDescription = adDescription;
	}
	public String getAdCategory() {
		return adCategory;
	}
	public void setAdCategory(String adCatagory) {
		this.adCategory = adCatagory;
	}
	public String getAdContent() {
		return adContent;
	}
	public void setAdContent(String adContent) {
		this.adContent = adContent;
	}
	public String getAdId() {
		return adId;
	}
	public void setAdId(String adId) {
		this.adId = adId;
	}
	public String getAdType() {
		return adType;
	}
	public void setAdType(String adType) {
		this.adType = adType;
	}
	public String getAdBanner() {
		return adBanner;
	}
	public void setAdBanner(String adBanner) {
		this.adBanner = adBanner;
	}
}
