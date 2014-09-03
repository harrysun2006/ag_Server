package com.agloco.dao;

import java.util.Calendar;
import java.util.List;

import com.agloco.model.VBTimeSubTotal;
import com.agloco.model.VBTimeTotal;
import com.agloco.model.VBTimeTrack;
import com.agloco.model.VBURLTrack;

public interface SurfDAO {

	//surf total
	public void createTimeTotal(VBTimeTotal sTotal);
	public void deleteTimeTotal(VBTimeTotal sTotal);
	public void updateTimeTotal(VBTimeTotal sTotal);
	public VBTimeTotal getTimeTotal(Long memberId);
	
	//surf subtotal
	public void createTimeSubTotal(VBTimeSubTotal subtotal, String tableName) throws Exception;
	public void createTimeSubTotal(VBTimeSubTotal subtotal);
	public void deleteTimeSubTotal(VBTimeSubTotal subtotal);
	public void updateTimeSubTotal(VBTimeSubTotal subtotal);
	public void updateTimeSubTotal(VBTimeSubTotal subtotal, String tableName) throws Exception;
	public VBTimeSubTotal getTimeSubTotal(Long memberId, Calendar surfDate);
	public long getTimeSubTotal(String tableName, Long memberId, Calendar surfDate) throws Exception;
	public long getTimeSubTotal(String sql);
	public List listTimeSubTotal(Long memberId);
	
	
	//time track
	public void createTimeTrack(VBTimeTrack timeTrack);
	public void createTimeTrack(VBTimeTrack timeTrack, String tableName) throws Exception;
	public void createTimeTrackBySQL(String sql) throws Exception;
	public void deleteTimeTrack(VBTimeTrack timeTrack);
	public void updateTimeTrack(VBTimeTrack timeTrack);
	public VBTimeTrack getTimeTrack(Long trackId);
	public boolean checkTimeTrack(Long memberId, Long submitId);
	public boolean checkTimeTrack(Long memberId, Long submitId, String tableName) throws Exception;
	public List listTimeTrack(Long memberId);
	
	//url track
	public void createURLTrack(VBURLTrack urlTrack);
	public void deleteURLTrack(VBURLTrack urlTrack);
	public void updateURLTrack(VBURLTrack urlTrack);
	public VBURLTrack getURLTrack(Long trackId);
	public List listURLTrack(Long memberId);
	
	//Common
	public void executeSQL(String sql) throws Exception;
	
}
