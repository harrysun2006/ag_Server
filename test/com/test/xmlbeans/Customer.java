package com.test.xmlbeans;

import java.util.List;

public class Customer {

	private Long id;
	private String name;
	private String code;
	private List orders;
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
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
	public List getOrders() {
		return orders;
	}
	public void setOrders(List orders) {
		this.orders = orders;
	}

}
