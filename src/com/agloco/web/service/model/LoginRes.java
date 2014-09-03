package com.agloco.web.service.model;

import java.util.ArrayList;
import java.util.List;

public class LoginRes {

	private int result;

	private Long memberId;

	private long serverTime;

	private Long nextSubmitTime;

	private List favorites = new ArrayList();

	private List engines = new ArrayList();

	private String adsBaseName;

	private String adsJsName;

	public int getResult() {
		return result;
	}

	public void setResult(int result) {
		this.result = result;
	}

	public Long getMemberId() {
		return memberId;
	}

	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}

	public List getFavorites() {
		return favorites;
	}

	public void setFavorites(List favorite) {
		this.favorites.add(favorite);
	}

	public long getServerTime() {
		return serverTime;
	}

	public void setServerTime(long serverTime) {
		this.serverTime = serverTime;
	}

	public List getEngines() {
		return engines;
	}

	public void setEngines(List engines) {
		this.engines.add(engines);
	}

	public Long getNextSubmitTime() {
		return nextSubmitTime;
	}

	public void setNextSubmitTime(Long nextSubmitTime) {
		this.nextSubmitTime = nextSubmitTime;
	}

	public String getAdsBaseName() {
		return adsBaseName;
	}

	public void setAdsBaseName(String adsBaseName) {
		this.adsBaseName = adsBaseName;
	}

	public String getAdsJsName() {
		return adsJsName;
	}

	public void setAdsJsName(String adsJsName) {
		this.adsJsName = adsJsName;
	}

}
