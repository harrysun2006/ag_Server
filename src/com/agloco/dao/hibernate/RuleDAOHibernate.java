package com.agloco.dao.hibernate;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.agloco.dao.RuleDAO;
import com.agloco.model.VBRule;

public class RuleDAOHibernate extends HibernateDaoSupport implements RuleDAO {

	public List listRules(String category, int active) {
		StringBuffer selectSQL = new StringBuffer(
				"from VBRule vbr where vbr.category = ? and vbr.active = ?");
		return getHibernateTemplate().find(selectSQL.toString(),
				new Object[] { category, Integer.valueOf(active) });
	}

	public void createRule(VBRule rule) {
		getHibernateTemplate().save(rule);
	}

	public VBRule getRule(Long ruleId) {
		String selectSQL = "from VBRule vbr where vbr.ruleId = ?";
		List list = getHibernateTemplate().find(selectSQL, ruleId);
		if (list == null || list.size() < 1) {
			return null;
		}
		return (VBRule) list.iterator().next();
	}

	public VBRule getRule(String name) {
		String selectSQL = "from VBRule vbr where vbr.name = ?";
		List list = getHibernateTemplate().find(selectSQL, name);
		if (list == null || list.size() < 1) {
			return null;
		}
		return (VBRule) list.iterator().next();
	}

	public void updateRule(VBRule rule) {
		getHibernateTemplate().update(rule);
	}
}
