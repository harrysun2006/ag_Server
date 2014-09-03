package com.agloco.service.util;

import java.util.List;

import org.springframework.context.ApplicationContext;

import com.agloco.model.VBSearchEngine;
import com.agloco.model.VBSearchTrack;
import com.agloco.service.SearchService;
import com.agloco.spring.SpringUtil;
import com.agloco.util.ClusterPool;
import com.agloco.util.StringUtil;
import com.opensymphony.oscache.base.NeedsRefreshException;
import com.opensymphony.oscache.general.GeneralCacheAdministrator;

/**
 * 
 * @author terry_zhao
 * @version 1.0
 */
public class SearchServiceUtil {
	
	private static GeneralCacheAdministrator _cache = ClusterPool.getCache();
	public final static String CACHE_SEARCH_ENGINE_LIST = "search.engine.list";
	public static final String GROUP_NAME = SearchServiceUtil.class.getName();
	public static final String[] GROUP_NAME_ARRAY = new String[] {GROUP_NAME};
	
	
	//searche engine
	public static void createSearchEngine(VBSearchEngine sEngine){
		getSearchService().createSearchEngine(sEngine);
	}
	public static void deleteSearchEngine(VBSearchEngine sEngine){
		getSearchService().deleteSearchEngine(sEngine);
	}
	public static void updateSearchEngine(VBSearchEngine sEngine){
		getSearchService().updateSearchEngine(sEngine);
	}
	public static VBSearchEngine getSearchEngine(Long engineId){
		return getSearchService().getSearchEngine(engineId);
	}
	public static VBSearchEngine getSearchEngine(String engineName){
		return getSearchService().getSearchEngine(engineName);
	}
	public static List listAllSearchEngine(){
		
		String key = encodeKey(CACHE_SEARCH_ENGINE_LIST);
		List list = null;
		try {
			list = (List)_cache.getFromCache(key);
		} catch (NeedsRefreshException e) {
			list = listAllSearchEngineFromDB();
			_cache.putInCache(key, list, GROUP_NAME_ARRAY);
		}
		finally{
			if(list == null){
				_cache.cancelUpdate(key);
			}
		}
		return list;
		
	}
	private static List listAllSearchEngineFromDB(){
		
		return getSearchService().listAllSearchEngine();
	}
	
	//search track
	public static void createSearchTrack(VBSearchTrack sTrack){
		getSearchService().createSearchTrack(sTrack);
	}
	public static void deleteSearchTrack(VBSearchTrack sTrack){
		getSearchService().createSearchTrack(sTrack);
	}
	public static void updateSearchTrack(VBSearchTrack sTrack){
		getSearchService().updateSearchTrack(sTrack);
	}
	public static VBSearchTrack getSearchTrack(Long TrackId){
		return getSearchService().getSearchTrack(TrackId);
	}
	public static List ListSearchTrack(Long memberId){
		return getSearchService().ListSearchTrack(memberId);
	}
	
	
	public static void clearSearchServiceUtilPool() {
		_cache.flushGroup(GROUP_NAME);
	}

	public static void clearSearchServiceUtilPool(String key) {
		key = encodeKey(key);
		_cache.flushEntry(key);
	}	
	
	private static String encodeKey(String key) {
		return GROUP_NAME + StringUtil.POUND + key;
	}
	
	private static SearchService getSearchService(){
		ApplicationContext ctx = SpringUtil.getContext();
		SearchServiceUtil util = (SearchServiceUtil)ctx.getBean(SearchServiceUtil.class.getName());
		return util.searchService;
	}

	public void setSearchService(SearchService searchService) {
		this.searchService = searchService;
	}
	
	private SearchService searchService;




}
