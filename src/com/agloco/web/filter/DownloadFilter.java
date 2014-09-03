package com.agloco.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author author E-mail:zhaon12@gmail.com
 * @version 1.0
 * @createDate createDate:Apr 24, 2007 10:09:55 AM
 * @content
 */
public class DownloadFilter implements Filter {

	public void destroy() {

	}

	public void doFilter(ServletRequest servletRequest,
			ServletResponse servletResponse, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;

		if (request.isSecure()) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST);
			return;
		}
		chain.doFilter(servletRequest, servletResponse);
	}

	public void init(FilterConfig arg0) throws ServletException {

	}

}
