package com.agloco.model;

import java.io.Serializable;
import java.util.Calendar;

public class VBTools implements Serializable {

	private Long id;
	private String name;
	private String version;
	private String operatingSystem;
	private String description;
	private Calendar createDate;
	private Long downloadCount;
	
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
	public Long getDownloadCount() {
		return downloadCount;
	}
	public void setDownloadCount(Long downloadCount) {
		this.downloadCount = downloadCount;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getOperatingSystem() {
		return operatingSystem;
	}
	public void setOperatingSystem(String operatingSystem) {
		this.operatingSystem = operatingSystem;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	
}
