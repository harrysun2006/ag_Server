package com.agloco.web.service;

import java.io.OutputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.agloco.Constants;
import com.agloco.model.VBViewbar;
import com.agloco.parse.XmlToBeanUtil;
import com.agloco.service.util.ViewbarServiceUtil;
import com.agloco.util.PerformanceUtil;
import com.agloco.web.exception.InputFileException;
import com.agloco.web.exception.XmlToBeanException;
import com.agloco.web.service.model.DownloadReq;
import com.agloco.web.session.SessionAuthenticator;

public class DownloadCountService implements BaseService {

	private final static Log log = LogFactory
			.getLog(DownloadCountService.class);

	public void run(HttpServletRequest request, HttpServletResponse response,
			List fileItems) throws Exception {
		OutputStream out = null;
		try {
			// get the file inputStream
			if (fileItems == null || fileItems.size() != 1) {
				throw new InputFileException();
			}
			FileItem item = (FileItem) fileItems.iterator().next();
			DownloadReq downloadReq = (DownloadReq) XmlToBeanUtil
					.parseByCastor(item.getInputStream());

			if (request.getSession().getAttribute(
					Constants.VIEWBAR_AUTO_UPDATE_FLAG) != null) {
				if (request.getSession().getAttribute(
						Constants.VIEWBAR_AUTO_UPDATE_FLAG)
						.equals(Boolean.TRUE)
						&& downloadReq.getOperate().equals(
								Constants.AUTO_UPDATE_START)) {
					return;
				}
			}
			out = response.getOutputStream();
			if (downloadReq.getOperate().equals(Constants.AUTO_UPDATE_START)) {

				VBViewbar viewbar = ViewbarServiceUtil.getViewbar(downloadReq
						.getViewbarId());
				if (viewbar == null) {
					return;
				}
				long downloadCount = Long.parseLong(viewbar.getDownloadCount()
						.toString()) + 1;
				viewbar.setDownloadCount(Long.valueOf(downloadCount));
				ViewbarServiceUtil.updateViewbar(viewbar);

				PerformanceUtil.VB_DOWNLOAD_COUNT = PerformanceUtil.VB_DOWNLOAD_COUNT + 1;
				request.getSession().setAttribute(
						Constants.VIEWBAR_AUTO_UPDATE_FLAG, Boolean.TRUE);

				//Add by Erick 2007-04-29
				if (log.isInfoEnabled()) {
					log.info("Down load start : "
							+ SessionAuthenticator.getMemberId(request) + ":"
							+ request.getRemoteAddr());
				}
			} else if (downloadReq.getOperate().equals(
					Constants.AUTO_UPDATE_END)) {
				
				VBViewbar viewbar = ViewbarServiceUtil.getViewbar(downloadReq
						.getViewbarId());
				if (viewbar == null) {
					return;
				}
				long downloadSCount = Long.parseLong(viewbar.getDownloadSCount()
						.toString()) + 1;
				viewbar.setDownloadSCount(Long.valueOf(downloadSCount));
				ViewbarServiceUtil.updateViewbar(viewbar);
				
				PerformanceUtil.VB_DOWNLOAD_COUNT = PerformanceUtil.VB_DOWNLOAD_COUNT - 1;
				request.getSession().setAttribute(
						Constants.VIEWBAR_AUTO_UPDATE_FLAG, Boolean.FALSE);
				
				//Add by Erick 2007-04-29
				if(log.isInfoEnabled())
				{
					log.info("Down load success : " + SessionAuthenticator.getMemberId(request) + ":" +request.getRemoteAddr());
				}
			} else {
				return;
			}
		} catch (XmlToBeanException e) {
			if (log.isErrorEnabled()) {
				log.error("XmlToBeanException", e);
			}
		} catch (Exception e) {
			if (log.isErrorEnabled()) {
				log.error("Exception", e);
			}
		} finally {
			if (out != null) {
				out.close();
			}
		}
	}
}
