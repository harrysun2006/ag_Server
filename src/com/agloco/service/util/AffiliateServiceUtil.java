
package com.agloco.service.util;

import java.util.Calendar;
import java.util.List;

import org.springframework.context.ApplicationContext;

import com.agloco.model.VBAFDetail;
import com.agloco.model.VBAffiliate;
import com.agloco.service.AffiliateService;
import com.agloco.spring.SpringUtil;

/**
 * 
 * @author Erick Kong
 * @see AffiliateServiceUtil.java
 * @createDate: 2007-4-12
 * @version 1.0
 */

public class AffiliateServiceUtil
{
	private AffiliateService affiliateService;


	//Affiliate
	public static List getNewAffiliates(Calendar afterDate)
	{
		return getAffiliateService().getNewAffiliates(afterDate);
	}
	public static List getAllAffiliates()
	{
		return getAffiliateService().getAllAffiliates();
	}
	public static VBAffiliate getAffiliate(Long affiliateId)
	{
		return getAffiliateService().getAffiliate(affiliateId);
	}
	public static void createAffiliate(VBAffiliate vbaffiliate)
	{
		getAffiliateService().createAffiliate(vbaffiliate);
	}
	public static void updateAffiliate(VBAffiliate vbaffiliate)
	{
		getAffiliateService().updateAffiliate(vbaffiliate);
	}
	public static void deleteAffiliate(VBAffiliate vbaffiliate)
	{
		getAffiliateService().deleteAffiliate(vbaffiliate);
	}
	

	//Affiliate Detail
	public static List getAFDetails()
	{
		return getAffiliateService().getAFDetails();
	}
	public static List getNewAFDetails(Calendar afterDate)
	{
		return getAffiliateService().getNewAFDetails(afterDate);
	}
	public static List getAFDetails(Long affiliateId)
	{
		return getAffiliateService().getAFDetails(affiliateId);
	}
	public static List getNewAFDetails(Long affiliateId,Calendar afterDate)
	{
		return getAffiliateService().getNewAFDetails(affiliateId, afterDate);
	}
	public static VBAFDetail getAFDetail(Long afDetailId)
	{
		return getAffiliateService().getAFDetail(afDetailId);
	}
	public static void createAFDetail(VBAFDetail vbAFDetail)
	{
		getAffiliateService().createAFDetail(vbAFDetail);
	}
	public static void updateAFDetail(VBAFDetail vbAFDetail)
	{
		getAffiliateService().updateAFDetail(vbAFDetail);
	}
	public static void deleteAFDetail(VBAFDetail vbAFDetail)
	{
		getAffiliateService().deleteAFDetail(vbAFDetail);
	}

	

	private static AffiliateService getAffiliateService()
	{
		ApplicationContext ctx = SpringUtil.getContext();
		AffiliateServiceUtil util = (AffiliateServiceUtil)ctx.getBean(AffiliateServiceUtil.class.getName());
		return util.affiliateService;
	}
	public void setAffiliateService(AffiliateService affiliateService)
	{
		this.affiliateService = affiliateService;
	}
	
	
}

