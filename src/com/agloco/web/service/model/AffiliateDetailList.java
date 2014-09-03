package com.agloco.web.service.model;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Erick Kong
 * @see AffiliateDetailList.java
 * @createDate: 2007-4-12
 * @version 1.0
 */

public class AffiliateDetailList
{
	private List afDetails;

	public AffiliateDetailList()
	{
		this.afDetails = new ArrayList();
	}

	public List getAfDetails()
	{
		return this.afDetails;
	}

	public void addAfDetail(List afDetail)
	{
		this.afDetails.addAll(afDetail);
	}

}
