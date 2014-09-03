package com.agloco.dao.hibernate;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.agloco.dao.LogMessageDao;
import com.agloco.log4j.MessageModel;
import com.agloco.util.HibernateUtil;

public class LogMessageDaoHibernate extends HibernateDaoSupport implements
		LogMessageDao
{

	public void addLogMessage(MessageModel messageMo)
	{
		// TODO Auto-generated method stub
		getHibernateTemplate().save(messageMo);
	}

	public String getLatestMaxCreateDate(String tableName)
	{

		String getLatestMaxCreateDateSQL = "select max(createDate) from "
				+ tableName;
		Query q = getSession().createSQLQuery(getLatestMaxCreateDateSQL);

		List temList = null;

		temList = q.list();
		if (temList == null)
		{
			return "";
		}
		else
		{
			return temList.get(0).toString();
		}
	}

	public Long getLatestMaxLogID(String tableName)
	{
		String getLatestMaxLogIDSQL = "select max(logId) from " + tableName;
		Query q = getSession().createSQLQuery(getLatestMaxLogIDSQL);
		List temList = null;
		temList = q.list();
		if (temList == null)
		{
			return Long.valueOf("0");
		}
		else
		{
			return Long.valueOf(temList.get(0).toString());
		}
	}

	public void alterLogTableIncrement(String tableName, Long incrementNo)
			throws Exception
	{

		try
		{
			Connection conn = getSession().connection();
			Statement sql = conn.createStatement();
			String alterTableIncrementSQL = " ALTER TABLE " + tableName
					+ " AUTO_INCREMENT = " + incrementNo;
			sql.execute(alterTableIncrementSQL);
		}
		catch (Exception ex)
		{
			throw ex;
		}

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

	public boolean checkTableExsit(String tableName)
	{
		try
		{
			Connection conn = getSession().connection();
			Statement stat = conn.createStatement();
			String checkSQL = "select * from " + tableName + " where 1=0 ";
			stat.execute(checkSQL);
			return true;
		}
		catch (Exception ex)
		{
			return false;
		}
	}

	public List getLogList(final String sql, final Object[] params){
		List list = (List)getHibernateTemplate().execute(new HibernateCallback(){

			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				// TODO Auto-generated method stub
				Query q = getSession().createQuery(sql);

				if (params != null) {
					for (int i = 0; i < params.length; i++) {
						q.setParameter(i, params[i]);
					}
				}
				return q.list();
			}
			
		});
		return list;
	}
	
	public List queryLogInfo(String sql) throws Exception
	{
		Query q = getSession().createSQLQuery(sql);
		return q.list();
	}

	public void createNewLogTable(String className, String newTableName)
			throws Exception
	{
//		try
//		{
//			Connection conn = getSession().connection();
//			Statement sql = conn.createStatement();
//
//			 String createSQL = "CREATE TABLE `"
//			 + newTableName
//			 + "`("
//			 + "`logId` int(11) NOT NULL auto_increment,"
//			 + "`createDate` datetime NOT NULL default '0000-00-00 00:00:00',"
//			 + "`thread` varchar(75) default '',"
//			 + "`priority` varchar(75) default '',"
//			 + "`category` varchar(75) default '',"
//			 + "`message` varchar(255) default '',"
//			 + "`operate` varchar(15) default '',"
//			 + "`description` varchar(255) default '',"
//			 + "`userId` varchar(75) default '',"
//			 + "`emailAddress` varchar(100) default '',"
//			 + "`exception` varchar(255) default '',"
//			 + "`memberCode` varchar(15) default '',"
//			 + "`referralCode` varchar(15) default '',"
//			 + "`IP` varchar(24) default '',"
//			 + "PRIMARY KEY (`logId`))";
//			String createSQL = HibernateUtil.generateSchemaCreationScript(
//					className, newTableName);
//
//			sql.execute(createSQL);
//		}
//		catch (Exception ex)
//		{
//			throw ex;
//		}
		HibernateUtil.createTable(className, newTableName, "ENGINE=MyISAM");
	}
	

}
