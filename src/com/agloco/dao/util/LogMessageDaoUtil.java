package com.agloco.dao.util;

import java.util.List;

import org.springframework.context.ApplicationContext;

import com.agloco.dao.LogMessageDao;
import com.agloco.log4j.MessageModel;
import com.agloco.spring.SpringUtil;

public class LogMessageDaoUtil {
	private LogMessageDao logMessageDao;

	public static void addLogMessage(MessageModel messageMo) {
		getLogMessageDao().addLogMessage(messageMo);
	}

	public static String getLatestMaxCreateDate(String tableName) {
		return getLogMessageDao().getLatestMaxCreateDate(tableName);
	}

//	public static void createNewLogTable(String newTableName)throws Exception {
//		getLogMessageDao().createNewLogTable(newTableName);
//	}
	public static void createNewLogTable(String className,String newTableName)throws Exception{
		getLogMessageDao().createNewLogTable(className, newTableName);
	}
	public static Long getLatestMaxLogID(String tableName) {
		return getLogMessageDao().getLatestMaxLogID(tableName);
	}

	public static void alterLogTableIncrement(String tableName, Long incrementNo)throws Exception {
		getLogMessageDao().alterLogTableIncrement(tableName, incrementNo);
	}

	public static void alterTableName(String oldName, String newName)throws Exception {
		getLogMessageDao().alterTableName(oldName, newName);
	}
	public static boolean checkTableExsit(String tableName) {
		return getLogMessageDao().checkTableExsit(tableName);
	}

	public static LogMessageDao getLogMessageDao() {
		ApplicationContext ctx = SpringUtil.getContext();
		LogMessageDaoUtil util = (LogMessageDaoUtil) ctx
				.getBean(LogMessageDaoUtil.class.getName());
		return util.logMessageDao;
	}

	public static List queryLogInfo(String sql) throws Exception
	{
		return getLogMessageDao().queryLogInfo(sql);
	}
	
	public void setLogMessageDao(LogMessageDao logMessageDao) {
		this.logMessageDao = logMessageDao;
	}
	
}
