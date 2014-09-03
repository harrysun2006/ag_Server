package com.agloco.spring.advice;

import java.lang.reflect.Method;

import org.springframework.aop.AfterReturningAdvice;

import com.agloco.service.impl.FavoriteServiceImpl;
import com.agloco.service.impl.SearchServiceImpl;
import com.agloco.service.impl.ToolsServiceImpl;
import com.agloco.service.util.FavoriteServiceUtil;
import com.agloco.service.util.SearchServiceUtil;
import com.agloco.service.util.ToolsServiceUtil;
import com.agloco.web.util.FavoriteHashUtil;
import com.agloco.web.util.SearchEngineHashUtil;
import com.agloco.web.util.ToolsHashUtil;

/**
 * 
 * @author terry_zhao
 * @date Apr 20, 2007
 */
public class ClearCacheAdvice implements AfterReturningAdvice {

	public void afterReturning(Object returnValue, Method method,
			Object[] args, Object target) throws Throwable {
		
		if(SearchServiceImpl.class.getName().equals(target.getClass().getName())){
			SearchServiceUtil.clearSearchServiceUtilPool();
			SearchEngineHashUtil.clearSearchEngineHashPool();
			return;
		}
		
		if(FavoriteServiceImpl.class.getName().equals(target.getClass().getName())){
			FavoriteServiceUtil.clearFavoriteServiceUtilPool();
			FavoriteHashUtil.clearFavoriteHashPool();
			return;
		}
		
		if(ToolsServiceImpl.class.getName().equals(target.getClass().getName())){
			ToolsServiceUtil.clearToolsServiceUtilPool();
			ToolsHashUtil.clearToolsHashPool();
			return;
		}
		
	}

}
