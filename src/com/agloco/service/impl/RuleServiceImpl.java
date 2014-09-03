package com.agloco.service.impl;

import java.util.List;

import com.agloco.dao.util.RuleDAOUtil;
import com.agloco.model.VBRule;
import com.agloco.service.RuleService;

public class RuleServiceImpl implements RuleService {

	public List listRules(String category, int active) {
		return RuleDAOUtil.listRules(category, active);
	}

	public void createRule(VBRule rule) {
		RuleDAOUtil.createRule(rule);
	}

	public VBRule getRule(Long ruleId) {
		return  RuleDAOUtil.getRule(ruleId);
	}

	public VBRule getRule(String name) {
		return RuleDAOUtil.getRule(name);
	}

	public void updateRule(VBRule rule) {
		RuleDAOUtil.updateRule(rule);
	}

}
