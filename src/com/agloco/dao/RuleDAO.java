package com.agloco.dao;

import java.util.List;

import com.agloco.model.VBRule;

public interface RuleDAO {

	public void createRule(VBRule rule);
	public void updateRule(VBRule rule);
	public VBRule getRule(Long ruleId);
	public VBRule getRule(String name);
	public List listRules(String category, int active);
}
