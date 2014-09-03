package com.agloco.web.service.model;

import java.io.Serializable;

/**
 * @author author E-mail:zhaon12@gmail.com
 * @version createDate:Apr 5, 2007 3:50:09 PM content
 */
public class MOptionRes implements Serializable {
	private static final long serialVersionUID = -5361274207817881883L;
	private Long memberId;
	private String name;
	private String value;
	
	public Long getMemberId() {
		return memberId;
	}
	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	

}
