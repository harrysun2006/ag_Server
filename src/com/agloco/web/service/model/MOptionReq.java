package com.agloco.web.service.model;

import java.io.Serializable;

/**
 * @author 		author E-mail:zhaon12@gmail.com
 * @version 		1.0
 * @createDate 	createDate:Apr 9, 2007 3:17:56 PM
 * @content		
 */
public class MOptionReq implements Serializable {

	private static final long serialVersionUID = 6966941179500935348L;
	private long memberId;
	private String name;
	private String value;
	
	public long getMemberId() {
		return memberId;
	}
	public void setMemberId(long memberId) {
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
