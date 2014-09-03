package com.agloco.dao.hibernate;

import java.util.List;

import org.hibernate.Query;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.agloco.dao.ViewbarDAO;
import com.agloco.model.VBViewbar;

public class ViewbarDAOHibernate extends HibernateDaoSupport implements
		ViewbarDAO {

	public VBViewbar getLatestViewbar(String viewbarShortId) {

		StringBuffer selectSQL = new StringBuffer();
		selectSQL.append("Select * from `VB_Viewbar` where viewbarId like '")
				.append(viewbarShortId).append("%' order by viewbarId desc");
//		selectSQL
//				.append("from VBViewbar vbv where vbv.viewbarId like ? order by vbv.viewbarId desc");

		Query q = getSession().createSQLQuery(selectSQL.toString()).addEntity(VBViewbar.class);
		List list = q.list();
//		List list = getHibernateTemplate().find(selectSQL.toString(),
//				viewbarShortId + "%");

		if (list == null || list.size() == 0) {
			return null;
		}
		return (VBViewbar) list.get(0);
	}

	public VBViewbar getViewbar(String viewbarId) {
		return getViewbar(viewbarId, null);
	}

	public VBViewbar getViewbar(String viewbarId, String osVersion) {

		List list = null;
		StringBuffer selectSQl = new StringBuffer(" from VBViewbar vbv where ");
		if (viewbarId == null && osVersion == null) {
			return null;
		} else if (viewbarId != null && osVersion == null) {
			selectSQl.append(" vbv.viewbarId = '");
			selectSQl.append(viewbarId).append("'");
		} else if (viewbarId == null && osVersion != null) {
			selectSQl.append(" vbv.osVersion = '");
			selectSQl.append(osVersion).append("'");
		} else {
			selectSQl.append(" vbv.viewbarId = '");
			selectSQl.append(viewbarId);
			selectSQl.append("' and vbv.osVersion = '");
			selectSQl.append(osVersion).append("'");
		}
		list = getHibernateTemplate().find(selectSQl.toString());
		if (list == null || list.size()<1) {
			return null;
		}
		return (VBViewbar) list.get(0);
	}

	public void updateViewbar(VBViewbar viewbar) {
		getHibernateTemplate().update(viewbar);
	}

	public void createViewbar(VBViewbar viewbar) {
		getHibernateTemplate().save(viewbar);
	}
}
