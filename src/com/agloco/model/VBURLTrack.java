package com.agloco.model;

import java.util.Calendar;

public class VBURLTrack {

	private Long trackId;
	private Long memberId;
	private Calendar createDate;
	private String domainName;
	private String completeURL;
	private String title;
	private String keywords;
	private String errorDescription;
	private String errorStatus;
	private String browser;
	private String description;
	
	public String getBrowser() {
		return browser;
	}
	public void setBrowser(String browser) {
		this.browser = browser;
	}
	public String getCompleteURL() {
		return completeURL;
	}
	public void setCompleteURL(String completeURL) {
		this.completeURL = completeURL;
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
	public String getDomainName() {
		return domainName;
	}
	public void setDomainName(String domainName) {
		this.domainName = domainName;
	}
	public String getErrorDescription() {
		return errorDescription;
	}
	public void setErrorDescription(String errorDescription) {
		this.errorDescription = errorDescription;
	}
	public String getErrorStatus() {
		return errorStatus;
	}
	public void setErrorStatus(String errorStatus) {
		this.errorStatus = errorStatus;
	}
	public Long getMemberId() {
		return memberId;
	}
	public String getKeywords() {
		return keywords;
	}
	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}
	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Long getTrackId() {
		return trackId;
	}
	public void setTrackId(Long trackId) {
		this.trackId = trackId;
	}

}
