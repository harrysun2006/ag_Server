package com.agloco.web.service;

import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.agloco.Constants;
import com.agloco.ResponseCode;
import com.agloco.log4j.LogUtil;
import com.agloco.log4j.MessageObject;
import com.agloco.model.FormalMember;
import com.agloco.model.MemberModel;
import com.agloco.model.VBFavorite;
import com.agloco.model.VBSearchEngine;
import com.agloco.model.VBSessionTrack;
import com.agloco.parse.BeanToXmlUtil;
import com.agloco.parse.XmlToBeanUtil;
import com.agloco.service.util.FavoriteServiceUtil;
import com.agloco.service.util.MemberServiceUtil;
import com.agloco.service.util.SearchServiceUtil;
import com.agloco.service.util.SessionTrackServiceUtil;
import com.agloco.util.AdsTemplateUtil;
import com.agloco.util.CommonUtil;
import com.agloco.util.Ip2CountryUtil;
import com.agloco.util.StringUtil;
import com.agloco.web.exception.BeanToXmlException;
import com.agloco.web.exception.XmlToBeanException;
import com.agloco.web.service.model.Engine;
import com.agloco.web.service.model.Favorite;
import com.agloco.web.service.model.LoginReq;
import com.agloco.web.service.model.LoginRes;
import com.agloco.web.session.ClientInfo;
import com.agloco.web.util.FavoriteHashUtil;
import com.agloco.web.util.MiscUtil;
import com.agloco.web.util.SearchEngineHashUtil;

/**
 * 
 * @author terry_zhao
 * @version 1.0
 */
public class LoginService implements BaseService {

	private final static Log log = LogFactory.getLog(LoginService.class);
	
	public void run(HttpServletRequest request, HttpServletResponse response,List fileItems) throws Exception{
		
		LoginRes loginRes = new LoginRes();
//		String loginResXml;
		
		OutputStream out = null;
		try {
			
			response.setContentType("text/html");
			response.setHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode("response.xml", "UTF-8"));
			out = response.getOutputStream();
			
			excute(request,fileItems, loginRes);
//			loginResXml = BeanToXmlUtil.parseByCastor(loginRes,true);
//			System.out.println("Castor time:"+(System.currentTimeMillis() - startTime));
			out.write(BeanToXmlUtil.parseByCastor(loginRes,true).getBytes());


		} catch (XmlToBeanException e) {
			
			if(log.isErrorEnabled()){
				log.error("XmlToBeanException",e);
			}
			
			StringBuffer sb = new StringBuffer(Constants.XML_FILE_HEADER);
			sb.append("<loginRes result=\"")
			  .append(ResponseCode.MAPPING_EXCEPTION)
			  .append("\"/>");
			out.write(sb.toString().getBytes());
			
		} catch (BeanToXmlException e) {
			
			if(log.isErrorEnabled()){
				log.error("BeanToXmlException",e);
			}
			
			StringBuffer sb = new StringBuffer(Constants.XML_FILE_HEADER);
			sb.append("<loginRes")
			  .append(" result=\"")         .append(ResponseCode.SUCCESS)         .append("\"")
			  .append(" memberId=\"")       .append(loginRes.getMemberId())       .append("\"")
			  .append(" serverTime=\"")     .append(loginRes.getServerTime())     .append("\"")
			  .append(" nextSubmitTime=\"")	.append(loginRes.getNextSubmitTime())	.append("\"")
			  .append(" ads-basename=\"")		.append(loginRes.getAdsBaseName())		.append("\"")
			  .append(" ads-jsname=\"")			.append(loginRes.getAdsJsName())			.append("\"")
			  .append(">");
		
			
			List favorites = loginRes.getFavorites();
			if(favorites != null && favorites.size() > 0){
				sb.append("<favorites>");
				List<Favorite> favs = (List<Favorite>)favorites.iterator().next();
				for(Iterator it = favs.iterator(); it.hasNext();){
					Favorite fv = (Favorite) it.next();
                    sb.append("<favorite")
                      .append(" favoriteId=\"").append(fv.getFavoriteId()).append("\"")
                      .append(" name=\"")      .append(fv.getName()).append("\"")
                      .append(" url=\"")       .append(fv.getUrl()).append("\"")
                      .append(" category=\"")  .append(fv.getCategory()).append("\"")
                      .append(" />");
				}
				sb.append("</favorites>");
			}
			
			List engines = loginRes.getEngines();
			if(engines != null && engines.size() > 0){
				sb.append("<engines>");
				List<Engine> engs = (List<Engine>)engines.iterator().next();
				for(Iterator it = engs.iterator(); it.hasNext();){
					Engine eg = (Engine) it.next();
                    sb.append("<engine")
                      .append(" engineId=\"").append(eg.getEngineId()).append("\"")
                      .append(" name=\"")    .append(eg.getName()).append("\"")
                      .append(" url=\"")     .append(eg.getUrl()).append("\"")
                      .append(" pattern=\"") .append(eg.getPattern()).append("\"")
                      .append(" />");
				}
				sb.append("</engines>");
			}
	
			sb.append("</loginRes>");
			
			out.write(sb.toString().getBytes());
			
		} catch (Exception e) {
			
			if(log.isErrorEnabled()){
				log.error("process login error",e);
			}
			
			StringBuffer sb = new StringBuffer(Constants.XML_FILE_HEADER);
			sb.append("<loginRes result=\"")
			  .append(ResponseCode.COMMON_EXCEPTION)
			  .append("\"/>");
			out.write(sb.toString().getBytes());
			
		}
		finally{
			try {
				if(out != null){
					out.close();
				}	
			} catch (Exception e) {
				if(log.isErrorEnabled()){
					log.error("close outputstream error",e);
				}
			}
			
		}
		
	}

	private void excute(HttpServletRequest request, List fileItems, LoginRes res) throws Exception {
		
		//verify fileItems
		if(fileItems == null || fileItems.size() < 1){
			res.setResult(ResponseCode.PARAMETER_ERROR);
			return;
		}
		
		FileItem item = (FileItem)fileItems.iterator().next();
		
		if(item == null || item.getSize() < 1){
			res.setResult(ResponseCode.PARAMETER_ERROR);
			return;
		}
		
		LoginReq loginReq = (LoginReq) XmlToBeanUtil.parseByCastor(item.getInputStream());
		
		//verify paramter
		if(loginReq == null){
			res.setResult(ResponseCode.PARAMETER_ERROR);
			return;
		}
		
		if(StringUtils.isBlank(loginReq.getUserName())){
			res.setResult(ResponseCode.USER_INEXSTENCE_ERROR);
			return;
		}
		if(StringUtils.isBlank(loginReq.getPassword())){
			res.setResult(ResponseCode.PASSWORD_WRONG_ERROR);
			return;
		}
		
		if(StringUtils.isBlank(loginReq.getViewbarId())){
			res.setResult(ResponseCode.FORCE_UPDATE);
			return;
		}
		
		//get member from database
		FormalMember m = null;
		if(loginReq.getUserName().indexOf(StringUtil.AT) > 0){
			m = MemberServiceUtil.getFormalMemberByEmail(loginReq.getUserName());
		}
		else{
			m = MemberServiceUtil.getFormalMemberByCode(loginReq.getUserName());
		}
		
		if(m == null){
			res.setResult(ResponseCode.USER_INEXSTENCE_ERROR);
			return;
		}
		
		if(!m.getPassword().equals(loginReq.getPassword())){
			res.setResult(ResponseCode.PASSWORD_WRONG_ERROR);
			return;
		}
		
		//verify member status
		if(MemberModel.MEMBER_STATUS_INACTIVE.equals(m.getStatus())){
			res.setResult(ResponseCode.USER_SUSPEND_ERROR);
			return;
		}
		if(MemberModel.MEMBER_STATUS_LOCK.equals(m.getStatus())){
			res.setResult(ResponseCode.USER_LOCKED_ERROR);
			return;
		}
		
		res.setResult(ResponseCode.SUCCESS);
		res.setMemberId(m.getMemberId());
		res.setServerTime(Calendar.getInstance().getTimeInMillis()/1000);

		res.setNextSubmitTime(CommonUtil.getNextSubmitTime());
		
		// Oct 12, 2007 by Harry Sun, here response the template basename and jsname to viewbar client
		String clientIp = request.getRemoteAddr();
		String countryCode = Ip2CountryUtil.getCountryCode(clientIp);
		res.setAdsBaseName(AdsTemplateUtil.getBaseName(countryCode));
		res.setAdsJsName(AdsTemplateUtil.getJsName(countryCode));
		
		/*
		//get latest viewbar version
		String latestViewbarId = loginReq.getViewbarId();
		VBViewbar vb = ViewbarServiceUtil.getLatestViewbar(loginReq.getViewbarId());
		if(vb != null){
			latestViewbarId = vb.getViewbarId();
		}
		res.setLatestViewbarId(latestViewbarId);
		
		//check force update
		boolean forceUpdate = false;
		ViewbarCheckRes check = ViewbarServiceUtil.checkViewbar(loginReq.getViewbarId());
		if(check != null){
			forceUpdate = check.getForceUpdate();
		}
		res.setForceUpdate(forceUpdate);
		*/
		
		//check search engine hash code
		checkSearchEngineHash(res, loginReq);
		
		//check favorite hash code
		checkFavoriteHash(res, loginReq);
		
		//save session into db
		sessionTrack(request,loginReq, res);	
		
		//log login in db
		logInDB(request, res, loginReq);

	}
	
	private void checkFavoriteHash(LoginRes res, LoginReq loginReq) throws Exception {
		String hash = FavoriteHashUtil.getAllHashCode();
		if((StringUtils.isBlank(loginReq.getFavoriteHash()) && 
			StringUtils.isNotBlank(hash)) || 
			 (StringUtils.isNotBlank(loginReq.getFavoriteHash()) && 
			  !loginReq.getFavoriteHash().equals(hash))
			){
			
			List favorites = new ArrayList();
			List list = FavoriteServiceUtil.listFavorite(VBFavorite.ACTIVE);
			if(list != null && list.size() > 0){
				for(Iterator it = list.iterator(); it.hasNext();){
					VBFavorite f = (VBFavorite)it.next();
					if(f != null){
						Favorite fav = new Favorite();
						fav.setFavoriteId(f.getFavoriteId());
						fav.setName(f.getName());
						fav.setUrl(f.getUrl());
						fav.setCategory(f.getCategory());
						favorites.add(fav);
					}
				}
			}
			res.setFavorites(favorites);
//			res.setFavorite((Favorite[])favorites.toArray(new Favorite[favorites.size()]));
		}
	}
	
	private void checkSearchEngineHash(LoginRes res, LoginReq loginReq) throws Exception {
		String hash = SearchEngineHashUtil.getAllHashCode();
		if((StringUtils.isBlank(loginReq.getSearchEngineHash()) && 
			StringUtils.isNotBlank(hash)) || 
			 (StringUtils.isNotBlank(loginReq.getSearchEngineHash()) && 
			  !loginReq.getSearchEngineHash().equals(hash))
			){
			
			List searches = new ArrayList();
			List list = SearchServiceUtil.listAllSearchEngine();
			if(list != null && list.size() > 0){
				for(Iterator it = list.iterator(); it.hasNext();){
					VBSearchEngine se = (VBSearchEngine)it.next();
					if(se != null){
						Engine engine = new Engine();
						engine.setEngineId(se.getEngineId());
						engine.setName(se.getName());
						engine.setUrl(se.getUrl());
						engine.setPattern(se.getPattern());
						searches.add(engine);
					}
				}
			}
			res.setEngines(searches);
//			res.setSearchEngines((Engine[])searches.toArray(new Engine[searches.size()]));
		}
	}

	private void logInDB(HttpServletRequest request, LoginRes res, LoginReq loginReq) {
		try {
			if(log.isInfoEnabled()){
				MessageObject mo = new MessageObject();
				
				mo.setComputerName(loginReq.getComputerName());
				mo.setDomainName(loginReq.getDomainName());
				mo.setIpAddr(getIP(request, loginReq));
				mo.setLoginUser(loginReq.getUserName());
				mo.setMacAddr(loginReq.getMacAddr());
				mo.setMemberId(res.getMemberId());
				mo.setOperate(Constants.LOG_OPERATE_LOGIN);
				mo.setOsVersion(loginReq.getOsVersion());
				mo.setServerIp(MiscUtil.getLocalIp());
				mo.setSessionId(request.getSession().getId());
				mo.setViewbarId(loginReq.getViewbarId());
				log.info(mo);
			}	
		} catch (Exception e) {
			LogUtil.error("log error", e);
		}
		
	}

	private String getIP(HttpServletRequest request, LoginReq loginReq) {
		StringBuffer ip = new StringBuffer();
		ip.append(request.getRemoteAddr());
		ip.append(":");
		if(StringUtils.isNotBlank(loginReq.getIpAddr())){
			ip.append(loginReq.getIpAddr());
		}
		return ip.toString();
	}

	private void sessionTrack(HttpServletRequest request,LoginReq loginReq, LoginRes res) throws Exception {
		VBSessionTrack track = SessionTrackServiceUtil.getSessionTrack(res.getMemberId());
		if(track == null){
			track = new VBSessionTrack();
			track.setMemberId(res.getMemberId());
			track.setSessionId(request.getSession().getId());
			SessionTrackServiceUtil.createSessionTrack(track);
		}
		else{
			track.setSessionId(request.getSession().getId());
			SessionTrackServiceUtil.updateSessionTrack(track);
		}
		ClientInfo ci = new ClientInfo();
		ci.setMemberId(res.getMemberId());
		ci.setIp(getIP(request, loginReq));
		ci.setViewbarId(loginReq.getViewbarId());
		request.getSession().setAttribute(Constants.SESSION_CLIENT_INFO,ci);
	}

}
