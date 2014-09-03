/**
 * RollingCalendar is a helper class. 
 * Given a periodicity type and the current time,
 * it computes the start of the next interval.
 */

package com.agloco.rolling;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;

/**
 * 
 * @author Erick Kong
 * @see RollingCalendar.java
 * @createDate: 2007-4-3
 * @version 1.0
 */

public class RollingCalendar extends GregorianCalendar
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static final int TOP_OF_TROUBLE = -1;

	public static final int TOP_OF_MINUTE = 0;

	public static final int TOP_OF_HOUR = 1;

	public static final int HALF_DAY = 2;

	public static final int TOP_OF_DAY = 3;

	public static final int TOP_OF_WEEK = 4;

	public static final int TOP_OF_MONTH = 5;

	int type = TOP_OF_TROUBLE;

	public RollingCalendar()
	{
		super();
	}

	public RollingCalendar(TimeZone tz, Locale locale)
	{
		super(tz, locale);
	}

	public void setType(int type)
	{
		this.type = type;
	}

	public long getNextCheckMillis(Date now)
	{
		return getNextCheckDate(now).getTime();
	}

	/**
	 * compute the next check time according now time
	 * 
	 * @param now
	 * @return Date
	 */
	public Date getNextCheckDate(Date now)
	{
		this.setTime(now);

		switch (type)
		{
		case TOP_OF_MINUTE:
			this.set(Calendar.SECOND, 0);
			this.set(Calendar.MILLISECOND, 0);
			this.add(Calendar.MINUTE, 1);
			break;
		case TOP_OF_HOUR:
			this.set(Calendar.MINUTE, 0);
			this.set(Calendar.SECOND, 0);
			this.set(Calendar.MILLISECOND, 0);
			this.add(Calendar.HOUR_OF_DAY, 1);
			break;
		case HALF_DAY:
			this.set(Calendar.MINUTE, 0);
			this.set(Calendar.SECOND, 0);
			this.set(Calendar.MILLISECOND, 0);
			int hour = get(Calendar.HOUR_OF_DAY);
			if (hour < 12)
			{
				this.set(Calendar.HOUR_OF_DAY, 12);
			}
			else
			{
				this.set(Calendar.HOUR_OF_DAY, 0);
				this.add(Calendar.DAY_OF_MONTH, 1);
			}
			break;
		case TOP_OF_DAY:
			this.set(Calendar.HOUR_OF_DAY, 0);
			this.set(Calendar.MINUTE, 0);
			this.set(Calendar.SECOND, 0);
			this.set(Calendar.MILLISECOND, 0);
			this.add(Calendar.DATE, 1);
			break;
		case TOP_OF_WEEK:
			this.set(Calendar.DAY_OF_WEEK, getFirstDayOfWeek());
			this.set(Calendar.HOUR_OF_DAY, 0);
			this.set(Calendar.MINUTE, 0);
			this.set(Calendar.SECOND, 0);
			this.set(Calendar.MILLISECOND, 0);
			this.add(Calendar.WEEK_OF_YEAR, 1);
			break;
		case TOP_OF_MONTH:
			this.set(Calendar.DATE, 1);
			this.set(Calendar.HOUR_OF_DAY, 0);
			this.set(Calendar.MINUTE, 0);
			this.set(Calendar.SECOND, 0);
			this.set(Calendar.MILLISECOND, 0);
			this.add(Calendar.MONTH, 1);
			break;
		default:
			throw new IllegalStateException("Unknown periodicity type.");
		}
		return getTime();
	}
	
	/**
	 * Compute the rolling type.
	 * Here are some datePattern-type correspondences
	 * datePattern	type
	 * 'yyyyMMdd'	day
	 * 'yyyyww'		week
	 * 'yyyyMM'		month
	 * ......
	 * 
	 * @param datePattern
	 * @param timeZone
	 * @return int: rolling type
	 */
	public static int computeCheckPeriod(String datePattern, TimeZone timeZone)
	{
		if(datePattern == null || datePattern.length()<1)
			datePattern = "yyyyMMdd";
		RollingCalendar rollingCalendar = new RollingCalendar(timeZone,
				Locale.ENGLISH);
		// set sate to 1970-01-01 00:00:00 GMT
		Date epoch = new Date(0);
		if (datePattern != null)
		{
			for (int i = TOP_OF_MINUTE; i <= TOP_OF_MONTH; i++)
			{
				SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
						datePattern);
				simpleDateFormat.setTimeZone(timeZone); // do all date
				// formatting in GMT
				String r0 = simpleDateFormat.format(epoch);
				rollingCalendar.setType(i);
				Date next = new Date(rollingCalendar.getNextCheckMillis(epoch));
				String r1 = simpleDateFormat.format(next);
				// System.out.println("Type = "+i+", r0 = "+r0+", r1 = "+r1);
				if (r0 != null && r1 != null && !r0.equals(r1))
				{
					return i;
				}
			}
		}
		return TOP_OF_TROUBLE; // Deliberately head for trouble...
	}


}