package com.agloco.service;

import java.util.Date;


public interface RollingService {
	public void createNewTable(String className,String newTableName)throws Exception;
	public void alterTableName(String oldName,String newName)throws Exception;
	public boolean checkTableExsit(String tableName);
	public void backupAndCreateTable(String className, String tableName,String backupTableName) throws Exception;
	public Date getTableCreateDate(String tableName) throws Exception;
}
