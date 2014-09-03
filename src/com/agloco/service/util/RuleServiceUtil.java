package com.agloco.service.util;

import java.util.List;

import org.springframework.context.ApplicationContext;

import com.agloco.model.VBRule;
import com.agloco.service.RuleService;
import com.agloco.spring.SpringUtil;

public class RuleServiceUtil {

	private RuleService ruleService;

	public void createRule(VBRule rule) {
		getRuleService().createRule(rule);
	}

	public void updateRule(VBRule rule) {
		getRuleService().updateRule(rule);
	}

	public VBRule getRule(Long ruleId) {
		return getRuleService().getRule(ruleId);
	}

	public VBRule getRule(String name) {
		return getRuleService().getRule(name);
	}

	public static List listRules(String category, int active) {
		return getRuleService().listRules(category, active);
	}

	public static RuleService getRuleService() {
		ApplicationContext ctx = SpringUtil.getContext();
		RuleServiceUtil util = (RuleServiceUtil) ctx
				.getBean(RuleServiceUtil.class.getName());
		return util.ruleService;
	}

	public void setRuleService(RuleService ruleService) {
		this.ruleService = ruleService;
	}

}
