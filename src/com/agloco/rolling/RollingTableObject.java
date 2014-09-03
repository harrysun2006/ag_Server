package com.agloco.rolling;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import com.agloco.log4j.LogUtil;
import com.agloco.model.VBConfig;
import com.agloco.service.util.RollingServiceUtil;
import com.agloco.service.util.ConfigServiceUtil;

/**
 * RollingTableObject is used to rolling the table that need to be created according the date
 * datePattern, className and className are the necessary parameters
 * 
 * @author Erick Kong
 * @see RollingTableObject.java
 * @createDate: 2007-4-4
 * @version 1.0
 */

public class RollingTableObject
{
	final TimeZone gmtTimeZone = TimeZone.getTimeZone("GMT");
	
	/**
	 * The date pattern. By default,
	 * the pattern is set to "'_'yyyyMMdd" meaning daily rollover.
	 */
	private String datePattern = "'_'yyyyMMdd";
	
	private String tableName = "";
	
	private String className = ""; 

	private String scheduledTableName;

	private long nextCheck = System.currentTimeMillis() - 1;

	private Date now = new Date();

	private RollingCalendar rc = new RollingCalendar();

	private SimpleDateFormat sdf;

	private String rollingTableName = "viewbar.rolling.";
	private boolean valueInit = false;
	public void init()
	{
		if (datePattern != null && tableName != null)
		{
			now.setTime(System.currentTimeMillis());
			sdf = new SimpleDateFormat(datePattern);
			int type = computeCheckPeriod();
			rc.setType(type);
			this.scheduledTableName = tableName + sdf.format(now);
			
		}
		else
		{
			LogUtil
					.error("Either tableName or DatePattern options are not set");
		}
	}

	/**
	 * Check if the table need to be created
	 * if true, backup old table and create new table
	 */
	public void rolling()
	{
		if (!valueInit){
			ConfigServiceUtil.updateVBConfig(rollingTableName + tableName, scheduledTableName);
			valueInit= true;
		}
		
		long n = System.currentTimeMillis();
		if (n >= nextCheck)
		{
			try
			{
				now.setTime(n);
				createNewTable();
				
				
				nextCheck = rc.getNextCheckMillis(now);
			}
			catch (Exception e)
			{
				LogUtil.error("Create new table failed.", e);
			}
		}
		
	}

	private void createNewTable() throws Exception
	{
		
		VBConfig config = 	ConfigServiceUtil.getVBConfig(rollingTableName + tableName);
		scheduledTableName =config.getValue();
		
		if (datePattern == null)
		{
			LogUtil.error("Missing DatePattern option in rollOver().");
			return;
		}

		String datedTableName = tableName + sdf.format(now);

		if (scheduledTableName.equals(datedTableName))
		{
			// Create new table
			if(!RollingServiceUtil.checkTableExsit(tableName))
				RollingServiceUtil.createNewTable(className, tableName);
			else
			{
				Date createDate = getTableCreateDate(tableName);
				String oldTableName = tableName + sdf.format(createDate);
				if(!oldTableName.equals(datedTableName) && !RollingServiceUtil.checkTableExsit(oldTableName))
				{
					if(!RollingServiceUtil.checkTableExsit(oldTableName))
					{
						RollingServiceUtil.alterTableName(tableName, oldTableName);
						RollingServiceUtil.createNewTable(className, tableName);
					}
					else {
						LogUtil.warn("old Table " + oldTableName
								+ " has already exists." + "Now time is: "
								+ sdf.format(now) + ". scheduled time is:  "
								+ scheduledTableName);
					}
				}
			}
			return;
		}
		else
		{
			//Backup old table and create new table
			if(!RollingServiceUtil.checkTableExsit(tableName))
				RollingServiceUtil.createNewTable(className, tableName);
			else
			{
				if(!RollingServiceUtil.checkTableExsit(scheduledTableName))
				{
					RollingServiceUtil.alterTableName(tableName, scheduledTableName);
					RollingServiceUtil.createNewTable(className, tableName);
				}
				else {
					LogUtil.warn("scheduled Table " + scheduledTableName
							+ " has already exists." + "Now time is: "
							+ sdf.format(now) + ". scheduled time is:  "
							+ scheduledTableName);
				}
			}
			//change by locker 2007/08/08
			ConfigServiceUtil.updateVBConfig(rollingTableName + tableName, datedTableName);
//			scheduledTableName = datedTableName;
		}
	}
	
	private Date getTableCreateDate(String tableName)
	{
		try
		{
			return RollingServiceUtil.getTableCreateDate(tableName);
		}
		catch (Exception e)
		{
			// TODO Auto-generated catch block
			LogUtil.error("Error",e);
			return null;
		}
	}
	
	private int computeCheckPeriod()
	{
		return RollingCalendar.computeCheckPeriod(datePattern,gmtTimeZone);
	}

	public void setDatePattern(String datePattern)
	{
		this.datePattern = datePattern;
	}

	public void setClassName(String className)
	{
		this.className = className;
	}

	public void setTableName(String tableName)
	{
		this.tableName = tableName;
	}

	public String getClassName()
	{
		return className;
	}

	public String getDatePattern()
	{
		return datePattern;
	}

	public String getTableName()
	{
		return tableName;
	}
}

