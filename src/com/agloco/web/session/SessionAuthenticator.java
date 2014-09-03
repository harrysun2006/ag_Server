package com.agloco.web.session;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.agloco.Constants;
import com.agloco.ResponseCode;
import com.agloco.model.VBSessionTrack;
import com.agloco.service.util.SessionTrackServiceUtil;
import com.agloco.service.util.ViewbarServiceUtil;

/**
 * 
 * @author terry_zhao
 * @version 1.0
 */
public class SessionAuthenticator {

	private final static Log log = LogFactory.getLog(SessionAuthenticator.class); 
	
	private static HttpSession getSession(String sessionId){
		return SessionContext.get(sessionId);
	}
	
	private static ClientInfo getClientInfo(String sessionId){
		HttpSession session = getSession(sessionId); 
		if(session == null){
			if(log.isDebugEnabled())
				log.debug("SessionContext session is null,session id:" + sessionId);
			return null;
		}
		return (ClientInfo)session.getAttribute(Constants.SESSION_CLIENT_INFO);
	}
	
	private static VBSessionTrack getSessinTrack(String sessionId){
		ClientInfo ci = getClientInfo(sessionId); 
		if(ci == null){
			if(log.isDebugEnabled())
				log.debug("ClientInfo is null");
			return null;
		}
		return SessionTrackServiceUtil.getSessionTrack(ci.getMemberId());
	}
	
	private static int authenticate(HttpSession session){
		if(session == null){
			if(log.isDebugEnabled())
				log.debug("parameter session is null");
			return ResponseCode.UNAUTHORIZED;
		}
		
		VBSessionTrack track = getSessinTrack(session.getId());
		if(track == null){
			if(log.isDebugEnabled())
				log.debug("VBSessionTrack is null");
			return ResponseCode.UNAUTHORIZED;
		}
		
		if(session.getId().equals(track.getSessionId())){
			if(ViewbarServiceUtil.forceUpdate(getClientInfo(session.getId()).getViewbarId())){
				return ResponseCode.FORCE_UPDATE;
			}
			return ResponseCode.SUCCESS;
		}
		
		if(log.isErrorEnabled()){
			ClientInfo ci = getClientInfo(track.getSessionId());
			ClientInfo ci2 = getClientInfo(session.getId());
			StringBuffer sb = new StringBuffer();
			if(ci != null){
				DateFormat df = new SimpleDateFormat("yyyy.MM.dd G 'at' HH:mm:ss z");
				sb.append("->MemberId: ").append(ci.getMemberId())
				  .append(" has login using ip:").append(ci.getIp())
				  .append("at ").append(df.format(track.getModifiedDate().getTime()))
				  .append(" sessionId:").append(track.getSessionId());
			}
			if(ci2 != null){
				sb.append(" your ip:").append(ci2.getIp())
				  .append(" sessionId:").append(session.getId());
			}
			log.error(sb.toString());
		}
		return ResponseCode.OTHER_PLACE_LOGIN;
	}
	
	public static int authenticate(HttpServletRequest request){
		return authenticate(request.getSession());
	}
	
	public static ClientInfo getClientInfo(HttpServletRequest request){
	
		if(request.getSession() == null){
			return null;
		}
		return getClientInfo(request.getSession().getId());
	}
	
	public static Long getMemberId(HttpServletRequest request){
		ClientInfo ci = getClientInfo(request);
		if(ci == null){
			return null;
		}
		return ci.getMemberId();
	}
	
}
