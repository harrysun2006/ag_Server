
package com.agloco.service.impl;

import java.util.Calendar;
import java.util.List;

import com.agloco.dao.util.AffiliateDAOUtil;
import com.agloco.model.VBAFDetail;
import com.agloco.model.VBAffiliate;
import com.agloco.service.AffiliateService;

/**
 * 
 * @author Erick Kong
 * @see AffiliateServiceImpl.java
 * @createDate: 2007-4-12
 * @version 1.0
 */

public class AffiliateServiceImpl implements AffiliateService
{

	public void createAFDetail(VBAFDetail vbAFDetail)
	{
		// TODO Auto-generated method stub
		AffiliateDAOUtil.createAFDetail(vbAFDetail);
	}

	public void createAffiliate(VBAffiliate vbaffiliate)
	{
		// TODO Auto-generated method stub
		AffiliateDAOUtil.createAffiliate(vbaffiliate);
	}

	public void deleteAFDetail(VBAFDetail vbAFDetail)
	{
		// TODO Auto-generated method stub
		AffiliateDAOUtil.deleteAFDetail(vbAFDetail);
	}

	public void deleteAffiliate(VBAffiliate vbaffiliate)
	{
		// TODO Auto-generated method stub
		AffiliateDAOUtil.deleteAffiliate(vbaffiliate);
	}

	public VBAFDetail getAFDetail(Long afDetailId)
	{
		// TODO Auto-generated method stub
		return AffiliateDAOUtil.getAFDetail(afDetailId);
	}

	public List getAFDetails()
	{
		// TODO Auto-generated method stub
		return AffiliateDAOUtil.getAFDetails();
	}

	public List getAFDetails(Long affiliateId)
	{
		// TODO Auto-generated method stub
		return AffiliateDAOUtil.getAFDetails(affiliateId);
	}

	public VBAffiliate getAffiliate(Long affiliateId)
	{
		// TODO Auto-generated method stub
		return AffiliateDAOUtil.getAffiliate(affiliateId);
	}

	public List getAllAffiliates()
	{
		// TODO Auto-generated method stub
		return AffiliateDAOUtil.getAllAffiliates();
	}

	public List getNewAFDetails(Calendar afterDate)
	{
		// TODO Auto-generated method stub
		return AffiliateDAOUtil.getNewAFDetails(afterDate);
	}

	public List getNewAFDetails(Long affiliateId, Calendar afterDate)
	{
		// TODO Auto-generated method stub
		return AffiliateDAOUtil.getNewAFDetails(affiliateId, afterDate);
	}

	public List getNewAffiliates(Calendar afterDate)
	{
		// TODO Auto-generated method stub
		return AffiliateDAOUtil.getNewAffiliates(afterDate);
	}

	public void updateAFDetail(VBAFDetail vbAFDetail)
	{
		// TODO Auto-generated method stub
		AffiliateDAOUtil.updateAFDetail(vbAFDetail);
	}

	public void updateAffiliate(VBAffiliate vbaffiliate)
	{
		// TODO Auto-generated method stub
		AffiliateDAOUtil.updateAffiliate(vbaffiliate);
	}

}

