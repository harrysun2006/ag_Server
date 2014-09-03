
package com.agloco.web.service.model;


/**
 * 
 * @author Erick Kong
 * @see AffiliateRes.java
 * @createDate: 2007-4-12
 * @version 1.0
 */

public class AffiliateRes
{
	private int result;
	private AffiliateList affiliates;
	private AffiliateDetailList afDetails;
	public AffiliateDetailList getAfDetails()
	{
		return this.afDetails;
	}
	public void setAfDetails(AffiliateDetailList afDetails)
	{
		this.afDetails = afDetails;
	}
	public AffiliateList getAffiliates()
	{
		return this.affiliates;
	}
	public void setAffiliates(AffiliateList affiliates)
	{
		this.affiliates = affiliates;
	}
	public int getResult()
	{
		return this.result;
	}
	public void setResult(int result)
	{
		this.result = result;
	}
}

