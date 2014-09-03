
package com.agloco.dao.util;

import java.util.Calendar;
import java.util.List;

import org.springframework.context.ApplicationContext;

import com.agloco.dao.AffiliateDAO;
import com.agloco.model.VBAFDetail;
import com.agloco.model.VBAffiliate;
import com.agloco.spring.SpringUtil;

/**
 * 
 * @author Erick Kong
 * @see AffiliateDAOUtil.java
 * @createDate: 2007-4-12
 * @version 1.0
 */

public class AffiliateDAOUtil
{
	private AffiliateDAO affiliateDAO;

	private static AffiliateDAO getAffiliateDAO()
	{
		ApplicationContext ctx = SpringUtil.getContext();
		AffiliateDAOUtil affiliateUtil = (AffiliateDAOUtil)ctx.getBean(AffiliateDAOUtil.class.getName());
		
		return affiliateUtil.affiliateDAO;
	}
	public void setAffiliateDAO(AffiliateDAO affiliateDAO)
	{
		this.affiliateDAO = affiliateDAO;
	}
	//Affiliate
	public static List getNewAffiliates(Calendar afterDate)
	{
		return getAffiliateDAO().getNewAffiliates(afterDate);
	}
	public static List getAllAffiliates()
	{
		return getAffiliateDAO().getAllAffiliates();
	}
	public static VBAffiliate getAffiliate(Long affiliateId)
	{
		return getAffiliateDAO().getAffiliate(affiliateId);
	}
	public static void createAffiliate(VBAffiliate vbaffiliate)
	{
		getAffiliateDAO().createAffiliate(vbaffiliate);
	}
	public static void updateAffiliate(VBAffiliate vbaffiliate)
	{
		getAffiliateDAO().updateAffiliate(vbaffiliate);
	}
	public static void deleteAffiliate(VBAffiliate vbaffiliate)
	{
		getAffiliateDAO().deleteAffiliate(vbaffiliate);
	}
	

	//Affiliate Detail
	public static List getAFDetails()
	{
		return getAffiliateDAO().getAFDetails();
	}
	public static List getNewAFDetails(Calendar afterDate)
	{
		return getAffiliateDAO().getNewAFDetails(afterDate);
	}
	public static List getAFDetails(Long affiliateId)
	{
		return getAffiliateDAO().getAFDetails(affiliateId);
	}
	public static List getNewAFDetails(Long affiliateId,Calendar afterDate)
	{
		return getAffiliateDAO().getNewAFDetails(affiliateId, afterDate);
	}
	public static VBAFDetail getAFDetail(Long afDetailId)
	{
		return getAffiliateDAO().getAFDetail(afDetailId);
	}
	public static void createAFDetail(VBAFDetail vbAFDetail)
	{
		getAffiliateDAO().createAFDetail(vbAFDetail);
	}
	public static void updateAFDetail(VBAFDetail vbAFDetail)
	{
		getAffiliateDAO().updateAFDetail(vbAFDetail);
	}
	public static void deleteAFDetail(VBAFDetail vbAFDetail)
	{
		getAffiliateDAO().deleteAFDetail(vbAFDetail);
	}

}

