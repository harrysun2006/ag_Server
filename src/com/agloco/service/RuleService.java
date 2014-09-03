package com.agloco.service;

import java.util.List;

import com.agloco.model.VBRule;

public interface RuleService extends BaseService {

	public void createRule(VBRule rule);
	public void updateRule(VBRule rule);
	public VBRule getRule(Long ruleId);
	public VBRule getRule(String name);
	public List listRules(String category, int active);
}
