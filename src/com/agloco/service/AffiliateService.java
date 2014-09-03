
package com.agloco.service;

import java.util.Calendar;
import java.util.List;

import com.agloco.model.VBAFDetail;
import com.agloco.model.VBAffiliate;

/**
 * 
 * @author Erick Kong
 * @see AffiliateService.java
 * @createDate: 2007-4-12
 * @version 1.0
 */

public interface AffiliateService
{
	//Affiliate
	public List getNewAffiliates(Calendar afterDate);
	public List getAllAffiliates();
	public VBAffiliate getAffiliate(Long affiliateId);
	public void createAffiliate(VBAffiliate vbaffiliate);
	public void updateAffiliate(VBAffiliate vbaffiliate);
	public void deleteAffiliate(VBAffiliate vbaffiliate);
	

	//Affiliate Detail
	public List getAFDetails();
	public List getNewAFDetails(Calendar afterDate);
	public List getAFDetails(Long affiliateId);
	public List getNewAFDetails(Long affiliateId,Calendar afterDate);
	public VBAFDetail getAFDetail(Long afDetailId);
	public void createAFDetail(VBAFDetail vbAFDetail);
	public void updateAFDetail(VBAFDetail vbAFDetail);
	public void deleteAFDetail(VBAFDetail vbAFDetail);

}

