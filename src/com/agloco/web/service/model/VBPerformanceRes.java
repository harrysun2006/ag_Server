package com.agloco.web.service.model;

import java.io.Serializable;

public class VBPerformanceRes implements Serializable {
	
	private static final long serialVersionUID = 6202179412997282102L;
	private String name="";
	private String value="";
	private String operator="";
	private String currentValue="";
	
	public VBPerformanceRes(){
		
	}
	public String getCurrentValue() {
		return currentValue;
	}
	public void setCurrentValue(String currentValue) {
		this.currentValue = currentValue;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getOperator() {
		return operator;
	}
	public void setOperator(String operator) {
		this.operator = operator;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
}
