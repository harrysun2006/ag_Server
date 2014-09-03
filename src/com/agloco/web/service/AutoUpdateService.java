package com.agloco.web.service;

import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.agloco.Constants;
import com.agloco.ResponseCode;
import com.agloco.model.VBFiles;
import com.agloco.model.VBViewbar;
import com.agloco.parse.BeanToXmlUtil;
import com.agloco.parse.XmlToBeanUtil;
import com.agloco.service.util.FilesServiceUtil;
import com.agloco.service.util.ViewbarServiceUtil;
import com.agloco.web.exception.BeanToXmlException;
import com.agloco.web.exception.InputFileException;
import com.agloco.web.exception.XmlToBeanException;
import com.agloco.web.service.model.VBFileDetailRes;
import com.agloco.web.service.model.VBUpdateFilesRes;
import com.agloco.web.service.model.ViewbarReq;
import com.agloco.web.session.SessionAuthenticator;

public class AutoUpdateService implements BaseService {

	private final static Log log = LogFactory.getLog(AutoUpdateService.class);

	public void run(HttpServletRequest request, HttpServletResponse response,
			List fileItems) throws Exception {

		response.setContentType("text/html");
		OutputStream out = null;

		try {
			response.setHeader("Content-Disposition", "attachment; filename="
					+ URLEncoder.encode("response.xml", "UTF-8"));
			out = response.getOutputStream();

			// get the file inputStream
			if (fileItems == null || fileItems.size() != 1) {
				throw new InputFileException();
			}
			FileItem item = (FileItem) fileItems.iterator().next();
			ViewbarReq viewbarReq = (ViewbarReq) XmlToBeanUtil
					.parseByCastor(item.getInputStream());
			VBViewbar currentViewbar = ViewbarServiceUtil.getViewbar(viewbarReq
					.getViewbarId());
			if (currentViewbar == null) {
				throw new Exception();
			}
			VBUpdateFilesRes vbUpdateFilesRes = new VBUpdateFilesRes();
			vbUpdateFilesRes.setViewbarId(currentViewbar.getViewbarId());
			vbUpdateFilesRes.setRootPath(currentViewbar.getRootPath());
			vbUpdateFilesRes.setResult(ResponseCode.SUCCESS);

			List<VBFileDetailRes> resFilesList = new ArrayList<VBFileDetailRes>();
			List<VBFiles> filesList = new ArrayList<VBFiles>();
			filesList = FilesServiceUtil.listFiles(viewbarReq.getViewbarId());
			for (int i = 0; i < filesList.size(); i++) {
				VBFiles tempFile = (VBFiles) filesList.get(i);
				VBFileDetailRes tempFileDetailRes = new VBFileDetailRes();
				tempFileDetailRes.setFileName(tempFile.getFileName());
				tempFileDetailRes.setFilePath(tempFile.getFilePath());
				tempFileDetailRes.setMd5(tempFile.getMd5());
				resFilesList.add(tempFileDetailRes);
			}
			vbUpdateFilesRes.setFilesList(resFilesList);

			// Add by Erick 2007-04-29
			if (log.isInfoEnabled()) {
				log.info("Update list success : " + SessionAuthenticator.getMemberId(request) + ":" + request.getRemoteAddr());
			}

			out.write(BeanToXmlUtil.parseByCastor(vbUpdateFilesRes, true)
					.getBytes());

		} catch (XmlToBeanException e) {

			if (log.isErrorEnabled()) {
				log.error("XmlToBeanException", e);
			}

			StringBuffer sb = new StringBuffer(Constants.XML_FILE_HEADER);
			sb.append("<VBUpdateFilesRes result=\"").append(
					ResponseCode.BAD_REQUEST).append("\"/>");
			out.write(sb.toString().getBytes());
		} catch (BeanToXmlException e) {

			if (log.isErrorEnabled()) {
				log.error("BeanToXmlException", e);
			}

			StringBuffer sb = new StringBuffer(Constants.XML_FILE_HEADER);
			sb.append("<VBUpdateFilesRes result=\"").append(
					ResponseCode.MAPPING_EXCEPTION).append("\"/>");
			out.write(sb.toString().getBytes());
		} catch (Exception e) {

			if (log.isErrorEnabled()) {
				log.error("exception", e);
			}

			StringBuffer sb = new StringBuffer(Constants.XML_FILE_HEADER);
			sb.append("<loginRes result=\"").append(
					ResponseCode.COMMON_EXCEPTION).append("\"/>");
			out.write(sb.toString().getBytes());
		} finally {
			if (out != null) {
				out.close();
			}
		}

	}

}
