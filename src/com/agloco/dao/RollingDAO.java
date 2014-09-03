package com.agloco.dao;

import java.util.Date;

/**
 * 
 * @author Erick Kong
 * @see RollingDAO.java
 * @createDate: 2007-4-4
 * @version 1.0
 */

public interface RollingDAO
{
	public void createNewTable(String className, String newTableName) throws Exception;
	public void alterTableName(String oldName,String newName)throws Exception;
	public boolean checkTableExsit(String tableName);
	public Date getTableCreateDate(String tableName) throws Exception;
}
