
package com.agloco.web.service;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.agloco.Constants;
import com.agloco.ResponseCode;
import com.agloco.model.VBAFDetail;
import com.agloco.model.VBAffiliate;
import com.agloco.parse.BeanToXmlUtil;
import com.agloco.parse.XmlToBeanUtil;
import com.agloco.service.util.AffiliateServiceUtil;
import com.agloco.web.exception.BeanToXmlException;
import com.agloco.web.exception.XmlToBeanException;
import com.agloco.web.service.model.Affiliate;
import com.agloco.web.service.model.AffiliateDetail;
import com.agloco.web.service.model.AffiliateDetailList;
import com.agloco.web.service.model.AffiliateList;
import com.agloco.web.service.model.AffiliateReq;
import com.agloco.web.service.model.AffiliateRes;

/**
 * Client request to check if these is new affiliate data
 * If true, the server will the new data to client  
 * 
 * @author Erick Kong
 * @see AffiliateService.java
 * @createDate: 2007-4-12
 * @version 1.0
 */

public class AffiliateService implements BaseService
{

	
	public void run(HttpServletRequest request, HttpServletResponse response,
			List fileItems) throws Exception
	{
		// TODO Auto-generated method stub
		OutputStream out = response.getOutputStream();
		AffiliateRes afres = new AffiliateRes();
		try
		{
			for (int i = 0; i < fileItems.size(); i++)
			{
				FileItem fileItem = (FileItem) fileItems.get(i);
				if (fileItem.getSize() < 1)
					continue;
				InputStream in = fileItem.getInputStream();
				AffiliateReq affiliateReq = (AffiliateReq) XmlToBeanUtil.parseByCastor(in);
				long afTime = affiliateReq.getAfTime();
				long afdTime = affiliateReq.getAfdTime();
				Calendar afDate = Calendar.getInstance();
				Calendar afdDate = Calendar.getInstance();
				afDate.setTimeInMillis(afTime);
				afdDate.setTimeInMillis(afdTime);
				
				List vbAffiliates = AffiliateServiceUtil.getNewAffiliates(afDate);
				List vbAfDetails = AffiliateServiceUtil.getNewAFDetails(afdDate);
				
				List affiliates = new ArrayList();
				if(vbAffiliates != null && vbAffiliates.size() > 0){
					for (Iterator iter = vbAffiliates.iterator(); iter.hasNext();)
					{
						VBAffiliate vaf = (VBAffiliate) iter.next();
						Affiliate af = new Affiliate();
						af.setAffiliateId(vaf.getAffiliateId());
						af.setCategory(vaf.getCategory());
						af.setCreateDate(new Long(vaf.getCreateDate().getTimeInMillis()/1000));
						af.setDescription(vaf.getDescription());
						af.setName(vaf.getName());
						affiliates.add(af);
					}
				}
				
				List afDetails = new ArrayList();
				if(vbAfDetails != null && vbAfDetails.size() > 0){
					for (Iterator iter = vbAfDetails.iterator(); iter.hasNext();)
					{
						VBAFDetail vafd = (VBAFDetail) iter.next();
						AffiliateDetail afd = new AffiliateDetail();
						afd.setAfDetailId(vafd.getAfDetailId());
						afd.setAffiliateId(vafd.getAffiliateId());
						afd.setAgPattern(vafd.getAgPattern());
						afd.setCategory(vafd.getCategory());
						afd.setCreateDate(new Long(vafd.getCreateDate().getTimeInMillis()/1000));
						afd.setDescription(vafd.getDescription());
						afd.setInfo(vafd.getInfo());
						afd.setPattern(vafd.getPattern());
						afd.setRebate(vafd.getRebate());
						afDetails.add(afd);
					}
				}
				
				
				AffiliateDetailList afdList = new AffiliateDetailList();
				afdList.addAfDetail(afDetails);
				
				AffiliateList affilisteList = new AffiliateList();
				affilisteList.addAffiliate(affiliates);
				
				
				afres.setAfDetails(afdList);
				afres.setAffiliates(affilisteList);
				
				//Create the response xml
				sendResponse(ResponseCode.SUCCESS, out, afres);
			}
			
			
		}
		catch (XmlToBeanException e)
		{
			// TODO Auto-generated catch block
			log.error(e);
			sendResponse(ResponseCode.MAPPING_EXCEPTION, out, afres);
			
		}
		finally
		{
			try
			{
				if (out != null)
					out.close();	
			}
			catch (Exception e)
			{
				if(log.isErrorEnabled()){
					log.error("close output stream error", e);
				}
			}
			
		}

	}

	private void sendResponse(int result, OutputStream out,AffiliateRes afres) throws IOException
	{
		try
		{
			afres.setResult(result);
			String resXml = BeanToXmlUtil.parseByCastor(afres,true);
			out.write(resXml.getBytes());
		}
		catch (BeanToXmlException e)
		{
			// TODO Auto-generated catch block
			log.error(e);
			StringBuffer sb = new StringBuffer(Constants.XML_FILE_HEADER);
			sb.append("<affiliateRes result=\"")
			  .append(ResponseCode.SUCCESS)
			  .append("\" />");
			out.write(sb.toString().getBytes());
		}
		finally
		{
			try
			{
				if (out != null)
					out.close();	
			}
			catch (Exception e)
			{
				if(log.isErrorEnabled()){
					log.error("close output stream error", e);
				}
			}
			
		}
	}

	private Log log = LogFactory.getLog(AffiliateService.class);

}

