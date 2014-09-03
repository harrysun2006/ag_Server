package com.agloco.dao.util;

import java.util.List;

import org.springframework.context.ApplicationContext;

import com.agloco.dao.SearchDAO;
import com.agloco.model.VBSearchEngine;
import com.agloco.model.VBSearchTrack;
import com.agloco.spring.SpringUtil;

/**
 * 
 * @author terry_zhao
 * @version 1.0
 */
public class SearchDAOUtil {

	//searche engine
	public static void createSearchEngine(VBSearchEngine sEngine){
		getSearchDAO().createSearchEngine(sEngine);
	}
	public static void deleteSearchEngine(VBSearchEngine sEngine){
		getSearchDAO().deleteSearchEngine(sEngine);
	}
	public static void updateSearchEngine(VBSearchEngine sEngine){
		getSearchDAO().updateSearchEngine(sEngine);
	}
	public static VBSearchEngine getSearchEngine(Long engineId){
		return getSearchDAO().getSearchEngine(engineId);
	}
	public static VBSearchEngine getSearchEngine(String engineName){
		return getSearchDAO().getSearchEngine(engineName);
	}
	public static List listAllSearchEngine(){
		return getSearchDAO().listAllSearchEngine();
	}
	
	
	//search track
	public static void createSearchTrack(VBSearchTrack sTrack){
		getSearchDAO().createSearchTrack(sTrack);
	}
	public static void deleteSearchTrack(VBSearchTrack sTrack){
		getSearchDAO().createSearchTrack(sTrack);
	}
	public static void updateSearchTrack(VBSearchTrack sTrack){
		getSearchDAO().updateSearchTrack(sTrack);
	}
	public static VBSearchTrack getSearchTrack(Long TrackId){
		return getSearchDAO().getSearchTrack(TrackId);
	}
	public static List ListSearchTrack(Long memberId){
		return getSearchDAO().ListSearchTrack(memberId);
	}
	
	
	
	private static SearchDAO getSearchDAO(){
		ApplicationContext ctx = SpringUtil.getContext();
		SearchDAOUtil util = (SearchDAOUtil)ctx.getBean(SearchDAOUtil.class.getName());
		return util.searchDAO;
	}

	public void setSearchDAO(SearchDAO searchDAO) {
		this.searchDAO = searchDAO;
	}
	
	private SearchDAO searchDAO;
}
