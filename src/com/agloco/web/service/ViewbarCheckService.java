package com.agloco.web.service;

import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.agloco.Constants;
import com.agloco.ResponseCode;
import com.agloco.model.VBViewbar;
import com.agloco.parse.BeanToXmlUtil;
import com.agloco.parse.XmlToBeanUtil;
import com.agloco.service.util.ViewbarServiceUtil;
import com.agloco.web.exception.BeanToXmlException;
import com.agloco.web.exception.InputFileException;
import com.agloco.web.exception.XmlToBeanException;
import com.agloco.web.service.model.ViewbarCheckRes;
import com.agloco.web.service.model.ViewbarReq;
import com.agloco.web.session.SessionAuthenticator;

public class ViewbarCheckService implements BaseService {

	private final static Log log = LogFactory.getLog(ViewbarCheckService.class);

	public void run(HttpServletRequest request, HttpServletResponse response,
			List fileItems) throws Exception {

		response.setContentType("text/html");
		response.setHeader("Content-Disposition", "attachment; filename="
				+ URLEncoder.encode("response.xml", "UTF-8"));
		OutputStream out = null;
		try {
			out = response.getOutputStream();
			// get the file inputStream
			if (fileItems == null || fileItems.size() != 1) {
				throw new InputFileException();
			}
			FileItem item = (FileItem) fileItems.iterator().next();
			ViewbarReq viewbarReq = (ViewbarReq) XmlToBeanUtil
					.parseByCastor(item.getInputStream());
			// the Return value
			ViewbarCheckRes returnViewbar = ViewbarServiceUtil
					.checkViewbar(viewbarReq.getViewbarId());
			if (returnViewbar == null) {
				VBViewbar tempViewbar = ViewbarServiceUtil
						.getLatestViewbar(viewbarReq.getViewbarId().substring(
								0, viewbarReq.getViewbarId().indexOf(".") + 1));
				if (tempViewbar==null){
					throw new Exception("No This viewbar exsit!");
				}
				returnViewbar = new ViewbarCheckRes();
				returnViewbar.setLatestViewbarId(tempViewbar.getViewbarId());
			}
			returnViewbar.setResult(ResponseCode.SUCCESS);

			// Add by locker 2007-05-10
			if (log.isInfoEnabled()) {
				log.info("viewbar check success : "
						+ SessionAuthenticator.getMemberId(request) + ":"
						+ request.getRemoteAddr());
			}
			out.write(BeanToXmlUtil.parseByCastor(returnViewbar, true)
					.getBytes());
		} catch (XmlToBeanException e) {

			if (log.isErrorEnabled()) {
				log.error("XmlToBeanException", e);
			}

			StringBuffer sb = new StringBuffer(Constants.XML_FILE_HEADER);
			sb.append("<latestViewbar result=\"").append(
					ResponseCode.BAD_REQUEST).append("\"/>");
			out.write(sb.toString().getBytes());
		} catch (BeanToXmlException e) {

			if (log.isErrorEnabled()) {
				log.error("BeanToXmlException", e);
			}

			StringBuffer sb = new StringBuffer(Constants.XML_FILE_HEADER);
			sb.append("<latestViewbar result=\"").append(
					ResponseCode.MAPPING_EXCEPTION).append("\"/>");
			out.write(sb.toString().getBytes());
		} catch (Exception e) {

			if (log.isErrorEnabled()) {
				log.error("exception", e);
			}

			StringBuffer sb = new StringBuffer(Constants.XML_FILE_HEADER);
			sb.append("<latestViewbar result=\"").append(
					ResponseCode.COMMON_EXCEPTION).append("\"/>");
			out.write(sb.toString().getBytes());
		} finally {
			if (out != null) {
				out.close();
			}
		}
	}
}
