package com.agloco.model;

import java.io.Serializable;

import com.agloco.util.StringUtil;

public class VBADMatrixPK implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3068077285151267695L;
	private Long adKeywordId;
	private String adId;
	
	public VBADMatrixPK(){
		
	}
	public VBADMatrixPK(Long adKeywordId, String adId) {
		super();
		this.adKeywordId = adKeywordId;
		this.adId = adId;
	}
	
	public String getAdId() {
		return adId;
	}
	public void setAdId(String adId) {
		this.adId = adId;
	}
	public Long getAdKeywordId() {
		return adKeywordId;
	}
	public void setAdKeywordId(Long adKeywordId) {
		this.adKeywordId = adKeywordId;
	}
	
	public int compareTo(Object obj) {
		if (obj == null) {
			return -1;
		}
		VBADMatrixPK pk = (VBADMatrixPK)obj;
		int value = 0;
		value = adKeywordId.compareTo(pk.getAdKeywordId());
		if (value != 0) {
			return value;
		}
		value = adId.compareTo(pk.getAdId());
		if (value != 0) {
			return value;
		}
		return 0;
	}
	
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		VBADMatrixPK pk = null;

		try {
			pk = (VBADMatrixPK)obj;
		}
		catch (ClassCastException cce) {
			return false;
		}

		if ((adKeywordId.compareTo(pk.getAdKeywordId()) == 0) && (adId.equals(pk.getAdId()))) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public int hashCode() {
		return (adKeywordId + adId).hashCode();
	}

	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append(StringUtil.OPEN_CURLY_BRACE);
		sb.append("adKeywordId");
		sb.append(StringUtil.EQUAL);
		sb.append(adKeywordId);
		sb.append(StringUtil.COMMA);
		sb.append(StringUtil.SPACE);
		sb.append("adId");
		sb.append(StringUtil.EQUAL);
		sb.append(adId);
		sb.append(StringUtil.CLOSE_CURLY_BRACE);

		return sb.toString();
	}

}
