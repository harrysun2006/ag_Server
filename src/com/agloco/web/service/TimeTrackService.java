package com.agloco.web.service;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.agloco.Constants;
import com.agloco.ResponseCode;
import com.agloco.parse.BeanToXmlUtil;
import com.agloco.parse.XmlToBeanUtil;
import com.agloco.rolling.RollingTableObject;
import com.agloco.service.util.SurfServiceUtil;
import com.agloco.util.CommonUtil;
import com.agloco.web.exception.BadRequestException;
import com.agloco.web.exception.BeanToXmlException;
import com.agloco.web.exception.DuplicateRecordException;
import com.agloco.web.exception.XmlToBeanException;
import com.agloco.web.service.model.TimeTrackListReq;
import com.agloco.web.service.model.TimeTrackRes;

/**
 * Record the time track
 * The data will be inserted to timeTrack, timeSubtotal and timeTotal table
 * TimeTrack table record the detail surf time, this table will be created every day
 * TimeSubtotal table record the total time of every day, this table will be created every month
 * TimeTotal table record the total surf time of every member 
 * 
 * @author Erick Kong
 * @see TimeTrackService.java
 * @createDate: 2007-4-3
 * @version 1.0
 */

public class TimeTrackService implements BaseService
{
	private static RollingTableObject timeRolling;

	private static RollingTableObject timeSubTotal;

	public void run(HttpServletRequest request, HttpServletResponse response,
			List fileItems) throws Exception
	{
		// Rolling time-track table and time-sub-total table 		
		timeRolling.rolling();
		timeSubTotal.rolling();

		OutputStream out = response.getOutputStream();

		Long submitId = new Long(0);
		try
		{
			for (int i = 0; i < fileItems.size(); i++)
			{
				FileItem fileItem = (FileItem) fileItems.get(i);
				if (fileItem.getSize() < 1)
					continue;
				InputStream in = fileItem.getInputStream();
				TimeTrackListReq trackList = (TimeTrackListReq) XmlToBeanUtil.parseByCastor(in);
				submitId = trackList.getSubmitId();
				if(submitId == null)
				{
					sendResponse(submitId,ResponseCode.FORCE_UPDATE, out);
					if (out != null)
						out.close();
					return;
				}
//				else if(submitId > System.currentTimeMillis() / 1000 + 60) // set 1 minutes difference
				else if(submitId > (System.currentTimeMillis() / 1000 + 30)) // Modify by locker at 2007-06-04
				{
					if(log.isDebugEnabled())
					{
						log.debug("MemberId:"+trackList.getMemberId()+"  Submit time:"+submitId+"  Server time:"+System.currentTimeMillis() / 1000);
					}
					// Send the result to client
					sendResponse(submitId,ResponseCode.BAD_REQUEST, out);
					if (out != null)
						out.close();
					return;
				}
				
				SurfServiceUtil.addBatchTimeTrack(trackList, timeRolling
						.getTableName(), timeRolling.getDatePattern(),
						timeSubTotal.getTableName(), timeSubTotal
								.getDatePattern());
			}

			// Send the result to client
			sendResponse(submitId,ResponseCode.SUCCESS, out);
		}
		catch (XmlToBeanException e)
		{
			// TODO Auto-generated catch block
			log.error("Parse xml to bean error!", e);
			sendResponse(submitId,ResponseCode.MAPPING_EXCEPTION, out);
		}
		catch (DuplicateRecordException e)
		{
			// TODO Auto-generated catch block
			log.error("", e);
			sendResponse(submitId,ResponseCode.DUPLICATE_RECORD, out);
		}
		catch (BadRequestException e)
		{
			// TODO Auto-generated catch block
			log.error("", e);
			sendResponse(submitId,ResponseCode.BAD_REQUEST, out);
		}
		finally
		{
			if (out != null)
				out.close();
		}
	}

	private void sendResponse(Long submitId,int result, OutputStream out) throws IOException
	{
		try
		{
			TimeTrackRes timeRes = new TimeTrackRes();
			timeRes.setSubmitId(submitId.longValue());
			timeRes.setResult(result);
			timeRes.setNextTime(CommonUtil.getNextSubmitTime().longValue());
			String timeXml = BeanToXmlUtil.parseByCastor(timeRes);
			if(log.isDebugEnabled()) {
				log.debug("timeTrackService: " + timeXml);
			}
			out.write(timeXml.getBytes());
		}
		catch (BeanToXmlException e)
		{
			// TODO Auto-generated catch block
			log.error(e);
			StringBuffer sb = new StringBuffer(Constants.XML_FILE_HEADER);
			sb.append("<timeRes result=\"").append(
					ResponseCode.MAPPING_EXCEPTION)
					.append("\" submitId=\"").append(submitId)
					.append("\" nextTime=\"").append(
							CommonUtil.getNextSubmitTime().longValue())
					.append("\"/>");
			out.write(sb.toString().getBytes());
		}
	}
	
	
	public void setTimeRolling(RollingTableObject timeRolling)
	{
		TimeTrackService.timeRolling = timeRolling;
	}

	public void setTimeSubTotal(RollingTableObject timeSubTotal)
	{
		TimeTrackService.timeSubTotal = timeSubTotal;
	}

	private Log log = LogFactory.getLog(TimeTrackService.class);

}
