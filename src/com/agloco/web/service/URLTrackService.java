package com.agloco.web.service;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.agloco.Constants;
import com.agloco.ResponseCode;
import com.agloco.model.VBURLTrack;
import com.agloco.parse.BeanToXmlUtil;
import com.agloco.parse.XmlToBeanUtil;
import com.agloco.rolling.RollingTableObject;
import com.agloco.service.util.SurfServiceUtil;
import com.agloco.util.CommonUtil;
import com.agloco.web.exception.BeanToXmlException;
import com.agloco.web.exception.XmlToBeanException;
import com.agloco.web.service.model.URLTrack;
import com.agloco.web.service.model.URLTrackListReq;
import com.agloco.web.service.model.URLTrackRes;
import com.agloco.web.service.model.VBReqList;

/**
 * 
 * @author Erick Kong
 * @see URLTrackService.java
 * @createDate: 2007-4-3
 * @version 1.0
 */

public class URLTrackService implements BaseService
{
	private static RollingTableObject urlRolling;

	public void run(HttpServletRequest request, HttpServletResponse response,
			List fileItems) throws Exception
	{
		// Rolling url track table
		urlRolling.rolling();

		OutputStream out = response.getOutputStream();

		try
		{
			for (int i = 0; i < fileItems.size(); i++)
			{
				FileItem fileItem = (FileItem) fileItems.get(i);
				if (fileItem.getSize() < 1)
					continue;
				InputStream in = fileItem.getInputStream();
				URLTrackListReq trackList = (URLTrackListReq) XmlToBeanUtil.parseByCastor(in);

				dealWithUrlTrack(trackList);
			}
			
			sendResponse(ResponseCode.SUCCESS, out);
		}
		catch (XmlToBeanException e)
		{
			// TODO Auto-generated catch block
			log.error(e);
			sendResponse(ResponseCode.MAPPING_EXCEPTION, out);
			
		}
		finally
		{
			if (out != null)
				out.close();
		}
	}

	private void dealWithUrlTrack(VBReqList trackList) throws Exception
	{
		for (int idx = 0; idx < trackList.size(); idx++)
		{
			URLTrack urlTrack = (URLTrack) trackList.get(idx);

			// Deal with UrlTrack;
			VBURLTrack vcUrlTrack = new VBURLTrack();
			BeanUtils.copyProperties(vcUrlTrack, urlTrack);
			vcUrlTrack.setMemberId(trackList.getMemberId());
			vcUrlTrack.setCreateDate(Calendar.getInstance());
			SurfServiceUtil.createURLTrack(vcUrlTrack);
		}

	}

	private void sendResponse(int result, OutputStream out) throws IOException
	{
		try
		{
			URLTrackRes urlRes = new URLTrackRes();
			urlRes.setResult(ResponseCode.SUCCESS);
			urlRes.setNextTime(CommonUtil.getNextSubmitTime().longValue());
			String resXml = BeanToXmlUtil.parseByCastor(urlRes);
			out.write(resXml.getBytes());
		}
		catch (BeanToXmlException e)
		{
			// TODO Auto-generated catch block
			log.error(e);
			StringBuffer sb = new StringBuffer(Constants.XML_FILE_HEADER);
			sb.append("<urlRes result=\"").append(ResponseCode.SUCCESS).append(
					"\" nextTime=\"").append(
					CommonUtil.getNextSubmitTime().longValue()).append("\"/>");
			out.write(sb.toString().getBytes());
		}
	}

	public void setUrlRolling(RollingTableObject urlRolling)
	{
		URLTrackService.urlRolling = urlRolling;
	}

	private Log log = LogFactory.getLog(URLTrackService.class);

}
