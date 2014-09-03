package com.agloco.model;

public class VBSuggestion {

	private Long suggestionId;
	private Long memberId;
	private String viewbarId;
	private String suggestion;
	
	public Long getMemberId() {
		return memberId;
	}
	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}
	public String getSuggestion() {
		return suggestion;
	}
	public void setSuggestion(String suggestion) {
		this.suggestion = suggestion;
	}
	public Long getSuggestionId() {
		return suggestionId;
	}
	public void setSuggestionId(Long suggestionId) {
		this.suggestionId = suggestionId;
	}
	public String getViewbarId() {
		return viewbarId;
	}
	public void setViewbarId(String viewbarId) {
		this.viewbarId = viewbarId;
	}
	
}
