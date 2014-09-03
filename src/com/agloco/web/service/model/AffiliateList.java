package com.agloco.web.service.model;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Erick Kong
 * @see AffiliateList.java
 * @createDate: 2007-4-12
 * @version 1.0
 */

public class AffiliateList
{
	private List affiliates;

	public AffiliateList()
	{
		this.affiliates = new ArrayList();
	}

	public List getAffiliates()
	{
		return this.affiliates;
	}

	public void addAffiliate(List affiliate)
	{
		this.affiliates.addAll(affiliate);
	}
}
