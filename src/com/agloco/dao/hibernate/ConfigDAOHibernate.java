package com.agloco.dao.hibernate;

import java.util.List;

import org.hibernate.Query;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.agloco.dao.ConfigDAO;
import com.agloco.model.VBConfig;

/**
 * @author author E-mail:zhaon12@gmail.com
 * @version 1.0
 * @createDate createDate:2007-8-8 下午01:21:31
 * @content
 */
public class ConfigDAOHibernate extends HibernateDaoSupport implements
		ConfigDAO {

	public VBConfig getVBConfig(String name, String value) {

		List list = getHibernateTemplate().find(
				"from VBConfig vc where vc.active = 1 and vc.name = ?", name);
		if (list == null || list.size() == 0) {
			return null;
		}

		VBConfig config = (VBConfig) list.iterator().next();
		if (!config.getValue().equals(value)) {
			config.setValue(value);
			getHibernateTemplate().update(config);
		}

		return config;
	}
	
	public VBConfig getVBConfig(String name){
		List list = getHibernateTemplate().find(
				"from VBConfig vc where vc.active = 1 and vc.name = ?", name);
		if (list == null || list.size() == 0) {
			return null;
		}
		return (VBConfig)list.iterator().next();
	}
	
	public void updateVBConfig(String name, String value) {
		StringBuffer selectSQL =new StringBuffer( "select * from VB_Config where name = '");
		selectSQL.append(name).append("' for update");
		Query q = getSession().createSQLQuery(selectSQL.toString()).addEntity(VBConfig.class);
		
		List list = q.list();
		if (list == null || list.size() == 0) {
			return;
		}
		VBConfig config = (VBConfig) list.iterator().next();
		config.setValue(value);
		getHibernateTemplate().update(config);
	}
}
