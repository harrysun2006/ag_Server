package com.agloco.web.servlet;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.agloco.Constants;
import com.agloco.spring.SpringUtil;
import com.agloco.web.service.BaseService;

public class MainServlet extends HttpServlet implements Servlet{
	

	public MainServlet() {
		super();
		System.out.println("MainServlet init success");
	}  
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}  	
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String serviceName = null;
		BaseService service = null;
		List fileItems = null;
		
		try {
			
			serviceName = (String) request.getAttribute(Constants.REQUEST_SERVICE_NAME);
			if(StringUtils.isBlank(serviceName)){
				response.sendError(HttpServletResponse.SC_BAD_REQUEST);	
			}
			
			fileItems = (List) request.getAttribute(Constants.REQUEST_FILE_ITEMS);
			service = (BaseService) SpringUtil.getContext().getBean(serviceName);
			if(service == null){
				response.sendError(HttpServletResponse.SC_BAD_REQUEST);	
			}
			
			service.run(request, response, fileItems);
			
			for (Iterator iter = fileItems.iterator(); iter.hasNext();)
			{
				FileItem element = (FileItem) iter.next();
				if(element!=null)
					element.delete();
			}
			
			
		} catch (Exception e) {
			
			if(log.isErrorEnabled()){
				StringBuffer sb = new StringBuffer();
				sb.append("Error in servlet , sessionId = ").append(request.getSession().getId());
				sb.append(" serviceName = ").append(serviceName);
				log.error(sb.toString(), e);
			}
			response.sendError(HttpServletResponse.SC_BAD_REQUEST);
		}
	}
	
	private static final Log log = LogFactory.getLog(MainServlet.class);
}
