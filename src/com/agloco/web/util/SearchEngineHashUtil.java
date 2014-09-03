package com.agloco.web.util;

import java.security.NoSuchAlgorithmException;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.agloco.model.VBSearchEngine;
import com.agloco.service.util.SearchServiceUtil;
import com.agloco.util.ClusterPool;
import com.agloco.util.StringUtil;
import com.opensymphony.oscache.base.NeedsRefreshException;
import com.opensymphony.oscache.general.GeneralCacheAdministrator;

/**
 *  
 * @author terry_zhao
 * @version 1.0
 */
public class SearchEngineHashUtil {
	
	private final static Log log = LogFactory.getLog(SearchEngineHashUtil.class);
	private static GeneralCacheAdministrator _cache = ClusterPool.getCache();
	public final static String CACHE_ALL_SEARCH_ENGINE_HASH = "all.search.engine.hash";
	public static final String GROUP_NAME = SearchEngineHashUtil.class.getName();
	public static final String[] GROUP_NAME_ARRAY = new String[] {GROUP_NAME};

	public static String getAllHashCode(){
		String key = encodeKey(CACHE_ALL_SEARCH_ENGINE_HASH);
		String hash = null;
		try {
			hash = (String)_cache.getFromCache(key);
		} catch (NeedsRefreshException e) {
			hash = regetAllHashCode();
			_cache.putInCache(key, hash, GROUP_NAME_ARRAY);
		}
		finally{
			if(hash == null){
				_cache.cancelUpdate(key);
			}
		}
		return hash;
	}
	
	public static void clearSearchEngineHashPool() {
		_cache.flushGroup(GROUP_NAME);
	}

	public static void clearSearchEngineHashPool(String key) {
		key = encodeKey(key);
		_cache.flushEntry(key);
	}	
	
	private static String encodeKey(String key) {
		return GROUP_NAME + StringUtil.POUND + key;
	}
	
	public static String regetAllHashCode(){
		String hashCode = null;
		List list = listAllSearchEngine();
		if(list == null || list.size() < 1){
			return hashCode;	
		}
		try {
			StringBuffer sb = new StringBuffer();
			for(Iterator it = list.iterator(); it.hasNext();){
				VBSearchEngine se = (VBSearchEngine)it.next();
				sb.append(se.getEngineId())
				  .append(se.getName())
				  .append(se.getUrl())
				  .append(se.getPattern());
			}
			hashCode = MD5HashUtil.hashCode(sb.toString());
		} catch (NoSuchAlgorithmException e) {
			if(log.isErrorEnabled()){
				log.error("md5 hash error", e);
			}
		}
		return hashCode;
	}
	
//	public static List listAllHashCode(){
//		List list = listAllSearchEngine();
//		if(list == null || list.size() < 1){
//			return null;	
//		}
//		
//		List hash = new ArrayList();
//		try {
//			for(Iterator it = list.iterator(); it.hasNext();){
//				VBSearchEngine se = (VBSearchEngine)it.next();
//				StringBuffer sb = new StringBuffer();
//				sb.append(se.getEngineId())
//				  .append(se.getName())
//				  .append(se.getUrl())
//				  .append(se.getPattern());
//
//				
//				SearchEngineHash seh = new SearchEngineHash();
//				seh.setSearchEngineId(se.getEngineId());
//				seh.setHashcode(MD5HashUtil.hashCode(sb.toString()));
//				hash.add(seh);
//				
//			}
//		} catch (NoSuchAlgorithmException e) {
//			if(log.isErrorEnabled()){
//				log.error("md5 hash error", e);
//			}
//		}
//		return hash;
//	}
	
	
	private static List listAllSearchEngine(){
		return SearchServiceUtil.listAllSearchEngine();
	}
	

}
