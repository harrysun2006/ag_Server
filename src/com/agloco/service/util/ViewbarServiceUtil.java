package com.agloco.service.util;

import java.util.Date;

import org.springframework.context.ApplicationContext;

import com.agloco.model.VBViewbar;
import com.agloco.service.ViewbarService;
import com.agloco.spring.SpringUtil;
import com.agloco.util.ClusterPool;
import com.agloco.web.service.model.ViewbarCheckRes;
import com.opensymphony.oscache.base.NeedsRefreshException;
import com.opensymphony.oscache.general.GeneralCacheAdministrator;

public class ViewbarServiceUtil {

	private static GeneralCacheAdministrator _cache = ClusterPool.getCache();

	public final static String CACHE_LATEST_VIEWBAR = "latest.viewbar.";

	public static final String GROUP_NAME = ViewbarServiceUtil.class.getName();

	public static final String[] GROUP_NAME_ARRAY = new String[] { GROUP_NAME };

	private ViewbarService viewbarService;

	public static void createViewbar(VBViewbar viewbar) {
		getViewbarService().createViewbar(viewbar);
	}

	public static void updateViewbar(VBViewbar viewbar) {
		getViewbarService().updateViewbar(viewbar);
	}

	public static VBViewbar getLatestViewbar(String viewbarShortId) {
		return getViewbarService().getLatestViewbar(viewbarShortId);
	}

	public static VBViewbar getViewbar(String viewbarId) {
		return getViewbarService().getViewbar(viewbarId);
	}

	public static VBViewbar getViewbar(String viewbarId, String osVersion) {
		return getViewbarService().getViewbar(viewbarId, osVersion);
	}

	public static boolean forceUpdate(String viewbarId) {
		if (viewbarId == null){
			return true;
		}
		ViewbarCheckRes temp = checkViewbar(viewbarId);
		if (temp == null) {
			return true;
		}
		return temp.getForceUpdate();
	}
	public static ViewbarCheckRes checkViewbar(String viewbarId) {	
		String tempString = viewbarId.substring(0,
				viewbarId.indexOf(".") + 1);

		ViewbarCheckRes viewbarCheckRes = new ViewbarCheckRes();
		VBViewbar latestViewbar = null;
		VBViewbar currentViewbar = getViewbarService().getViewbar(viewbarId);

		if (currentViewbar == null) {
			return null;
		} else {
			try {
				latestViewbar = (VBViewbar) _cache
						.getFromCache(CACHE_LATEST_VIEWBAR + tempString);
			} catch (NeedsRefreshException e) {
				latestViewbar = getLatestViewbar(tempString);
				_cache.putInCache(CACHE_LATEST_VIEWBAR + tempString,
						latestViewbar, GROUP_NAME_ARRAY);
			} finally {
				if (latestViewbar == null) {
					_cache.cancelUpdate(CACHE_LATEST_VIEWBAR + tempString);
				}
			}
			if (latestViewbar == null){
				return null;
			}
			viewbarCheckRes.setLatestViewbarId(latestViewbar.getViewbarId());
			if (currentViewbar.getExpireDate() == null
					|| currentViewbar.getExpireDate().after(new Date())) {
				viewbarCheckRes.setForceUpdate(false);
			} else {
				viewbarCheckRes.setForceUpdate(true);
			}
		}

		return viewbarCheckRes;
	}

	public static ViewbarService getViewbarService() {
		ApplicationContext ctx = SpringUtil.getContext();
		ViewbarServiceUtil util = (ViewbarServiceUtil) ctx
				.getBean(ViewbarServiceUtil.class.getName());
		return util.viewbarService;
	}

	public void setViewbarService(ViewbarService viewbarService) {
		this.viewbarService = viewbarService;
	}
}
