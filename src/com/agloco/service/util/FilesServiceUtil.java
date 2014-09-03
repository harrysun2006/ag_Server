package com.agloco.service.util;

import java.util.List;

import org.springframework.context.ApplicationContext;

import com.agloco.model.VBFiles;
import com.agloco.service.FilesService;
import com.agloco.spring.SpringUtil;
import com.agloco.util.ClusterPool;
import com.opensymphony.oscache.base.NeedsRefreshException;
import com.opensymphony.oscache.general.GeneralCacheAdministrator;

public class FilesServiceUtil {
	private static GeneralCacheAdministrator _cache = ClusterPool.getCache();
	public final static String CACHE_LATEST_FILES = "latest.files.";
	public static final String GROUP_NAME = FilesServiceUtil.class.getName();
	public static final String[] GROUP_NAME_ARRAY = new String[] {GROUP_NAME};
	private FilesService filesService;

	public static void createFile(VBFiles files) {
		getFilesService().createFile(files);
	}

	public static void updateFile(VBFiles files) {
		getFilesService().updateFile(files);
	}

	public void deleteFile(String viewbarId) {
		getFilesService().deleteFile(viewbarId);
	}

	public static List listFiles(String viewbarId) {
		List result = null;
		try {
			result = (List)_cache.getFromCache(CACHE_LATEST_FILES + viewbarId);
		} catch (NeedsRefreshException e) {
			result = getFilesService().listFiles(viewbarId);
			_cache.putInCache(CACHE_LATEST_FILES + viewbarId, result, GROUP_NAME_ARRAY);
		}
		finally{
			if(result == null){
				_cache.cancelUpdate(CACHE_LATEST_FILES + viewbarId);
			}
		}
		return result;
	}

	public static List listFiles(String viewbarId, String osVersion) {
		return getFilesService().listFiles(viewbarId, osVersion);
	}

	public static FilesService getFilesService() {
		ApplicationContext ctx = SpringUtil.getContext();
		FilesServiceUtil util = (FilesServiceUtil) ctx
				.getBean(FilesServiceUtil.class.getName());
		return util.filesService;
	}

	public void setFilesService(FilesService filesService) {
		this.filesService = filesService;
	}

}
