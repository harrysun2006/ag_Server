package com.agloco.service.impl;

import java.util.Date;

import org.hibernate.HibernateException;

import com.agloco.dao.util.RollingDAOUtil;
import com.agloco.service.RollingService;

public class RollingServiceImpl implements RollingService
{
	public void alterTableName(String oldName, String newName) throws Exception
	{
		RollingDAOUtil.alterTableName(oldName, newName);
	}

	public void createNewTable(String className, String newTableName)
			throws Exception
	{
		RollingDAOUtil.createNewTable(className, newTableName);
	}

	public boolean checkTableExsit(String tableName)
	{
		return RollingDAOUtil.checkTableExsit(tableName);
	}

	public void backupAndCreateTable(String className, String tableName, String backupTableName)
			throws Exception
	{
		if (!RollingDAOUtil.checkTableExsit(backupTableName))
		{
			RollingDAOUtil.alterTableName(tableName, backupTableName);
			RollingDAOUtil.createNewTable(className, tableName);
		}
		else
		{
			throw new HibernateException("Table is exists:" + backupTableName);
		}
	}

	public Date getTableCreateDate(String tableName) throws Exception
	{
		return RollingDAOUtil.getTableCreateDate(tableName);
	}
}
