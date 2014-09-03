package com.viewbar.test.util;

import java.util.Calendar;

import org.apache.commons.lang.StringUtils;

import com.agloco.parse.BeanToXmlUtil;
import com.agloco.web.service.model.AffiliateReq;
import com.agloco.web.service.model.LoginReq;
import com.agloco.web.service.model.LogoutReq;
import com.agloco.web.service.model.SearchKeywordReq;
import com.agloco.web.service.model.TimeTrack;
import com.agloco.web.service.model.TimeTrackListReq;
import com.agloco.web.service.model.URLTrack;
import com.agloco.web.service.model.URLTrackListReq;

/**
 * 
 * @author Erick Kong
 * @see GenerateXmlUtil.java
 * @createDate: 2007-4-23
 * @version 1.0
 */

public class GenerateXmlUtil {
	public static StringBuffer generateLoginXml(String memberCode) {
		return generateLoginXml(memberCode,null,null);
	}

	public static StringBuffer generateLoginXml(String memberCode,String searchEngineHash,String favoriteHash) {
		LoginReq req = new LoginReq();
		req.setUserName(memberCode);
		req.setPassword("test");
		if(StringUtils.isBlank(searchEngineHash)){
			req.setSearchEngineHash("2e1c1d14a34b564bbce7cb27ba883a8b");
		}
		else{
			req.setSearchEngineHash(searchEngineHash);
		}
		
		if(StringUtils.isBlank(favoriteHash)){
			req.setFavoriteHash("d4270d7b4a5eb21d2aed1b26a1a89878");
		}
		else{
			req.setFavoriteHash(favoriteHash);
		}
		
		req.setComputerName("computer name");
		req.setDomainName("domain name");
		req.setIpAddr("119.119.119.1");
		req.setMacAddr("df-df-44-66-uj");
		req.setOsVersion("windowns 2000");
		req.setViewbarId("win32.1.0.1");
		String xml = BeanToXmlUtil.parseByCastor(req);
		
		return new StringBuffer(xml);
	}

	public static StringBuffer generateTimeTrackXml(Long memberId) {
		return generateTimeTrackXml(memberId, 1);
	}

	public static StringBuffer generateTimeTrackXml(Long memberId, int count) {
		TimeTrackListReq timeTracks = new TimeTrackListReq();
		timeTracks.setMemberId(memberId);
		Calendar now = Calendar.getInstance();
		Calendar yesterday = Calendar.getInstance();
//		yesterday.add(Calendar.DATE, -1);
		timeTracks.setSubmitId(new Long(now.getTimeInMillis()/1000));

		for (int i = 0; i < count; i++) {
			TimeTrack timeTrack = new TimeTrack();
			if(i % 2 == 0)
			{
				timeTrack.setBeginPoint(new Long(now.getTimeInMillis()/1000 - 300));
				timeTrack.setEndPoint(new Long(now.getTimeInMillis()/1000-180));
			}
			else
			{
				timeTrack.setBeginPoint(new Long(yesterday.getTimeInMillis()/1000 - 180));
				timeTrack.setEndPoint(new Long(yesterday.getTimeInMillis()/1000));
			}
			timeTrack.setPoint(new Double(0.0));
			timeTrack.setStatus("");
			timeTracks.addItem(timeTrack);
		}

		String xml = BeanToXmlUtil.parseByCastor(timeTracks);
		return new StringBuffer(xml);
	}

	public static StringBuffer generateURLTrackXml(Long memberId) {
		return generateURLTrackXml(memberId, 1);
	}

	public static StringBuffer generateURLTrackXml(Long memberId, int count) {
		URLTrackListReq urlTracks = new URLTrackListReq();
		urlTracks.setMemberId(memberId);

		for (int i = 0; i < count; i++) {
			URLTrack urlTrack = new URLTrack();
			urlTrack.setBrowser("IE");
			urlTrack.setCompleteURL("http://test.agloco.com/testcase");
			urlTrack.setDomainName("http://test.agloco.com/");
			urlTrack.setKeywords("test,测试");
			urlTrack.setTitle("test,测试");
			urlTracks.addItem(urlTrack);
		}

		String xml = BeanToXmlUtil.parseByCastor(urlTracks);
		return new StringBuffer(xml);
	}

	public static StringBuffer generateViewbarCheckXML() {
		return new StringBuffer(
				"<?xml version=\"1.0\" encoding=\"UTF-8\"?> <viewbarReq viewbarId=\"win32.0.50\" />");
	}
	public static StringBuffer generateAutoUpdateXML() {
		return new StringBuffer(
				"<?xml version=\"1.0\" encoding=\"UTF-8\"?> <viewbarReq viewbarId=\"win32.0.50\" />");
	}
	
	public static StringBuffer generateLogoutXML()
	{
		LogoutReq logout = new LogoutReq();
		logout.setComputerName("");
		logout.setDomainName("");
		logout.setIpAddr("");
		logout.setViewbarId("");
		String xml = BeanToXmlUtil.parseByCastor(logout);
		return new StringBuffer(xml);
	}
	
	public static StringBuffer generateSearchKeywordsXML(Long memberId)
	{
		SearchKeywordReq searchReq = new SearchKeywordReq();
		searchReq.setBrowser("MSIE6.0");
		searchReq.setKeywords(new String[]{"Englist","中文"});
		searchReq.setMemberId(memberId);
		searchReq.setSearchEngineId(new Long(1));
		String xml = BeanToXmlUtil.parseByCastor(searchReq);
		return new StringBuffer(xml);
	}
	
	public static StringBuffer generateAffiliateXML()
	{
		AffiliateReq affiliate = new AffiliateReq();
		affiliate.setAfdTime(1176779700);
		affiliate.setAfTime(1176779700);
		String xml = BeanToXmlUtil.parseByCastor(affiliate);
		return new StringBuffer(xml);
	}
	
	public static String format(int i)
	{
		int base = i/10000;
		int residue = i%10000;
		String[] baseCodes = new String[]{"BBBB","BBBC","BBBD","BBBE","BBBF","BBBG","BBBH","BBBI","BBBJ","BBBK"};
		String baseCode = baseCodes[base];
		if (residue < 1)
			return baseCode+"0000";
		else if (residue < 10)
			return baseCode+"000" + residue;
		else if (residue < 100)
			return baseCode+"00" + residue;
		else if (residue < 1000)
			return baseCode+"0" + residue;
		else
			return baseCode+residue + "";
	}

}
