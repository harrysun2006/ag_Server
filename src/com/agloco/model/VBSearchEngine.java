package com.agloco.model;

public class VBSearchEngine {

	private Long engineId;
	private String name;
	private String url;
	private String pattern;
	private String description;
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Long getEngineId() {
		return engineId;
	}
	public void setEngineId(Long engineId) {
		this.engineId = engineId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPattern() {
		return pattern;
	}
	public void setPattern(String pattern) {
		this.pattern = pattern;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
}
