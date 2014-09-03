package com.agloco.model;

public class VBFavorite {

	public final static int ACTIVE   = 1;
	public final static int INACTIVE = 0;
	
	private Long favoriteId;
	private String name;
	private String url;
	private String category;
	private Integer active;
	private String sponsor;
	private String description;
	
	public Integer getActive() {
		return active;
	}
	public void setActive(Integer active) {
		this.active = active;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
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
	public String getSponsor() {
		return sponsor;
	}
	public void setSponsor(String sponsor) {
		this.sponsor = sponsor;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
}
