package com.agloco.web.service.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class SearchKeywordRes implements Serializable {

	private int result;
	private List advertises = new ArrayList();
	private AdMatrixList matrixList;
	private AdKeywordsList keywordsList;
	
	public AdKeywordsList getKeywordsList() {
		return keywordsList;
	}
	public void setKeywordsList(AdKeywordsList keywordsList) {
		this.keywordsList = keywordsList;
	}
	public int getResult() {
		return result;
	}
	public void setResult(int result) {
		this.result = result;
	}
	public List getAdvertises() {
		return advertises;
	}
	public void setAdvertises(List advertises) {
		this.advertises.add(advertises);
	}
	public AdMatrixList getMatrixList() {
		return matrixList;
	}
	public void setMatrixList(AdMatrixList matrixList) {
		this.matrixList = matrixList;
	}
	
}
