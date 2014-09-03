package com.test;

import java.io.IOException;
import java.util.Calendar;

import junit.framework.TestCase;

import org.springframework.context.ApplicationContext;

import com.agloco.model.VBADKeywords;
import com.agloco.model.VBADMatrix;
import com.agloco.model.VBADMatrixPK;
import com.agloco.model.VBAFDetail;
import com.agloco.model.VBAdvertise;
import com.agloco.model.VBAffiliate;
import com.agloco.model.VBFavorite;
import com.agloco.model.VBSearchEngine;
import com.agloco.service.util.AdvertiseServiceUtil;
import com.agloco.service.util.AffiliateServiceUtil;
import com.agloco.service.util.FavoriteServiceUtil;
import com.agloco.service.util.SearchServiceUtil;
import com.agloco.spring.SpringUtil;

public class TestInitDB extends TestCase {

	private static ApplicationContext ctx = null;
	static{
		try {
			ContextHelper.addResource("jdbc.properties");
			ContextHelper.addResource("jdbc-vb.properties");
			ctx = SpringUtil.getContext();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void testInitDatabase(){
		//which need new database or create new table related
		initAdKeywords();
		initAdvertise();
		initAdMatrix();
		initSearchEngine();
		initFavorte();
		initAfflicate();
		initAfflicateDetail();
	}
	
	private void initAfflicate(){
		
		VBAffiliate af1 = new VBAffiliate();
		af1.setCreateDate(Calendar.getInstance());
		af1.setDescription("");
		af1.setCategory("ttt");
		af1.setName("www.baidu.com");
		AffiliateServiceUtil.createAffiliate(af1);
		
		VBAffiliate af2 = new VBAffiliate();
		af2.setCreateDate(Calendar.getInstance());
		af2.setDescription("");
		af2.setCategory("ttt");
		af2.setName("www.google.com");
		AffiliateServiceUtil.createAffiliate(af2);
		

		
	}
	private void initAfflicateDetail(){
		VBAFDetail afd1 = new VBAFDetail();
		afd1.setAffiliateId(new Long(1));
		afd1.setAgPattern("%s.baidu.com%s=agloco");
		afd1.setPattern("%s.baidu.com");
		afd1.setCategory("");
		afd1.setCreateDate(Calendar.getInstance());
		afd1.setDescription("");
		afd1.setInfo("");
		afd1.setRebate(new Double(1.2));
		afd1.setStatus("N");
		AffiliateServiceUtil.createAFDetail(afd1);
		
		VBAFDetail afd2 = new VBAFDetail();
		afd2.setAffiliateId(new Long(2));
		afd2.setAgPattern("%s.google.com%s=agloco");
		afd2.setPattern("%s.google.com");
		afd2.setCategory("");
		afd2.setCreateDate(Calendar.getInstance());
		afd2.setDescription("");
		afd2.setInfo("");
		afd2.setRebate(new Double(0.9));
		afd2.setStatus("N");
		AffiliateServiceUtil.createAFDetail(afd2);
	}
	private void initFavorte(){
		
		{
			VBFavorite favorite = new VBFavorite();
			favorite.setName("NBA");
			favorite.setUrl("http://www.nba.com");
			favorite.setActive(new Integer(VBFavorite.ACTIVE));
			favorite.setCategory("sports");
			FavoriteServiceUtil.createFavorite(favorite);
		}
		
		{
			VBFavorite favorite = new VBFavorite();
			favorite.setName("tom sports");
			favorite.setUrl("http://sports.tom.com");
			favorite.setActive(new Integer(VBFavorite.ACTIVE));
			favorite.setCategory("sports");
			FavoriteServiceUtil.createFavorite(favorite);
		}
		
		{
			VBFavorite favorite = new VBFavorite();
			favorite.setName("sohu sports");
			favorite.setUrl("http://sports.sohu.com");
			favorite.setActive(new Integer(VBFavorite.ACTIVE));
			favorite.setCategory("sports");
			FavoriteServiceUtil.createFavorite(favorite);
		}
		
		{
			VBFavorite favorite = new VBFavorite();
			favorite.setName("spring");
			favorite.setUrl("http://www.springframework.org/");
			favorite.setActive(new Integer(VBFavorite.ACTIVE));
			favorite.setCategory("j2ee");
			FavoriteServiceUtil.createFavorite(favorite);
		}
		
		{
			VBFavorite favorite = new VBFavorite();
			favorite.setName("hibernate");
			favorite.setUrl("http://www.hibernate.org/");
			favorite.setActive(new Integer(VBFavorite.ACTIVE));
			favorite.setCategory("j2ee");
			FavoriteServiceUtil.createFavorite(favorite);
		}
		
		{
			VBFavorite favorite = new VBFavorite();
			favorite.setName("agloco");
			favorite.setUrl("http://www.agloco.com/");
			favorite.setActive(new Integer(VBFavorite.ACTIVE));
			favorite.setCategory("");
			FavoriteServiceUtil.createFavorite(favorite);
		}
	}
	
	private void initSearchEngine(){
		
		{
			VBSearchEngine se = new VBSearchEngine();
			se.setName("Google");
			se.setUrl("http://www.google.com");
			se.setPattern("search?hl=en&q={[%s]}&btnG=Google+Search");
			se.setDescription("google is great");
			SearchServiceUtil.createSearchEngine(se);
		}
		
		{
			VBSearchEngine se = new VBSearchEngine();
			se.setName("Baidu");
			se.setUrl("http://www.baidu.com");
			se.setPattern("s?wd={[%s]}&cl=3&tn=baiducom&ie=utf-8");
			se.setDescription("baidu is ok");
			SearchServiceUtil.createSearchEngine(se);
		}
		
		{
			VBSearchEngine se = new VBSearchEngine();
			se.setName("Live");
			se.setUrl("http://search.live.com");
			se.setPattern("results.aspx?q={[%s]}&mkt=en-us&FORM=LVCP&go.x=0&go.y=0&go=Search");
			se.setDescription("live is fine");
			SearchServiceUtil.createSearchEngine(se);
		}
		
		{
			VBSearchEngine se = new VBSearchEngine();
			se.setName("Yahoo");
			se.setUrl("http://search.yahoo.com");
			se.setPattern("search?p={[%s]}&fr=yfp-t-501&toggle=1&cop=mss&ei=UTF-8&vc=");
			se.setDescription("yahoo is good");
			SearchServiceUtil.createSearchEngine(se);
		}
		
		{
			VBSearchEngine se = new VBSearchEngine();
			se.setName("Ebay");
			se.setUrl("http://search.ebay.com");
			se.setPattern("search/search.dll?from=R40&satitle={[%s]}&category0=");
			se.setDescription("ebay is good");
			SearchServiceUtil.createSearchEngine(se);
		}
		
		{
			VBSearchEngine se = new VBSearchEngine();
			se.setName("Dictionary");
			se.setUrl("http://www.reference.com");
			se.setPattern("search?q={[%s]}&r=d&db=web");
			se.setDescription("Dictionary is good");
			SearchServiceUtil.createSearchEngine(se);
		}
		
		{
			VBSearchEngine se = new VBSearchEngine();
			se.setName("Wikipedia");
			se.setUrl("http://www.wikipedia.org");
			se.setPattern("wiki/Special:Search?search={[%s]}&go=Go");
			se.setDescription("Wikipedia is good");
			SearchServiceUtil.createSearchEngine(se);
		}
		
		assertTrue(true);
		
	}

	private void initAdKeywords(){
//		init keywords
		{
			VBADKeywords kw = new VBADKeywords();
			kw.setContent("car");
			kw.setDescription("need for speed");
			AdvertiseServiceUtil.createADKeywords(kw);
		}
		{
			VBADKeywords kw = new VBADKeywords();
			kw.setContent("bmw");
			kw.setDescription("girls love them");
			AdvertiseServiceUtil.createADKeywords(kw);
		}
		{
			VBADKeywords kw = new VBADKeywords();
			kw.setContent("benz");
			kw.setDescription("men love them");
			AdvertiseServiceUtil.createADKeywords(kw);
		}
		{
			VBADKeywords kw = new VBADKeywords();
			kw.setContent("mp3");
			kw.setDescription("listen to the music");
			AdvertiseServiceUtil.createADKeywords(kw);
		}
		{
			VBADKeywords kw = new VBADKeywords();
			kw.setContent("sony");
			kw.setDescription("rejected them!");
			AdvertiseServiceUtil.createADKeywords(kw);
		}
		
		{
			VBADKeywords kw = new VBADKeywords();
			kw.setContent("red");
			kw.setDescription("enthusiasm");
			AdvertiseServiceUtil.createADKeywords(kw);
		}
		
		{
			VBADKeywords kw = new VBADKeywords();
			kw.setContent("blue");
			kw.setDescription("profound");
			AdvertiseServiceUtil.createADKeywords(kw);
		}
		assertNotNull(AdvertiseServiceUtil.getADKeywords("car"));
		assertNotNull(AdvertiseServiceUtil.getADKeywords("bmw"));
		assertNotNull(AdvertiseServiceUtil.getADKeywords("benz"));
		assertNotNull(AdvertiseServiceUtil.getADKeywords("mp3"));
		assertNotNull(AdvertiseServiceUtil.getADKeywords("sony"));
		assertNotNull(AdvertiseServiceUtil.getADKeywords("red"));
		assertNotNull(AdvertiseServiceUtil.getADKeywords("blue"));
		
	}
	private void initAdvertise(){
//		 init advertise
		{
			VBAdvertise advertise = new VBAdvertise();
			advertise.setAdId("car.bmw.001");
			advertise.setCategory("car");
			advertise.setBanner("bmw car");
			advertise.setContent("http://www.google.com/search?hl=en&newwindow=1&q=car+bmw&btnG=Search");
			advertise.setDescription("bmw car is good");
			advertise.setSponsor("bmw");
			advertise.setType("url");
			advertise.setPattern("waiting for ...");
			AdvertiseServiceUtil.createAdvertise(advertise);
		}
		
		{
			VBAdvertise advertise = new VBAdvertise();
			advertise.setAdId("car.bmw.002");
			advertise.setCategory("car");
			advertise.setBanner("red bmw car");
			advertise.setContent("http://www.google.com/search?hl=en&newwindow=1&q=car+bmw+red&btnG=Search");
			advertise.setDescription("red bmw car is better");
			advertise.setSponsor("bmw");
			advertise.setType("url");
			advertise.setPattern("waiting for ...");
			AdvertiseServiceUtil.createAdvertise(advertise);
		}
		
		{
			VBAdvertise advertise = new VBAdvertise();
			advertise.setAdId("car.benz.001");
			advertise.setCategory("car");
			advertise.setBanner("benz car");
			advertise.setContent("http://www.google.com/search?hl=en&newwindow=1&q=car+benz&btnG=Search");
			advertise.setDescription("benz car is good");
			advertise.setSponsor("benz");
			advertise.setType("url");
			advertise.setPattern("waiting for ...");
			AdvertiseServiceUtil.createAdvertise(advertise);	
		}
		
		
		{
			VBAdvertise advertise = new VBAdvertise();
			advertise.setAdId("car.benz.002");
			advertise.setCategory("car");
			advertise.setBanner("blue benz car");
			advertise.setContent("http://www.google.com/search?hl=en&newwindow=1&q=car+benz+blue&btnG=Search");
			advertise.setDescription("red benz car is good");
			advertise.setSponsor("benz");
			advertise.setType("url");
			advertise.setPattern("waiting for ...");
			AdvertiseServiceUtil.createAdvertise(advertise);	
		}
		
		{
			VBAdvertise advertise = new VBAdvertise();
			advertise.setAdId("mp3.sony.001");
			advertise.setCategory("mp3");
			advertise.setBanner("sony mp3");
			advertise.setContent("http://www.google.com/search?hl=en&newwindow=1&q=mp3+sony&btnG=Search");
			advertise.setDescription("sony mp3 is good");
			advertise.setSponsor("sony");
			advertise.setType("url");
			advertise.setPattern("waiting for ...");
			AdvertiseServiceUtil.createAdvertise(advertise);
		}
		
		{
			VBAdvertise advertise = new VBAdvertise();
			advertise.setAdId("mp3.sony.002");
			advertise.setCategory("mp3");
			advertise.setBanner("blue sony mp3");
			advertise.setContent("http://www.google.com/search?hl=en&newwindow=1&q=mp3+sony+blue&btnG=Search");
			advertise.setDescription("blue sony mp3 is good");
			advertise.setSponsor("sony");
			advertise.setType("url");
			advertise.setPattern("waiting for ...");
			AdvertiseServiceUtil.createAdvertise(advertise);	
		}
		assertNotNull(AdvertiseServiceUtil.getAdvertise("car.bmw.001"));
		assertNotNull(AdvertiseServiceUtil.getAdvertise("car.bmw.002"));
		assertNotNull(AdvertiseServiceUtil.getAdvertise("car.benz.001"));
		assertNotNull(AdvertiseServiceUtil.getAdvertise("car.benz.002"));
		assertNotNull(AdvertiseServiceUtil.getAdvertise("mp3.sony.001"));
		assertNotNull(AdvertiseServiceUtil.getAdvertise("mp3.sony.002"));
	}
	private void initAdMatrix(){
		
		
		
		
		
		/////////////////////////////////////////////////
		
		{
			VBADMatrix matrix = new VBADMatrix();
			VBADMatrixPK pk = new VBADMatrixPK();
			pk.setAdId("car.bmw.001");
			pk.setAdKeywordId(new Long(1));
			matrix.setPrimaryKey(pk);
			matrix.setWeight(new Double(65));
			AdvertiseServiceUtil.createADMatrix(matrix);
		}
		
		{
			VBADMatrix matrix = new VBADMatrix();
			VBADMatrixPK pk = new VBADMatrixPK();
			pk.setAdId("car.bmw.001");
			pk.setAdKeywordId(new Long(2));
			matrix.setPrimaryKey(pk);
			matrix.setWeight(new Double(66));
			AdvertiseServiceUtil.createADMatrix(matrix);
		}
		
		{
			VBADMatrix matrix = new VBADMatrix();
			VBADMatrixPK pk = new VBADMatrixPK();
			pk.setAdId("car.bmw.002");
			pk.setAdKeywordId(new Long(1));
			matrix.setPrimaryKey(pk);
			matrix.setWeight(new Double(67));
			AdvertiseServiceUtil.createADMatrix(matrix);
		}
		
		{
			VBADMatrix matrix = new VBADMatrix();
			VBADMatrixPK pk = new VBADMatrixPK();
			pk.setAdId("car.bmw.002");
			pk.setAdKeywordId(new Long(2));
			matrix.setPrimaryKey(pk);
			matrix.setWeight(new Double(81));
			AdvertiseServiceUtil.createADMatrix(matrix);
		}
		{
			VBADMatrix matrix = new VBADMatrix();
			VBADMatrixPK pk = new VBADMatrixPK();
			pk.setAdId("car.bmw.002");
			pk.setAdKeywordId(new Long(6));
			matrix.setPrimaryKey(pk);
			matrix.setWeight(new Double(31));
			AdvertiseServiceUtil.createADMatrix(matrix);
		}
		
		////
		{
			VBADMatrix matrix = new VBADMatrix();
			VBADMatrixPK pk = new VBADMatrixPK();
			pk.setAdId("car.benz.001");
			pk.setAdKeywordId(new Long(1));
			matrix.setPrimaryKey(pk);
			matrix.setWeight(new Double(68));
			AdvertiseServiceUtil.createADMatrix(matrix);
		}
		
		{
			VBADMatrix matrix = new VBADMatrix();
			VBADMatrixPK pk = new VBADMatrixPK();
			pk.setAdId("car.benz.001");
			pk.setAdKeywordId(new Long(3));
			matrix.setPrimaryKey(pk);
			matrix.setWeight(new Double(82));
			AdvertiseServiceUtil.createADMatrix(matrix);
		}
		
		{
			VBADMatrix matrix = new VBADMatrix();
			VBADMatrixPK pk = new VBADMatrixPK();
			pk.setAdId("car.benz.002");
			pk.setAdKeywordId(new Long(1));
			matrix.setPrimaryKey(pk);
			matrix.setWeight(new Double(69));
			AdvertiseServiceUtil.createADMatrix(matrix);
		}
		
		{
			VBADMatrix matrix = new VBADMatrix();
			VBADMatrixPK pk = new VBADMatrixPK();
			pk.setAdId("car.benz.002");
			pk.setAdKeywordId(new Long(3));
			matrix.setPrimaryKey(pk);
			matrix.setWeight(new Double(83));
			AdvertiseServiceUtil.createADMatrix(matrix);
		}
		{
			VBADMatrix matrix = new VBADMatrix();
			VBADMatrixPK pk = new VBADMatrixPK();
			pk.setAdId("car.benz.002");
			pk.setAdKeywordId(new Long(7));
			matrix.setPrimaryKey(pk);
			matrix.setWeight(new Double(32));
			AdvertiseServiceUtil.createADMatrix(matrix);
		}
		////
		
		
		{
			VBADMatrix matrix = new VBADMatrix();
			VBADMatrixPK pk = new VBADMatrixPK();
			pk.setAdId("mp3.sony.001");
			pk.setAdKeywordId(new Long(4));
			matrix.setPrimaryKey(pk);
			matrix.setWeight(new Double(61));
			AdvertiseServiceUtil.createADMatrix(matrix);
		}
		
		{
			VBADMatrix matrix = new VBADMatrix();
			VBADMatrixPK pk = new VBADMatrixPK();
			pk.setAdId("mp3.sony.001");
			pk.setAdKeywordId(new Long(5));
			matrix.setPrimaryKey(pk);
			matrix.setWeight(new Double(85));
			AdvertiseServiceUtil.createADMatrix(matrix);
		}
		
		{
			VBADMatrix matrix = new VBADMatrix();
			VBADMatrixPK pk = new VBADMatrixPK();
			pk.setAdId("mp3.sony.002");
			pk.setAdKeywordId(new Long(4));
			matrix.setPrimaryKey(pk);
			matrix.setWeight(new Double(62));
			AdvertiseServiceUtil.createADMatrix(matrix);
		}
		
		{
			VBADMatrix matrix = new VBADMatrix();
			VBADMatrixPK pk = new VBADMatrixPK();
			pk.setAdId("mp3.sony.002");
			pk.setAdKeywordId(new Long(5));
			matrix.setPrimaryKey(pk);
			matrix.setWeight(new Double(86));
			AdvertiseServiceUtil.createADMatrix(matrix);
		}
		{
			VBADMatrix matrix = new VBADMatrix();
			VBADMatrixPK pk = new VBADMatrixPK();
			pk.setAdId("mp3.sony.002");
			pk.setAdKeywordId(new Long(7));
			matrix.setPrimaryKey(pk);
			matrix.setWeight(new Double(35));
			AdvertiseServiceUtil.createADMatrix(matrix);
		}
		
		assertTrue(true);
		
	}
}
