package com.agloco.web.session;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.agloco.Constants;
import com.agloco.model.VBSessionTrack;
import com.agloco.service.util.SessionTrackServiceUtil;
import com.agloco.util.PerformanceUtil;
import com.agloco.util.StringUtil;

/**
 * 
 * @author Erick Kong
 * @see SessionListener.java
 * @createDate: 2007-4-3
 * @version 1.0
 */

public class SessionListener implements HttpSessionListener {
	
	private final static Log log = LogFactory.getLog(SessionListener.class);

	public void sessionCreated(HttpSessionEvent event) {
		HttpSession ses = event.getSession();

		SessionContext.put(ses.getId(), ses);

		if(log.isDebugEnabled()){
			log.debug("create session " + ses.getId() + " session context size:" + SessionContext.size());
		}
//		System.out.println("Create session:" + ses.getId());
//		System.out.println(SessionContext.size());
	}

	public void sessionDestroyed(HttpSessionEvent event) {
		HttpSession ses = event.getSession();

		if (ses.getAttribute(Constants.VIEWBAR_AUTO_UPDATE_FLAG) != null) {
			if (ses.getAttribute(Constants.VIEWBAR_AUTO_UPDATE_FLAG).equals(
					Boolean.TRUE)) {
				PerformanceUtil.VB_DOWNLOAD_COUNT = PerformanceUtil.VB_DOWNLOAD_COUNT - 1;
			}
		}
		
		ClientInfo ci = (ClientInfo) ses.getAttribute(Constants.SESSION_CLIENT_INFO);
		if(ci != null){
			VBSessionTrack track = SessionTrackServiceUtil.getSessionTrack(ci.getMemberId());
			if(track != null && ses.getId().equals(track.getSessionId())){
				track.setSessionId(StringUtil.BLANK);
				SessionTrackServiceUtil.updateSessionTrack(track);
			}
		}
		
		SessionContext.remove(ses.getId());
		
		if(log.isDebugEnabled()){
			log.debug("Destroy session " + ses.getId() + " session context size:" + SessionContext.size());
		}

//		System.out.println("Destroy session:" + ses.getId());
//		System.out.println(SessionContext.size());
	}

	// private static Log _log = LogFactory.getLog(PortalSessionListener.class);

}