package com.agloco.service;

import java.util.Calendar;
import java.util.List;

import com.agloco.model.VBTimeSubTotal;
import com.agloco.model.VBTimeTotal;
import com.agloco.model.VBTimeTrack;
import com.agloco.model.VBURLTrack;
import com.agloco.web.service.model.TimeTrackListReq;

public interface SurfService extends BaseService {

	public void addBatchTimeTrack(TimeTrackListReq ttList, String timeTrackTable, String timeTrackPattern, String subTotalTable, String subTotalPattern) throws Exception;

		//surf total
	public void createTimeTotal(VBTimeTotal sTotal);
	public void deleteTimeTotal(VBTimeTotal sTotal);
	public void updateTimeTotal(VBTimeTotal sTotal);
	public VBTimeTotal getTimeTotal(Long memberId);
	
	//surf subtotal
	public void createTimeSubTotal(VBTimeSubTotal sSubtotal);
	public void deleteTimeSubTotal(VBTimeSubTotal sSubtotal);
	public void updateTimeSubTotal(VBTimeSubTotal sSubtotal);
	public VBTimeSubTotal getTimeSubTotal(Long memberId, Calendar surfDate);
//	public VBTimeSubTotal getTimeSubTotal(String sql);
	public List listTimeSubTotal(Long memberId);
	
	
	//time track
	public void createTimeTrack(VBTimeTrack timeTrack);
	public void deleteTimeTrack(VBTimeTrack timeTrack);
	public void updateTimeTrack(VBTimeTrack timeTrack);
	public VBTimeTrack getTimeTrack(Long trackId);
	public List listTimeTrack(Long memberId);

	//URL track
	public void createURLTrack(VBURLTrack urlTrack);
	public void deleteURLTrack(VBURLTrack urlTrack);
	public void updateURLTrack(VBURLTrack urlTrack);
	public VBURLTrack getURLTrack(Long trackId);
	public List listURLTrack(Long memberId);
}
