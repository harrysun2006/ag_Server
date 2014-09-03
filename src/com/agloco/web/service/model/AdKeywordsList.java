package com.agloco.web.service.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class AdKeywordsList implements Serializable {

	private List keywordList = new ArrayList();

	public List getKeywordList() {
		return keywordList;
	}

	public void setKeywordList(List keywordList) {
		this.keywordList.addAll(keywordList);
	}
}
