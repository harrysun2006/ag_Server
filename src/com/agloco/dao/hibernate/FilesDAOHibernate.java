package com.agloco.dao.hibernate;

import java.util.List;

import com.agloco.dao.FilesDAO;
import com.agloco.model.VBFiles;

public class FilesDAOHibernate extends
		org.springframework.orm.hibernate3.support.HibernateDaoSupport
		implements FilesDAO {

	public List listFiles(String viewbarId) {
		return listFiles(viewbarId, null);
	}

	public List listFiles(String viewbarId, String osVersion) {
		StringBuffer selectSQL = new StringBuffer(
				" from VBFiles vbf where vbf.viewbarId = '");
		selectSQL.append(viewbarId).append("'");
		if (osVersion != null) {
			selectSQL.append(" and vbf.osVersion = '");
			selectSQL.append(osVersion).append("'");
		}
		return getHibernateTemplate().find(selectSQL.toString());
	}

	public void createFile(VBFiles files) {
		getHibernateTemplate().save(files);
	}

	public void deleteFile(String viewbarId) {
		StringBuffer deleteSQL = new StringBuffer(
				"delete from VB_Files where viewbarId = '");
		deleteSQL.append(viewbarId).append("'");
		getSession().createSQLQuery(deleteSQL.toString()).executeUpdate();
	}

	public void updateFile(VBFiles files) {
		getHibernateTemplate().update(files);
	}

}
