package com.agloco.service.util;

import org.springframework.context.ApplicationContext;

import com.agloco.model.VBSessionTrack;
import com.agloco.service.SessionTrackService;
import com.agloco.spring.SpringUtil;

public class SessionTrackServiceUtil {

	public static void createSessionTrack(VBSessionTrack track){
		getSessionServiceDAO().createSessionTrack(track);
	}
	public static void deleteSessionTrack(VBSessionTrack track){
		getSessionServiceDAO().deleteSessionTrack(track);
	}
	public static void updateSessionTrack(VBSessionTrack track){
		getSessionServiceDAO().updateSessionTrack(track);
	}
	public static VBSessionTrack getSessionTrack(Long memberId){
		return getSessionServiceDAO().getSessionTrack(memberId);
	}
	public static VBSessionTrack getSessionTrack(String sessionId){
		return getSessionServiceDAO().getSessionTrack(sessionId);
	}
	
	private static SessionTrackService getSessionServiceDAO(){
		ApplicationContext ctx = SpringUtil.getContext();
		SessionTrackServiceUtil util = (SessionTrackServiceUtil)ctx.getBean(SessionTrackServiceUtil.class.getName());
		return util.sessionTrackService;
	}
	
	public void setSessionTrackService(SessionTrackService sessionTrackService) {
		this.sessionTrackService = sessionTrackService;
	}
	private SessionTrackService sessionTrackService;
}
