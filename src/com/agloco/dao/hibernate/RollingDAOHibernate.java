package com.agloco.dao.hibernate;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.agloco.dao.RollingDAO;
import com.agloco.util.HibernateUtil;

/**
 * 
 * @author Erick Kong
 * @see RollingDAOHibernate.java
 * @createDate: 2007-4-4
 * @version 1.0
 */

public class RollingDAOHibernate extends HibernateDaoSupport implements
		RollingDAO
{
	public void createNewTable(String className, String newTableName)
			throws Exception
	{
//		try
//		{
//			Connection conn = getSession().connection();
//			Statement sql = conn.createStatement();
//
//			String createSQL = HibernateUtil.generateSchemaCreationScript(
//					className, newTableName);
//
//			sql.execute(createSQL);
//		}
//		catch (Exception ex)
//		{
//			throw ex;
//		}
		HibernateUtil.createTable(className, newTableName, "ENGINE=InnoDB");
	}

	public boolean checkTableExsit(String tableName)
	{
		try
		{
			Connection conn = getSession().connection();
			Statement sql = conn.createStatement();

			String checkSQL = "select * from " + tableName + " where 1=0 ";

			sql.execute(checkSQL);
			
			return true;
		}
		catch (Exception ex)
		{
			return false;
		}
	}
	
	public long getTimeDiff() throws Exception
	{
		try
		{
			Connection conn = getSession().connection();
			ResultSet rs = null;
			String sql = "SELECT UNIX_TIMESTAMP(UTC_TIMESTAMP()) - UNIX_TIMESTAMP(CURRENT_TIMESTAMP()) diff";
			PreparedStatement stat = (PreparedStatement) conn.prepareStatement(sql);
					
			rs = stat.executeQuery();
			if(rs!=null && rs.next())
			{
				return rs.getLong("diff");
			}
		}
		catch (Exception ex)
		{
			throw ex;
		}
		return 0;
	}
	
	public Date getTableCreateDate(String tableName) throws Exception
	{
		Date date = null;
		try
		{
			long diff = getTimeDiff()*1000;
			Connection conn = getSession().connection();
			ResultSet rs = null;
			String sql = "show table status like ?";
			PreparedStatement stat = (PreparedStatement) conn.prepareStatement(sql);
			stat.setString(1, tableName);
					
			rs = stat.executeQuery();
			if(rs!=null && rs.next())
			{
				date = rs.getTimestamp("Create_time");
				if(diff!=0)
					date.setTime(date.getTime()+diff);
			}
		}
		catch (Exception ex)
		{
			throw ex;
		}
		return date;
	}

	public void alterTableName(String oldName, String newName) throws Exception
	{

		try
		{
			Connection conn = getSession().connection();
			Statement sql = conn.createStatement();
			String alterTableNameSQL = " ALTER TABLE " + oldName
					+ " RENAME TO " + newName;
			sql.execute(alterTableNameSQL);
		}
		catch (Exception ex)
		{
			throw ex;
		}
	}

}
