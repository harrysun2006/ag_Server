package com.agloco.web.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * 
 * @author Erick Kong
 * @see BaseService.java
 * @createDate: 2007-4-3
 * @version 1.0
 */
public interface BaseService
{
	public void run(HttpServletRequest request, HttpServletResponse response, List fileItems) throws Exception;
}
