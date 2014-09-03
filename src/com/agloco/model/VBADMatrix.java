package com.agloco.model;

import java.util.Calendar;

public class VBADMatrix {

	private VBADMatrixPK primaryKey;
	private Double weight;
	private Calendar createDate;
	

	public VBADMatrixPK getPrimaryKey() {
		return primaryKey;
	}
	public void setPrimaryKey(VBADMatrixPK primaryKey) {
		this.primaryKey = primaryKey;
	}

	public Calendar getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Calendar createDate) {
		this.createDate = createDate;
	}
	public Double getWeight() {
		return weight;
	}
	public void setWeight(Double weight) {
		this.weight = weight;
	}

	
}
