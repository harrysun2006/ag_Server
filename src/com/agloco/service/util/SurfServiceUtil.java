package com.agloco.service.util;

import java.util.Calendar;
import java.util.List;

import org.springframework.context.ApplicationContext;

import com.agloco.model.VBTimeSubTotal;
import com.agloco.model.VBTimeTotal;
import com.agloco.model.VBTimeTrack;
import com.agloco.model.VBURLTrack;
import com.agloco.service.SurfService;
import com.agloco.spring.SpringUtil;
import com.agloco.web.service.model.TimeTrackListReq;

public class SurfServiceUtil {

//	surf total
	public static void createTimeTotal(VBTimeTotal sTotal){
		getSurfService().createTimeTotal(sTotal);
	}
	public static void deleteTimeTotal(VBTimeTotal sTotal){
		getSurfService().deleteTimeTotal(sTotal);
	}
	public static void updateTimeTotal(VBTimeTotal sTotal){
		getSurfService().updateTimeTotal(sTotal);
	}
	public static VBTimeTotal getTimeTotal(Long memberId){
		return getSurfService().getTimeTotal(memberId);
	}
	
	//surf subtotal
	public static void createTimeSubTotal(VBTimeSubTotal sSubtotal){
		getSurfService().createTimeSubTotal(sSubtotal);
	}
	public static void deleteTimeSubTotal(VBTimeSubTotal sSubtotal){
		getSurfService().deleteTimeSubTotal(sSubtotal);
	}
	public static void updateTimeSubTotal(VBTimeSubTotal sSubtotal){
		getSurfService().updateTimeSubTotal(sSubtotal);
	}
	public static VBTimeSubTotal getTimeSubTotal(Long memberId, Calendar surfDate){
		return getSurfService().getTimeSubTotal(memberId, surfDate);
	}
	public static List listTimeSubTotal(Long memberId){
		return getSurfService().listTimeSubTotal(memberId);
	}
	
	public static void addBatchTimeTrack(TimeTrackListReq ttList, String timeTrackTable, String timeTrackPattern, String subTotalTable, String subTotalPattern) throws Exception{
		getSurfService().addBatchTimeTrack(ttList, timeTrackTable, timeTrackPattern, subTotalTable, subTotalPattern);
	}
	//time track
	public static void createTimeTrack(VBTimeTrack timeTrack){
		getSurfService().createTimeTrack(timeTrack);
	}
	public static void deleteTimeTrack(VBTimeTrack timeTrack){
		getSurfService().deleteTimeTrack(timeTrack);
	}
	public static void updateTimeTrack(VBTimeTrack timeTrack){
		getSurfService().updateTimeTrack(timeTrack);
	}
	public static VBTimeTrack getTimeTrack(Long trackId){
		return getSurfService().getTimeTrack(trackId);
	}
	public static List listTimeTrack(Long memberId){
		return getSurfService().listTimeTrack(memberId);
	}
	
	//url track
	public static void createURLTrack(VBURLTrack urlTrack){
		getSurfService().createURLTrack(urlTrack);
	}
	public static void deleteURLTrack(VBURLTrack urlTrack){
		getSurfService().deleteURLTrack(urlTrack);
	}
	public static void updateURLTrack(VBURLTrack urlTrack){
		getSurfService().updateURLTrack(urlTrack);
	}
	public static VBURLTrack getURLTrack(Long trackId){
		return getSurfService().getURLTrack(trackId);
	}
	public static List listURLTrack(Long memberId){
		return getSurfService().listURLTrack(memberId);
	}
	

	
	private static SurfService getSurfService(){
		ApplicationContext ctx = SpringUtil.getContext();
		SurfServiceUtil util = (SurfServiceUtil) ctx.getBean(SurfServiceUtil.class.getName());
		return util.surfService;
	}
	public void setSurfService(SurfService surfService) {
		this.surfService = surfService;
	}
	private SurfService surfService;


}
