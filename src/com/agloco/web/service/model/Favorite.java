package com.agloco.web.service.model;

import java.io.Serializable;

public class Favorite implements Serializable {

	private Long favoriteId;
	private String name;
	private String url;
	private String category;
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public Long getFavoriteId() {
		return favoriteId;
	}
	public void setFavoriteId(Long favoriteId) {
		this.favoriteId = favoriteId;
	}
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
