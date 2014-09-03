package com.agloco.web.service.model;

import java.io.Serializable;

public class VBFavoriteRes implements Serializable{

	private static final long serialVersionUID = 1094380942376454343L;
	private String name;
	private String url;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
}
