package com.agloco.web.util;

import java.security.NoSuchAlgorithmException;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.agloco.model.VBFavorite;
import com.agloco.service.util.FavoriteServiceUtil;
import com.agloco.util.ClusterPool;
import com.agloco.util.StringUtil;
import com.opensymphony.oscache.base.NeedsRefreshException;
import com.opensymphony.oscache.general.GeneralCacheAdministrator;
/**
 *  
 * @author terry_zhao
 * @version 1.0
 */
public class FavoriteHashUtil {

	private final static Log log = LogFactory.getLog(FavoriteHashUtil.class);
	private static GeneralCacheAdministrator _cache = ClusterPool.getCache();
	public final static String CACHE_ALL_FAVORITE_HASH = "all.favorte.hash";
	public static final String GROUP_NAME = FavoriteHashUtil.class.getName();
	public static final String[] GROUP_NAME_ARRAY = new String[] {GROUP_NAME};
	
	public static String getAllHashCode(){
		
		String key = encodeKey(CACHE_ALL_FAVORITE_HASH);
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
	
	public static void clearFavoriteHashPool() {
		_cache.flushGroup(GROUP_NAME);
	}

	public static void clearFavoriteHashPool(String key) {
		key = encodeKey(key);
		_cache.flushEntry(key);
	}	
	
	private static String encodeKey(String key) {
		return GROUP_NAME + StringUtil.POUND + key;
	}
	
	
	public static String regetAllHashCode(){
		String hashCode = null;
		List list = listAllFavorite();
		if(list == null || list.size() < 1){
			return hashCode;	
		}
		try {
			StringBuffer sb = new StringBuffer();
			for(Iterator it = list.iterator(); it.hasNext();){
				VBFavorite fav = (VBFavorite)it.next();
				sb.append(fav.getFavoriteId())
				  .append(fav.getName())
				  .append(fav.getUrl());
//				  .append(fav.getCategory()); //charry still not process this field
			}
			hashCode = MD5HashUtil.hashCode(sb.toString());
		} catch (NoSuchAlgorithmException e) {
			if(log.isErrorEnabled()){
				log.error("md5 hash error", e);
			}
		}
		return hashCode;
	}
	private static List listAllFavorite(){
		return FavoriteServiceUtil.listFavorite(VBFavorite.ACTIVE);
	}
}
