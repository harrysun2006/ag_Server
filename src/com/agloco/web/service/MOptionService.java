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
import com.agloco.model.VBMOption;
import com.agloco.parse.BeanToXmlUtil;
import com.agloco.service.util.MOptionServiceUtil;
import com.agloco.web.exception.BeanToXmlException;
import com.agloco.web.exception.XmlToBeanException;
import com.agloco.web.service.model.MOptionListRes;
import com.agloco.web.service.model.MOptionRes;

/**
 * @author author E-mail:zhaon12@gmail.com
 * @version createDate:Apr 5, 2007 3:20:27 PM content
 */
public class MOptionService implements BaseService {

	private final static Log log = LogFactory.getLog(MOptionService.class);

	public void run(HttpServletRequest request, HttpServletResponse response,
			List fileItems) throws Exception {

		response.setContentType("text/html");
		response.setHeader("Content-Disposition", "attachment; filename="
				+ URLEncoder.encode("response.xml", "UTF-8"));
		OutputStream out = null;
		try {

			out = response.getOutputStream();
			List list = MOptionServiceUtil.listMOpton(new Long(1));
			MOptionListRes optionListRes = new MOptionListRes();
			optionListRes.setResult(ResponseCode.SUCCESS);

			for (int i = 0; i < list.size(); i++) {
				VBMOption tempVBMOption = (VBMOption) list.get(i);
				MOptionRes tempMOptionRes = new MOptionRes();
				tempMOptionRes.setMemberId(tempVBMOption.getMemberId());
				tempMOptionRes.setName(tempVBMOption.getName());
				tempMOptionRes.setValue(tempVBMOption.getValue());
				optionListRes.addItem(tempMOptionRes);
			}
			out.write(BeanToXmlUtil.parseByCastor(optionListRes, true)
					.getBytes());

		} catch (XmlToBeanException e) {
			
			if (log.isErrorEnabled()) {
				log.error("XmlToBeanException", e);
			}
			
			StringBuffer sb = new StringBuffer(Constants.XML_FILE_HEADER);
			sb.append("<MOptionListRes result=\"").append(
					ResponseCode.BAD_REQUEST).append("\"/>");
			out.write(sb.toString().getBytes());
		} catch (BeanToXmlException e) {
			
			if (log.isErrorEnabled()) {
				log.error("BeanToXmlException", e);
			}
			
			StringBuffer sb = new StringBuffer(Constants.XML_FILE_HEADER);
			sb.append("<MOptionListRes result=\"").append(
					ResponseCode.MAPPING_EXCEPTION).append("\"/>");
			out.write(sb.toString().getBytes());
		} catch (Exception e) {
			
			if (log.isErrorEnabled()) {
				log.error("exception", e);
			}
			
			StringBuffer sb = new StringBuffer(Constants.XML_FILE_HEADER);
			sb.append("<MOptionListRes result=\"").append(
					ResponseCode.COMMON_EXCEPTION).append("\"/>");
			out.write(sb.toString().getBytes());
		} finally {
			if (out != null) {
				out.close();
			}
		}
	}

}
