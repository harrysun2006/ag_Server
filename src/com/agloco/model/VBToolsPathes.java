package com.agloco.model;

import java.io.Serializable;

public class VBToolsPathes implements Serializable {

	private Long id;
	private Long toolId;
	private String url;
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Long getToolId() {
		return toolId;
	}
	public void setToolId(Long toolId) {
		this.toolId = toolId;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
}
