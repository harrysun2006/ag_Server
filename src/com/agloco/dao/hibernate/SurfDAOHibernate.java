package com.agloco.dao.hibernate;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;

import org.hibernate.Query;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.agloco.dao.SurfDAO;
import com.agloco.model.VBTimeSubTotal;
import com.agloco.model.VBTimeTotal;
import com.agloco.model.VBTimeTrack;
import com.agloco.model.VBURLTrack;

public class SurfDAOHibernate extends HibernateDaoSupport implements SurfDAO {

	//surf total
	public void createTimeTotal(VBTimeTotal sTotal) {
		getHibernateTemplate().save(sTotal);
	}
	public void deleteTimeTotal(VBTimeTotal sTotal) {
		getHibernateTemplate().delete(sTotal);
	}
	public VBTimeTotal getTimeTotal(Long memberId) {
		return (VBTimeTotal) getHibernateTemplate().get(VBTimeTotal.class, memberId);
	}
	public void updateTimeTotal(VBTimeTotal sTotal) {
		getHibernateTemplate().saveOrUpdate(sTotal);
	}

	
	//surf subtotal
	public void createTimeSubTotal(VBTimeSubTotal sSubtotal) {
		getHibernateTemplate().save(sSubtotal);
	}
	
	public void createTimeSubTotal(VBTimeSubTotal subtotal, String tableName) throws Exception
	{
		try
		{
			Calendar surfDate = subtotal.getSurfDate();
			Connection conn = getSession().connection();
			String sql = createSubTotalSQL(tableName);
			PreparedStatement stat = (PreparedStatement) conn.prepareStatement(sql);
			stat.setLong(1, subtotal.getMemberId().longValue());
			stat.setDate(2, new Date(subtotal.getSurfDate().getTimeInMillis()));
			stat.setInt(3, surfDate.get(Calendar.YEAR));
			stat.setInt(4, surfDate.get(Calendar.MONTH)+1);
			stat.setInt(5, surfDate.get(Calendar.DATE));
			stat.setLong(6, subtotal.getSecond().longValue());
					
			stat.execute();
		}
		catch (Exception ex)
		{
			throw ex;
		}
	}
	
	public void updateTimeSubTotal(VBTimeSubTotal subtotal, String tableName) throws Exception
	{
		try
		{
			Calendar surfDate = subtotal.getSurfDate();
			Connection conn = getSession().connection();
			String sql = updateSubTotalSQL(tableName);
			PreparedStatement stat = (PreparedStatement) conn.prepareStatement(sql);
			stat.setLong(1, subtotal.getSecond().longValue());
			stat.setLong(2, subtotal.getMemberId().longValue());
			stat.setInt(3, surfDate.get(Calendar.YEAR));
			stat.setInt(4, surfDate.get(Calendar.MONTH)+1);
			stat.setInt(5, surfDate.get(Calendar.DATE));
					
			stat.execute();
		}
		catch (Exception ex)
		{
			throw ex;
		}
	}
	
	public void deleteTimeSubTotal(VBTimeSubTotal sSubtotal) {
		getHibernateTemplate().delete(sSubtotal);
	}
	public VBTimeSubTotal getTimeSubTotal(Long memberId, Calendar surfDate) {
		String sql = "from VBTimeSubTotal vbst where vbst.memberId=:memberId and vbst.year=:year and vbst.month=:month and vbst.day=:day";
		Query q = getSession().createQuery(sql);
		q.setParameter("memberId", memberId);
		q.setParameter("year", new Integer(surfDate.get(Calendar.YEAR)));
		q.setParameter("month", new Integer(surfDate.get(Calendar.MONTH)+1));
		q.setParameter("day", new Integer(surfDate.get(Calendar.DATE)));
		List list = q.list();
		if(list != null && list.size() > 0){
			return (VBTimeSubTotal) list.iterator().next();
		}

		return null;
	}
	public long getTimeSubTotal(String tableName, Long memberId, Calendar surfDate) throws Exception {
		try
		{
			Connection conn = getSession().connection();
			ResultSet rs = null;
			String sql = "select second_ from "+tableName+" where memberId=? and year=? " +
					"and month=? and day=?";
			PreparedStatement stat = (PreparedStatement) conn.prepareStatement(sql);
			stat.setLong(1, memberId.longValue());
			stat.setInt(2, surfDate.get(Calendar.YEAR));
			stat.setInt(3, surfDate.get(Calendar.MONTH)+1);
			stat.setInt(4, surfDate.get(Calendar.DATE));
					
			rs = stat.executeQuery();
			if(rs!=null && rs.next())
			{
				return rs.getLong("second_");
			}
		}
		catch (Exception ex)
		{
			throw ex;
		}
		return -1;
	}
	public long getTimeSubTotal(String sql) {
		Query q = getSession().createSQLQuery(sql);
		List list = q.list();
		if(list != null && list.size() > 0){
			return ((java.math.BigInteger)list.iterator().next()).longValue();
		}
		return -1;
	}
	public VBTimeSubTotal getTimeSubTotal(Long subtotalId) {
		return (VBTimeSubTotal)getHibernateTemplate().get(VBTimeSubTotal.class, subtotalId);
	}
	public List listTimeSubTotal(Long memberId) {
		return getHibernateTemplate().find("from VBTimeSubTotal ss where ss.memberId=?", memberId);
	}
	public void updateTimeSubTotal(VBTimeSubTotal sSubtotal) {
		getHibernateTemplate().saveOrUpdate(sSubtotal);
	}
	
	
	//time track
	public void createTimeTrack(VBTimeTrack timeTrack) {
		getHibernateTemplate().save(timeTrack);
	}
	public void createTimeTrack(VBTimeTrack timeTrack, String tableName) throws Exception
	{
		try
		{
			Connection conn = getSession().connection();
			String sql = createTimeTrackSQL(tableName);
			PreparedStatement stat = (PreparedStatement) conn.prepareStatement(sql);
			stat.setLong(1, timeTrack.getMemberId().longValue());
			stat.setLong(2, timeTrack.getSubmitId().longValue());
			stat.setTimestamp(3, new Timestamp(timeTrack.getBeginTime().getTimeInMillis()));
			stat.setTimestamp(4, new Timestamp(timeTrack.getEndTime().getTimeInMillis()));
			stat.setDouble(5, timeTrack.getPoint().doubleValue());
			stat.setString(6, timeTrack.getStatus());
					
			stat.execute();
		}
		catch (Exception ex)
		{
			throw ex;
		}
	}
	private String createTimeTrackSQL(String tableName)
	{
		StringBuffer sqlSb = new StringBuffer();
		sqlSb.append("insert into ");
		sqlSb.append(tableName);
		sqlSb.append(" (memberId,submitId,beginTime,endTime,point,status)");
		sqlSb.append(" values(?,?,?,?,?,?)");
		return sqlSb.toString();
	}
	private String updateSubTotalSQL(String tableName)
	{
		StringBuffer sqlSb = new StringBuffer();
		sqlSb.append("update ");
		sqlSb.append(tableName);
		sqlSb.append(" set second_ = ?");
		sqlSb.append(" where memberId = ? and year = ? and month = ? and day = ?");
		return sqlSb.toString();
	}
	private String createSubTotalSQL(String tableName)
	{
		StringBuffer sqlSb = new StringBuffer();
		sqlSb.append("insert into ");
		sqlSb.append(tableName);
		sqlSb.append(" (memberId,surfDate,year,month,day,second_)");
		sqlSb.append(" values(?,?,?,?,?,?)");
		return sqlSb.toString();
	}

	public void createTimeTrackBySQL(String sql) throws Exception {
		executeSQL(sql);
	}
	public void deleteTimeTrack(VBTimeTrack timeTrack) {
		getHibernateTemplate().save(timeTrack);
	}

	public VBTimeTrack getTimeTrack(Long trackId) {
		return (VBTimeTrack)getHibernateTemplate().get(VBTimeTrack.class, trackId);
	}

	public boolean checkTimeTrack(Long memberId, Long submitId) {
		
		List list = getHibernateTemplate().find("from VBTimeTrack st where st.memberId=? and st.submitId=?", new Object[]{memberId,submitId});
		
		if(list != null && list.size()>0)
			return true;
		return false;
	}

	public boolean checkTimeTrack(Long memberId, Long submitId, String tableName) throws Exception {
		
		Connection conn = getSession().connection();
		ResultSet rs = null;
		String sql = "select * from "+tableName+" where memberId=? and submitId=?";
		PreparedStatement stat = (PreparedStatement) conn.prepareStatement(sql);
		stat.setLong(1, memberId);
		stat.setLong(2, submitId);
				
		rs = stat.executeQuery();
		if(rs!=null && rs.next())
		{
			return true;
		}
		return false;
	}

	public List listTimeTrack(Long memberId) {
		return getHibernateTemplate().find("from VBTimeTrack st where st.memberId=?", memberId);
	}
	public void updateTimeTrack(VBTimeTrack timeTrack) {
		getHibernateTemplate().saveOrUpdate(timeTrack);
	}

	//url track
	public void createURLTrack(VBURLTrack urlTrack) {
		getHibernateTemplate().save(urlTrack);
	}
	public void deleteURLTrack(VBURLTrack urlTrack) {
		getHibernateTemplate().save(urlTrack);
	}

	public VBURLTrack getURLTrack(Long trackId) {
		return (VBURLTrack)getHibernateTemplate().get(VBURLTrack.class, trackId);
	}

	public List listURLTrack(Long memberId) {
		return getHibernateTemplate().find("from VBURLTrack st where st.memberId=?", memberId);
	}
	public void updateURLTrack(VBURLTrack urlTrack) {
		getHibernateTemplate().saveOrUpdate(urlTrack);
	}

	public void executeSQL(String sql) throws Exception
	{
		try
		{
			Connection conn = getSession().connection();
			Statement stat = conn.createStatement();
			stat.execute(sql);
		}
		catch (Exception ex)
		{
			throw ex;
		}
	}
}
