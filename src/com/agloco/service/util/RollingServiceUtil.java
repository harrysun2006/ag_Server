package com.agloco.service.util;

import java.util.Date;

import org.springframework.context.ApplicationContext;

import com.agloco.service.RollingService;
import com.agloco.spring.SpringUtil;

public class RollingServiceUtil {
	private RollingService rollingService;

	public static void backupAndCreateTable(String className, String tableName,String backupTableName) throws Exception{
		getRollingService().backupAndCreateTable(className, tableName,backupTableName);
	}

	public static void createNewTable(String className,String tableName) throws Exception {
		 getRollingService().createNewTable(className, tableName);
	}

	public static void alterTableName(String oldName, String newName)throws Exception {
		getRollingService().alterTableName(oldName, newName);
	}
	public static boolean checkTableExsit(String tableName){
		return getRollingService().checkTableExsit(tableName);
	}
	public static Date getTableCreateDate(String tableName) throws Exception
	{
		return getRollingService().getTableCreateDate(tableName);
	}
	public static RollingService getRollingService() {
		ApplicationContext ctx = SpringUtil.getContext();
		RollingServiceUtil util = (RollingServiceUtil) ctx
				.getBean(RollingServiceUtil.class.getName());
		return util.rollingService;
	}
	
	public void setRollingService(RollingService rollingService) {
		this.rollingService = rollingService;
	}

}
