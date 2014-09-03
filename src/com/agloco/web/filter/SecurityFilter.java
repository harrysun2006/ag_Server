package com.agloco.web.filter;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.agloco.Constants;
import com.agloco.PropertiesKeys;
import com.agloco.ResponseCode;
import com.agloco.util.PropsUtil;
import com.agloco.web.session.SessionAuthenticator;



public class SecurityFilter implements Filter {

	private FileItemFactory factory = null;
	private ServletFileUpload upload = null;
	private List<String> services = null;
	private final static Log log = LogFactory.getLog(SecurityFilter.class);
	
	public void init(FilterConfig arg0) throws ServletException {
		factory = new DiskFileItemFactory();
		upload = new ServletFileUpload(factory);
		services = new ArrayList<String>();
		String[] nas = PropsUtil.getArray(PropertiesKeys.NEEDLESS_AUTHENTICATED_SERVICES);
		if(nas != null && nas.length > 1){
			for (int i = 0; i < nas.length; i++) {
				services.add(nas[i]);
			}	
		}
		
		System.out.println("SecurityFilter init success");

	}


	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
			FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest request = (HttpServletRequest)servletRequest;
		HttpServletResponse response = (HttpServletResponse)servletResponse;
		
		if (!request.isSecure()) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST);
			return;
		}
		String serviceName = null;
		List fileItems = null;
		try {

			boolean isMultipart = ServletFileUpload.isMultipartContent(request);
			if(isMultipart){
				List items = upload.parseRequest(request);
				fileItems = new ArrayList();
				if(items != null && items.size() > 0){
					for(Iterator it = items.iterator(); it.hasNext();){
						FileItem item = (FileItem)it.next();
						if(item.isFormField()){
							if(item.getFieldName().equals(Constants.REQUEST_SERVICE_NAME)){
								serviceName = item.getString();
							}
						}
						else{
							fileItems.add(item);
						}
					}
				}
					
			}
			else{
				serviceName = request.getParameter(Constants.REQUEST_SERVICE_NAME);
				
			}
			
			request.setAttribute(Constants.REQUEST_SERVICE_NAME, serviceName);
			request.setAttribute(Constants.REQUEST_FILE_ITEMS, fileItems);
			if(log.isDebugEnabled()) {
				StringBuffer sb = new StringBuffer();
				sb.append(request.getSession().getId()).append(": ");
				FileItem item;
				sb.append(serviceName).append("[").append(isMultipart).append(": ");
				for(Iterator it = fileItems.iterator(); it.hasNext(); ) {
					item = (FileItem) it.next();
					sb.append(item.getString()).append(", ");
				}
				if(fileItems.size() > 0) sb.delete(sb.length() - 2, sb.length());
				sb.append("]");
				log.debug(sb.toString());
			}
			

			
			if(services.contains(serviceName)){
				//if service is login or autoLogin service , renew a session. add at 2007-06-05
//				if(Constants.LOGIN_SERVICE.equals(serviceName) || Constants.AUTO_LOGIN_SERVICE.equals(serviceName)){
//					request.getSession(true);
//				}
				chain.doFilter(servletRequest, servletResponse);
				return;
			}
			
			int authenticate = SessionAuthenticator.authenticate(request);
			if(ResponseCode.SUCCESS == authenticate){
				chain.doFilter(servletRequest, servletResponse);
				return;
			}
			if(log.isDebugEnabled()) {
				StringBuffer sb = new StringBuffer();
				sb.append(request.getSession().getId()).append(": ");
				sb.append("Authenticate result : ").append(authenticate);
				log.debug(sb.toString());
			}
			
			response.setContentType("text/html");
			response.setHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode("response.xml", "UTF-8"));
			OutputStream out = null;
			out = response.getOutputStream();
			StringBuffer sb = new StringBuffer(Constants.XML_FILE_HEADER);
			sb.append("<authorize result=\"")
			  .append(authenticate)
			  .append("\"/>");
			out.write(sb.toString().getBytes());
			//add by locker 2007-06-12
			if(out != null){
				out.close();
			}	
			response.flushBuffer();
		} catch (Exception e) {
			
			if(log.isErrorEnabled()){
				StringBuffer sb = new StringBuffer();
				sb.append("Error in filter , sessionId = ").append(request.getSession().getId());
				sb.append(" serviceName = ").append(serviceName);
				log.error(sb.toString(), e);
			}
				
			response.sendError(HttpServletResponse.SC_BAD_REQUEST);
		}
		
	}

	public void destroy() {

	}


}
