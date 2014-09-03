package com.agloco.model;

import java.util.Calendar;

public class VBRule {

	private Long ruleId;
	private String category;
	private Calendar createDate;
	private String name;
	private String value;
	private String operator;
	private Integer active;
	
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public Calendar getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Calendar createDate) {
		this.createDate = createDate;
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
	public Long getRuleId() {
		return ruleId;
	}
	public void setRuleId(Long ruleId) {
		this.ruleId = ruleId;
	}
	public Integer getActive() {
		return active;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public void setActive(Integer active) {
		this.active = active;
	}

}
