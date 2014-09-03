
package com.agloco.dao.util;

import java.util.Date;

import org.springframework.context.ApplicationContext;

import com.agloco.dao.RollingDAO;
import com.agloco.spring.SpringUtil;

/**
 * 
 * @author Erick Kong
 * @see RollingDAOUtil.java
 * @createDate: 2007-4-4
 * @version 1.0
 */

public class RollingDAOUtil
{
	private RollingDAO rollingDao;

	public static void createNewTable(String className,String newTableName)throws Exception{
		getRollingDao().createNewTable(className, newTableName);
	}
	
	public static boolean checkTableExsit(String tableName) {
		return getRollingDao().checkTableExsit(tableName);
	}

	public static Date getTableCreateDate(String tableName) throws Exception
	{
		return getRollingDao().getTableCreateDate(tableName);
	}
	
	public static RollingDAO getRollingDao() {
		ApplicationContext ctx = SpringUtil.getContext();
		RollingDAOUtil util = (RollingDAOUtil) ctx
				.getBean(RollingDAOUtil.class.getName());
		return util.rollingDao;
	}

	public static void alterTableName(String oldName, String newName)throws Exception {
		getRollingDao().alterTableName(oldName, newName);
	}

	public void setRollingDao(RollingDAO rollingDao) {
		this.rollingDao = rollingDao;
	}
	
}

