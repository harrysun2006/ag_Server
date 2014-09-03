package com.agloco.dao.util;

import java.util.List;

import org.springframework.context.ApplicationContext;

import com.agloco.dao.RuleDAO;
import com.agloco.model.VBRule;
import com.agloco.spring.SpringUtil;

public class RuleDAOUtil {

	private RuleDAO ruleDAO;

	public static void createRule(VBRule rule) {
		getRuleDAO().createRule(rule);
	}

	public static void updateRule(VBRule rule) {
		getRuleDAO().updateRule(rule);
	}

	public static VBRule getRule(Long ruleId) {
		return getRuleDAO().getRule(ruleId);
	}

	public static VBRule getRule(String name) {
		return getRuleDAO().getRule(name);
	}

	public static List listRules(String category, int active) {
		return getRuleDAO().listRules(category, active);
	}

	public static RuleDAO getRuleDAO() {
		ApplicationContext ctx = SpringUtil.getContext();
		RuleDAOUtil util = (RuleDAOUtil) ctx.getBean(RuleDAOUtil.class
				.getName());
		return util.ruleDAO;
	}

	public void setRuleDAO(RuleDAO ruleDAO) {
		this.ruleDAO = ruleDAO;
	}

}
