package com.agloco.service.util;

import java.util.List;

import org.springframework.context.ApplicationContext;

import com.agloco.model.VBTools;
import com.agloco.model.VBToolsDTrack;
import com.agloco.model.VBToolsPathes;
import com.agloco.service.ToolsService;
import com.agloco.spring.SpringUtil;
import com.agloco.util.ClusterPool;
import com.agloco.util.StringUtil;
import com.opensymphony.oscache.base.NeedsRefreshException;
import com.opensymphony.oscache.general.GeneralCacheAdministrator;

/**
 * 
 * @author terry_zhao
 * @date Apr 23, 2007
 */
public class ToolsServiceUtil {

	private static GeneralCacheAdministrator _cache      = ClusterPool.getCache();
	public final static String   CACHE_TOOLS_LIST        = "tools.list";
	public final static String   CACHE_TOOLS_LIST_BY_OS  = "tools.list.by.os";
	public final static String   CACHE_TOOLS_PATHES_LIST = "tools.pathes.list";
	public final static String   GROUP_NAME              = ToolsServiceUtil.class.getName();
	public final static String[] GROUP_NAME_ARRAY        = new String[] {GROUP_NAME};
	
	//tools
	public static void createTool(VBTools tool) {
		getToolsService().createTool(tool);
	}

	public static void deleteTool(VBTools tool) {
		getToolsService().deleteTool(tool);
	}
	
	public static VBTools getTool(Long id) {
		return getToolsService().getTool(id);
	}
	
	@SuppressWarnings("unchecked")
	public static List<VBTools> listTools() {
		String key = encodeKey(CACHE_TOOLS_LIST);
		List list = null;
		try {
			list = (List)_cache.getFromCache(key);
		} catch (NeedsRefreshException e) {
			list = listToolsFromDB();
			_cache.putInCache(key, list, GROUP_NAME_ARRAY);
		}
		finally{
			if(list == null){
				_cache.cancelUpdate(key);
			}
		}
		return list;
	}
	
	public static List<VBTools> listToolsFromDB() {
		return getToolsService().listTools();
	}

	@SuppressWarnings("unchecked")
	public static List<VBTools> listToolsByOS(String os) {
		String key = encodeKey(CACHE_TOOLS_LIST_BY_OS + os);
		List list = null;
		try {
			list = (List)_cache.getFromCache(key);
		} catch (NeedsRefreshException e) {
			list = listToolsByOSFromDB(os);
			_cache.putInCache(key, list, GROUP_NAME_ARRAY);
		}
		finally{
			if(list == null){
				_cache.cancelUpdate(key);
			}
		}
		return list;
	}
	
	private static List<VBTools> listToolsByOSFromDB(String os) {
		return getToolsService().listToolsByOS(os);
	}
	
	public static void updateTool(VBTools tool) {
		getToolsService().updateTool(tool);
	}
	
	//tools download track
	public static void createToolDownloadTrack(VBToolsDTrack track) {
		getToolsService().createToolDownloadTrack(track);
	}

	public static void deleteToolDownloadTrack(VBToolsDTrack track) {
		getToolsService().deleteToolDownloadTrack(track);
	}

	public static VBToolsDTrack getToolDownloadTrack(Long trackId) {
		return getToolsService().getToolDownloadTrack(trackId);
	}

	public static List<VBToolsDTrack> listToolsDownloadTrack(Long memberId) {
		return getToolsService().listToolsDownloadTrack(memberId);
	}
	public static void updateToolDownloadTrack(VBToolsDTrack track) {
		getToolsService().updateToolDownloadTrack(track);
	}
	
	//tools pathes
	public static void createToolsPath(VBToolsPathes tPath) {
		getToolsService().createToolsPath(tPath);
	}
	public static void deleteToolsPath(VBToolsPathes tPath) {
		getToolsService().deleteToolsPath(tPath);
	}
	
	@SuppressWarnings("unchecked")
	public static List<VBToolsPathes> listToolsPathes(Long toolId) {
		String key = encodeKey(CACHE_TOOLS_PATHES_LIST + toolId);
		List list = null;
		try {
			list = (List)_cache.getFromCache(key);
		} catch (NeedsRefreshException e) {
			list = listToolsPathesFromDB(toolId);
			_cache.putInCache(key, list, GROUP_NAME_ARRAY);
		}
		finally{
			if(list == null){
				_cache.cancelUpdate(key);
			}
		}
		return list;
	}
	
	public static List<VBToolsPathes> listToolsPathesFromDB(Long toolId) {
		return getToolsService().listToolsPathes(toolId);
	}
	public static void updateToolsPath(VBToolsPathes tPath) {
		getToolsService().updateToolsPath(tPath);
	}
	
	public static void clearToolsServiceUtilPool() {
		_cache.flushGroup(GROUP_NAME);
	}

	public static void clearToolsServiceUtilPool(String key) {
		key = encodeKey(key);
		_cache.flushEntry(key);
	}	
	
	private static String encodeKey(String key) {
		return GROUP_NAME + StringUtil.POUND + key;
	}
	
	private static ToolsService getToolsService(){
		ApplicationContext ctx = SpringUtil.getContext();
		ToolsServiceUtil util = (ToolsServiceUtil)ctx.getBean(ToolsServiceUtil.class.getName());
		return util.toolsService;
	}
	public void setToolsService(ToolsService toolsService) {
		this.toolsService = toolsService;
	}
	private ToolsService toolsService;
}
