package com.agloco.web.service.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class AdMatrixList implements Serializable {

	private List matrixList = new ArrayList();

	public List getMatrixList() {
		return matrixList;
	}

	public void setMatrixList(List matrixList) {
		this.matrixList.addAll(matrixList);
	}
}
