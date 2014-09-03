package com.agloco.dao.util;

import org.springframework.context.ApplicationContext;

import com.agloco.dao.ViewbarDAO;
import com.agloco.model.VBViewbar;
import com.agloco.spring.SpringUtil;

public class ViewbarDAOUtil {

	private ViewbarDAO viewbarDAO;

	public static void updateViewbar(VBViewbar viewbar) {
		getViewbarDAO().updateViewbar(viewbar);
	}

	public static VBViewbar getNewestViewbar(String viewbarShortId) {
		return getViewbarDAO().getLatestViewbar(viewbarShortId);
	}

	public static VBViewbar getViewbar(String viewbarId) {
		return getViewbarDAO().getViewbar(viewbarId);
	}

	public static VBViewbar getViewbar(String viewbarId, String osVersion) {
		return getViewbarDAO().getViewbar(viewbarId, osVersion);
	}

	public static void createViewbar(VBViewbar viewbar) {
		getViewbarDAO().createViewbar(viewbar);
	}

	public static ViewbarDAO getViewbarDAO() {
		ApplicationContext ctx = SpringUtil.getContext();
		ViewbarDAOUtil util = (ViewbarDAOUtil) ctx.getBean(ViewbarDAOUtil.class
				.getName());
		return util.viewbarDAO;
	}

	public void setViewbarDAO(ViewbarDAO viewbarDAO) {
		this.viewbarDAO = viewbarDAO;
	}
}
