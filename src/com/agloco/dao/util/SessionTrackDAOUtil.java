package com.agloco.dao.util;

import org.springframework.context.ApplicationContext;

import com.agloco.dao.SessionTrackDAO;
import com.agloco.model.VBSessionTrack;
import com.agloco.spring.SpringUtil;
/**
 * 
 * @author terry_zhao
 * @version 1.0
 */
public class SessionTrackDAOUtil {

	public static void createSessionTrack(VBSessionTrack track){
		getSessionTrackDAO().createSessionTrack(track);
	}
	public static void deleteSessionTrack(VBSessionTrack track){
		getSessionTrackDAO().deleteSessionTrack(track);
	}
	public static void updateSessionTrack(VBSessionTrack track){
		getSessionTrackDAO().updateSessionTrack(track);
	}
	public static VBSessionTrack getSessionTrack(Long memberId){
		return getSessionTrackDAO().getSessionTrack(memberId);
	}
	public static VBSessionTrack getSessionTrack(String sessionId){
		return getSessionTrackDAO().getSessionTrack(sessionId);
	}
	
	private static SessionTrackDAO getSessionTrackDAO(){
		ApplicationContext ctx = SpringUtil.getContext();
		SessionTrackDAOUtil util = (SessionTrackDAOUtil)ctx.getBean(SessionTrackDAOUtil.class.getName());
		return util.sessionTrackDAO;
	}
	
	public void setSessionTrackDAO(SessionTrackDAO sessionTrackDAO) {
		this.sessionTrackDAO = sessionTrackDAO;
	}
	private SessionTrackDAO sessionTrackDAO;


}
