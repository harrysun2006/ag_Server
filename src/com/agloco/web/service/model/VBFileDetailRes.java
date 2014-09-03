package com.agloco.web.service.model;

import java.io.Serializable;

public class VBFileDetailRes implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1323004207526711618L;
	private String fileName;
	private String filePath;
	private String md5;
	private String fileVersion;
	
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public String getFileVersion() {
		return fileVersion;
	}
	public void setFileVersion(String fileVersion) {
		this.fileVersion = fileVersion;
	}
	public String getMd5() {
		return md5;
	}
	public void setMd5(String md5) {
		this.md5 = md5;
	}
	
}
