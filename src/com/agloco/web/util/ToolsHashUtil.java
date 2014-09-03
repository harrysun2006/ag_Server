package com.agloco.web.util;

import java.security.NoSuchAlgorithmException;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.agloco.model.VBTools;
import com.agloco.service.util.ToolsServiceUtil;
import com.agloco.util.ClusterPool;
import com.agloco.util.StringUtil;
import com.opensymphony.oscache.base.NeedsRefreshException;
import com.opensymphony.oscache.general.GeneralCacheAdministrator;
/**
 * 
 * @author terry_zhao
 * @date Apr 26, 2007
 */
public class ToolsHashUtil {

	private final static Log log                           = LogFactory.getLog(ToolsHashUtil.class);
	private static GeneralCacheAdministrator _cache        = ClusterPool.getCache();
	public final static String   CACHE_ALL_TOOLS_HASH      = "all.tools.hash";
	public final static String   CACHE_TOOLS_BY_OS_HASH    = "all.tools.hash";
	public final static String   GROUP_NAME                = ToolsHashUtil.class.getName();
	public final static String[] GROUP_NAME_ARRAY          = new String[] {GROUP_NAME};
	
	public static String getAllHashCode(){
		
		String key = encodeKey(CACHE_ALL_TOOLS_HASH);
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
	

	
	
	private static String regetAllHashCode(){
		String hashCode = null;
		List list = listAllTools();
		if(list == null || list.size() < 1){
			return hashCode;	
		}
		try {
			StringBuffer sb = new StringBuffer();
			for(Iterator it = list.iterator(); it.hasNext();){
				VBTools tool = (VBTools)it.next();
				sb.append(tool.getId())
				  .append(tool.getName())
				  .append(tool.getVersion())
				  .append(tool.getOperatingSystem())
				  .append(tool.getDescription());
			}
			hashCode = MD5HashUtil.hashCode(sb.toString());
		} catch (NoSuchAlgorithmException e) {
			if(log.isErrorEnabled()){
				log.error("md5 hash error", e);
			}
		}
		return hashCode;
	}
	
	public static String getToolsHashCodeByOS(String os){
		
		String key = encodeKey(CACHE_TOOLS_BY_OS_HASH + os);
		String hash = null;
		try {
			hash = (String)_cache.getFromCache(key);
		} catch (NeedsRefreshException e) {
			hash = regetToolsHashCodeByOS(os);
			_cache.putInCache(key, hash, GROUP_NAME_ARRAY);
		}
		finally{
			if(hash == null){
				_cache.cancelUpdate(key);
			}
		}
		return hash;
	}
	
	private static String regetToolsHashCodeByOS(String os){
		String hashCode = null;
		List list = listToolsByOS(os);
		if(list == null || list.size() < 1){
			return hashCode;	
		}
		try {
			StringBuffer sb = new StringBuffer();
			for(Iterator it = list.iterator(); it.hasNext();){
				VBTools tool = (VBTools)it.next();
				sb.append(tool.getId())
				  .append(tool.getName())
				  .append(tool.getVersion())
				  .append(tool.getOperatingSystem())
				  .append(tool.getDescription());
			}
			hashCode = MD5HashUtil.hashCode(sb.toString());
		} catch (NoSuchAlgorithmException e) {
			if(log.isErrorEnabled()){
				log.error("md5 hash error", e);
			}
		}
		return hashCode;
	}
	
	private static List listAllTools(){
		return ToolsServiceUtil.listTools();
	}
	
	private static List listToolsByOS(String os){
		return ToolsServiceUtil.listToolsByOS(os);
	}
	
	public static void clearToolsHashPool() {
		_cache.flushGroup(GROUP_NAME);
	}

	public static void clearToolHashPool(String key) {
		key = encodeKey(key);
		_cache.flushEntry(key);
	}	
	
	private static String encodeKey(String key) {
		return GROUP_NAME + StringUtil.POUND + key;
	}
	
}
