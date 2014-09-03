package com.agloco.service.util;

import java.util.List;

import org.springframework.context.ApplicationContext;

import com.agloco.model.VBFavorite;
import com.agloco.service.FavoriteService;
import com.agloco.spring.SpringUtil;
import com.agloco.util.ClusterPool;
import com.agloco.util.StringUtil;
import com.opensymphony.oscache.base.NeedsRefreshException;
import com.opensymphony.oscache.general.GeneralCacheAdministrator;

/**
 * @author author E-mail:zhaon12@gmail.com
 * @version createDate:Apr 5, 2007 10:19:52 AM
 * content
 */
public class FavoriteServiceUtil {

	private static GeneralCacheAdministrator _cache = ClusterPool.getCache();
	public final static String CACHE_FAVORITE_LIST = "favorite.list";
	public static final String GROUP_NAME = SearchServiceUtil.class.getName();
	public static final String[] GROUP_NAME_ARRAY = new String[] {GROUP_NAME};
	
	private FavoriteService favoriteService;

	public static List listFavorite(int active){
		String key = encodeKey(CACHE_FAVORITE_LIST + active);
		List list = null;
		try {
			list = (List)_cache.getFromCache(key);
		} catch (NeedsRefreshException e) {
			list = listFavoriteFromDB(active);
			_cache.putInCache(key, list, GROUP_NAME_ARRAY);
		}
		finally{
			if(list == null){
				_cache.cancelUpdate(key);
			}
		}
		return list;
	}
	
	public static List listFavoriteFromDB(int active){
		return getFavoriteService().listFavorite(active);
	}
	public static void createFavorite(VBFavorite favorite) {
		getFavoriteService().createFavorite(favorite);
	}

	public static VBFavorite getFavorite(Long favoriteId) {
		return getFavoriteService().getFavorite(favoriteId);
	}

	public static VBFavorite getFavorite(String url) {
		return getFavoriteService().getFavorite(url);
	}

	public static void updateFavorite(VBFavorite favorite) {
		getFavoriteService().updateFavorite(favorite);
	}
	
	public static void clearFavoriteServiceUtilPool() {
		_cache.flushGroup(GROUP_NAME);
	}

	public static void clearFavoriteServiceUtilPool(String key) {
		key = encodeKey(key);
		_cache.flushEntry(key);
	}	
	
	private static String encodeKey(String key) {
		return GROUP_NAME + StringUtil.POUND + key;
	}
	
	
	public static  FavoriteService getFavoriteService() {
		ApplicationContext ctx = SpringUtil.getContext();
		FavoriteServiceUtil util = (FavoriteServiceUtil) ctx
				.getBean(FavoriteServiceUtil.class.getName());
		return util.favoriteService;
	}

	public void setFavoriteService(FavoriteService favoriteService) {
		this.favoriteService = favoriteService;
	}
	
	
	
}
