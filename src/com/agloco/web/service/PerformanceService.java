package com.agloco.web.service;

import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.agloco.Constants;
import com.agloco.ResponseCode;
import com.agloco.model.VBRule;
import com.agloco.parse.BeanToXmlUtil;
import com.agloco.service.util.RuleServiceUtil;
import com.agloco.util.CommonUtil;
import com.agloco.util.PerformanceUtil;
import com.agloco.web.exception.BeanToXmlException;
import com.agloco.web.exception.InputFileException;
import com.agloco.web.exception.XmlToBeanException;
import com.agloco.web.service.model.VBPerformanceListRes;
import com.agloco.web.service.model.VBPerformanceRes;
import com.agloco.web.session.SessionAuthenticator;

public class PerformanceService implements BaseService {

	private final static Log log = LogFactory.getLog(PerformanceService.class);

	public void run(HttpServletRequest request, HttpServletResponse response,
			List fileItems) throws Exception {

		response.setContentType("text/html");
		response.setHeader("Content-Disposition", "attachment; filename="
				+ URLEncoder.encode("response.xml", "UTF-8"));
		OutputStream out = null;
		try {
			out = response.getOutputStream();
			if (fileItems.size() > 1) {
				throw new InputFileException();
			}

			List rules = RuleServiceUtil.listRules(
					Constants.VIEWBAR_PERFORMANCE_TYPE,
					Constants.VIEWBAR_RULE_ACTIVE);
			VBPerformanceListRes performanceListRes = new VBPerformanceListRes();
			performanceListRes.setNextTime(Long.parseLong(CommonUtil
					.getNextSubmitTime().toString()));
			performanceListRes.setResult(ResponseCode.SUCCESS);

			for (int i = 0; i < rules.size(); i++) {
				VBRule tempRule = (VBRule) rules.get(i);
				VBPerformanceRes tempPerformanceRes = new VBPerformanceRes();
				tempPerformanceRes.setName(tempRule.getName());
				tempPerformanceRes.setValue(tempRule.getValue());
				if (tempRule.getName().equals(Constants.PERFORMANCE_CPU)) {
					tempPerformanceRes.setCurrentValue(String
							.valueOf(PerformanceUtil.getCpuPerformance()));
				} else if (tempRule.getName().equals(Constants.PERFORMANCE_MEM)) {
					tempPerformanceRes.setCurrentValue(String
							.valueOf(PerformanceUtil.getMemPerformance()));
				} else if (tempRule.getName().equals(
						Constants.PERFORMANCE_ONLINE_COUNT)) {
					tempPerformanceRes.setCurrentValue(String
							.valueOf(PerformanceUtil.getCurrentOnlineCount()));
				} else if (tempRule.getName().equals(
						Constants.PERFORMANCE_DOWNLOAD_COUNT)) {
					tempPerformanceRes.setCurrentValue(String
							.valueOf(PerformanceUtil.getDownloadCount()));
				}
				tempPerformanceRes.setOperator(tempRule.getOperator());
				performanceListRes.addItem(tempPerformanceRes);
			}
			
			if (log.isInfoEnabled()) {
				log.info("performance success : " + SessionAuthenticator.getMemberId(request) + ":" + BeanToXmlUtil.parseByCastor(performanceListRes, true));
			}
			out.write(BeanToXmlUtil.parseByCastor(performanceListRes, true).getBytes());

		} catch (XmlToBeanException e) {
			
			if (log.isErrorEnabled()) {
				log.error("XmlToBeanException", e);
			}
			
			StringBuffer sb = new StringBuffer(Constants.XML_FILE_HEADER);
			sb.append("<vbPerformanceListRes result=\"").append(
					ResponseCode.BAD_REQUEST).append("\"/>");
			out.write(sb.toString().getBytes());
		} catch (BeanToXmlException e) {
			
			if (log.isErrorEnabled()) {
				log.error("BeanToXmlException", e);
			}
			
			StringBuffer sb = new StringBuffer(Constants.XML_FILE_HEADER);
			sb.append("<vbPerformanceListRes result=\"").append(
					ResponseCode.MAPPING_EXCEPTION).append("\"/>");
			out.write(sb.toString().getBytes());
		} catch (Exception e) {
			
			if (log.isErrorEnabled()) {
				log.error("exception", e);
			}
			
			StringBuffer sb = new StringBuffer(Constants.XML_FILE_HEADER);
			sb.append("<vbPerformanceListRes result=\"").append(
					ResponseCode.COMMON_EXCEPTION).append("\"/>");
			out.write(sb.toString().getBytes());
		} finally {
			if (out != null) {
				out.close();
			}
		}

	}
}
