package com.agloco.web.service.model;

import java.io.Serializable;
import java.util.List;

public class VBUpdateFilesRes implements Serializable  {

	private static final long serialVersionUID = 8042605438305961761L;
	private String viewbarId;
	private String rootPath;
	private int result;
	private List filesList;

	public int getResult() {
		return result;
	}
	public void setResult(int result) {
		this.result = result;
	}
	public List getFilesList() {
		return filesList;
	}
	public void setFilesList(List filesList) {
		this.filesList = filesList;
	}
	public String getRootPath() {
		return rootPath;
	}
	public void setRootPath(String rootPath) {
		this.rootPath = rootPath;
	}
	public String getViewbarId() {
		return viewbarId;
	}
	public void setViewbarId(String viewbarId) {
		this.viewbarId = viewbarId;
	}
	
}
