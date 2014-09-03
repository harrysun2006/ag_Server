package com.agloco.dao.util;

import java.util.Calendar;
import java.util.List;

import org.springframework.context.ApplicationContext;

import com.agloco.dao.SurfDAO;
import com.agloco.model.VBTimeSubTotal;
import com.agloco.model.VBTimeTotal;
import com.agloco.model.VBTimeTrack;
import com.agloco.model.VBURLTrack;
import com.agloco.spring.SpringUtil;

public class SurfDAOUtil {

	//surf total
	public static void createTimeTotal(VBTimeTotal sTotal){
		getSurfDAO().createTimeTotal(sTotal);
	}
	public static void deleteTimeTotal(VBTimeTotal sTotal){
		getSurfDAO().deleteTimeTotal(sTotal);
	}
	public static void updateTimeTotal(VBTimeTotal sTotal){
		getSurfDAO().updateTimeTotal(sTotal);
	}
	public static VBTimeTotal getTimeTotal(Long memberId){
		return getSurfDAO().getTimeTotal(memberId);
	}
	
	//surf subtotal
	public static void createTimeSubTotal(VBTimeSubTotal subtotal){
		getSurfDAO().createTimeSubTotal(subtotal);
	}
	public static void createTimeSubTotal(VBTimeSubTotal subtotal, String tableName) throws Exception
	{
		getSurfDAO().createTimeSubTotal(subtotal, tableName);
	}

	public static void createTimeSubTotal(String sql) throws Exception{
		getSurfDAO().executeSQL(sql);
	}
	public static void deleteTimeSubTotal(VBTimeSubTotal subtotal){
		getSurfDAO().deleteTimeSubTotal(subtotal);
	}
	public static void updateTimeSubTotal(VBTimeSubTotal subtotal){
		getSurfDAO().updateTimeSubTotal(subtotal);
	}
	public static void updateTimeSubTotal(VBTimeSubTotal subtotal, String tableName) throws Exception{
		getSurfDAO().updateTimeSubTotal(subtotal,tableName);
	}
	public static VBTimeSubTotal getTimeSubTotal(Long memberId, Calendar surfDate){
		return getSurfDAO().getTimeSubTotal(memberId, surfDate);
	}
	public static long getTimeSubTotal(String tableName, Long memberId, Calendar surfDate)  throws Exception{
		return getSurfDAO().getTimeSubTotal(tableName, memberId, surfDate);
	}
	public static long getTimeSubTotal(String sql){
		return getSurfDAO().getTimeSubTotal(sql);
	}
	public static List listTimeSubTotal(Long memberId){
		return getSurfDAO().listTimeSubTotal(memberId);
	}
	
	
	//time track
	public static void createTimeTrack(VBTimeTrack timeTrack){
		getSurfDAO().createTimeTrack(timeTrack);
	}
	
	public static void createTimeTrack(VBTimeTrack timeTrack, String tableName) throws Exception
	{
		getSurfDAO().createTimeTrack(timeTrack, tableName);
	}
	
	public static void createTimeTrackBySQL(String sql) throws Exception{
		getSurfDAO().createTimeTrackBySQL(sql);
	}
	public static void deleteTimeTrack(VBTimeTrack timeTrack){
		getSurfDAO().deleteTimeTrack(timeTrack);
	}
	public static void updateTimeTrack(VBTimeTrack timeTrack){
		getSurfDAO().updateTimeTrack(timeTrack);
	}
	public static VBTimeTrack getTimeTrack(Long trackId){
		return getSurfDAO().getTimeTrack(trackId);
	}
	public static boolean checkTimeTrack(Long memberId, Long submitId)
	{
		return getSurfDAO().checkTimeTrack(memberId, submitId);
	}
	public static boolean checkTimeTrack(Long memberId, Long submitId, String tableName) throws Exception
	{
		return getSurfDAO().checkTimeTrack(memberId, submitId, tableName);
	}
	public static List listTimeTrack(Long memberId){
		return getSurfDAO().listTimeTrack(memberId);
	}
	
	//URL track
	public static void createURLTrack(VBURLTrack urlTrack){
		getSurfDAO().createURLTrack(urlTrack);
	}
	public static void deleteURLTrack(VBURLTrack urlTrack){
		getSurfDAO().deleteURLTrack(urlTrack);
	}
	public static void updateURLTrack(VBURLTrack urlTrack){
		getSurfDAO().updateURLTrack(urlTrack);
	}
	public static VBURLTrack getURLTrack(Long trackId){
		return getSurfDAO().getURLTrack(trackId);
	}
	public static List listURLTrack(Long memberId){
		return getSurfDAO().listURLTrack(memberId);
	}
	
	
	public static void executeSQL(String sql) throws Exception
	{
		getSurfDAO().executeSQL(sql);
	}
	
	
	
	private static SurfDAO getSurfDAO(){
		ApplicationContext ctx = SpringUtil.getContext();
		SurfDAOUtil util = (SurfDAOUtil) ctx.getBean(SurfDAOUtil.class.getName());
		return util.surfDAO;
	}
	public void setSurfDAO(SurfDAO surfDAO) {
		this.surfDAO = surfDAO;
	}
	private SurfDAO surfDAO;
	
}
