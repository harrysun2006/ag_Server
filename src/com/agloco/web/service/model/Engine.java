package com.agloco.web.service.model;

import java.io.Serializable;

public class Engine implements Serializable {

	private Long engineId;
	private String name;
	private String url;
	private String pattern;
	
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
