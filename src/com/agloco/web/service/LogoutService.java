package com.agloco.web.service;

import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.agloco.Constants;
import com.agloco.ResponseCode;
import com.agloco.log4j.LogUtil;
import com.agloco.log4j.MessageObject;
import com.agloco.parse.BeanToXmlUtil;
import com.agloco.parse.XmlToBeanUtil;
import com.agloco.web.exception.BeanToXmlException;
import com.agloco.web.exception.XmlToBeanException;
import com.agloco.web.service.model.LogoutReq;
import com.agloco.web.service.model.LogoutRes;
import com.agloco.web.session.SessionAuthenticator;
import com.agloco.web.util.MiscUtil;
/**
 * 
 * @author terry_zhao
 * @since 2007-04-04
 * @version 1.0
 */
public class LogoutService implements BaseService {

	private final static Log log = LogFactory.getLog(LogoutService.class);
	
	public void run(HttpServletRequest request, HttpServletResponse response,
			List fileItems) throws Exception {
		
		response.setContentType("text/html");
		response.setHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode("response.xml", "UTF-8"));
		OutputStream out = null;
		LogoutRes logoutRes = new LogoutRes();
		try {
			
			out = response.getOutputStream();
			excute(request, fileItems, logoutRes);
			String logoutResXml = BeanToXmlUtil.parseByCastor(logoutRes,true);
			out.write(logoutResXml.getBytes());
			
		} catch (BeanToXmlException e) {

			if(log.isErrorEnabled()){
				log.error("BeanToXmlException",e);
			}
			
			StringBuffer sb = new StringBuffer(Constants.XML_FILE_HEADER);
			sb.append("<logoutRes result=\"")
			  .append(ResponseCode.SUCCESS)
			  .append("\"/>");
			out.write(sb.toString().getBytes());
			
		} catch (XmlToBeanException e) {
			
			if(log.isErrorEnabled()){
				log.error("XmlToBeanException",e);
			}
			
			StringBuffer sb = new StringBuffer(Constants.XML_FILE_HEADER);
			sb.append("<logoutRes result=\"")
			  .append(ResponseCode.MAPPING_EXCEPTION)
			  .append("\"/>");
			out.write(sb.toString().getBytes());
			
		}catch (Exception e) {
			
			if(log.isErrorEnabled()){
				log.error("process logout error",e);
			}
			
			StringBuffer sb = new StringBuffer(Constants.XML_FILE_HEADER);
			sb.append("<logoutRes result=\"")
			  .append(ResponseCode.COMMON_EXCEPTION)
			  .append("\"/>");
			out.write(sb.toString().getBytes());

		}
		finally{
			try {
				if(out != null){
					out.close();
				}	
			} catch (Exception e) {
				if(log.isErrorEnabled()){
					log.error("close outputstream error",e);
				}
			}
		}
	}

	private void excute(HttpServletRequest request, List fileItems, LogoutRes logoutRes) throws Exception{
		LogoutReq logoutReq;
		if(fileItems == null || fileItems.size() < 1){
			logoutRes.setResult(ResponseCode.PARAMETER_ERROR);
			return;
		}
		
		FileItem item = (FileItem)fileItems.iterator().next();
		if(item == null || item.getSize() < 1){
			logoutRes.setResult(ResponseCode.PARAMETER_ERROR);
			return;
		}
		
		logoutReq = (LogoutReq) XmlToBeanUtil.parseByCastor(item.getInputStream());
		if(logoutReq == null ){
			logoutRes.setResult(ResponseCode.PARAMETER_ERROR);
			return;
			
		}
		
		try {
			if(log.isInfoEnabled()){
				MessageObject mo = new MessageObject();
				Long memberId = SessionAuthenticator.getMemberId(request);
				if(memberId == null){
					memberId = logoutReq.getMemberId();
				}
				mo.setComputerName(logoutReq.getComputerName());
				mo.setDomainName(logoutReq.getDomainName());
				StringBuffer ip = new StringBuffer();
				ip.append(request.getRemoteAddr());
				ip.append(":");
				if(StringUtils.isNotBlank(logoutReq.getIpAddr())){
					ip.append(logoutReq.getIpAddr());
				}
				mo.setIpAddr(ip.toString());
				mo.setMacAddr(logoutReq.getMacAddr());
				mo.setMemberId(memberId);
				mo.setOperate(Constants.LOG_OPERATE_LOGOUT);
				mo.setOsVersion(logoutReq.getOsVersion());
				mo.setServerIp(MiscUtil.getLocalIp());
				mo.setSessionId(request.getSession().getId());
				mo.setViewbarId(logoutReq.getViewbarId());
				log.info(mo);
			}	
		} catch (Exception e) {
			LogUtil.error("log error",e);
		}
		
		
		logoutRes.setResult(ResponseCode.SUCCESS);
		
		HttpSession ses = request.getSession();
		ses.invalidate();
		
	}


}
