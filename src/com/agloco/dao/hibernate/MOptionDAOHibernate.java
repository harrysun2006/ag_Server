package com.agloco.dao.hibernate;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.agloco.dao.MOptionDAO;
import com.agloco.model.VBMOption;

/**
 * @author author E-mail:zhaon12@gmail.com
 * @version createDate:Apr 5, 2007 3:25:07 PM content
 */
public class MOptionDAOHibernate extends HibernateDaoSupport implements
		MOptionDAO {

	public void createMOption(VBMOption mOption) {
		getHibernateTemplate().save(mOption);
	}

	public List listMOpton(Long memberId) {
		String selectSQl = "from VBMOption vbm where vbm.memberId = ?";
		return getHibernateTemplate().find(selectSQl, memberId);
	}

	public void updateMOption(VBMOption mOption) {
		getHibernateTemplate().update(mOption);
	}

	public VBMOption getMOption(Long memberId, String name) {
		String selectSQL = "from VBMOption vbm where vbm.memberId = ? and vbm.name = ? ";
		List list = getHibernateTemplate().find(selectSQL,
				new Object[] { memberId, name });
		if (list == null || list.size() < 1) {
			return null;
		}
		return (VBMOption) list.iterator().next();
	}

}
