package com.agloco.dao;

import java.util.List;

import com.agloco.log4j.MessageModel;

public interface LogMessageDao {
	
	public void addLogMessage(MessageModel messageMo);
	public String getLatestMaxCreateDate(String tableName);
	public void createNewLogTable(String className,String newTableName)throws Exception;
	public Long getLatestMaxLogID(String tableName);
	public void alterLogTableIncrement(String tableName,Long incrementNo)throws Exception;
	public void alterTableName(String oldName,String newName)throws Exception;
	public boolean checkTableExsit(String tableName);
	public List queryLogInfo(String sql) throws Exception;
}
