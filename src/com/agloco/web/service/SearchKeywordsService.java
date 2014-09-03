package com.agloco.web.service;

import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.agloco.Constants;
import com.agloco.ResponseCode;
import com.agloco.model.VBADKeywords;
import com.agloco.model.VBADMatrix;
import com.agloco.model.VBAdvertise;
import com.agloco.model.VBSearchTrack;
import com.agloco.parse.BeanToXmlUtil;
import com.agloco.parse.XmlToBeanUtil;
import com.agloco.rolling.RollingTableObject;
import com.agloco.service.util.AdvertiseServiceUtil;
import com.agloco.service.util.SearchServiceUtil;
import com.agloco.util.KeywordUtil;
import com.agloco.util.StringUtil;
import com.agloco.web.exception.BeanToXmlException;
import com.agloco.web.exception.XmlToBeanException;
import com.agloco.web.service.model.AdKeywords;
import com.agloco.web.service.model.AdKeywordsList;
import com.agloco.web.service.model.AdMatrix;
import com.agloco.web.service.model.AdMatrixList;
import com.agloco.web.service.model.Advertise;
import com.agloco.web.service.model.SearchKeywordReq;
import com.agloco.web.service.model.SearchKeywordRes;
import com.agloco.web.session.ClientInfo;

public class SearchKeywordsService implements BaseService {

	private final static Log log = LogFactory.getLog(SearchKeywordsService.class);
	
	private RollingTableObject searchTrackRolling;
	public void setSearchTrackRolling(RollingTableObject searchTrackRolling) {
		this.searchTrackRolling = searchTrackRolling;
	}
	
	public void run(HttpServletRequest request, HttpServletResponse response,
			List fileItems) throws Exception {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		response.setHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode("response.xml", "UTF-8"));
		OutputStream out = null;
		SearchKeywordRes searchKeywordRes = new SearchKeywordRes();
		try {
			
			searchTrackRolling.rolling();
			
			out = response.getOutputStream();
			excute(request,response, fileItems,searchKeywordRes);
			String resXml = BeanToXmlUtil.parseByCastor(searchKeywordRes,true);
			out.write(resXml.getBytes());
			
		} catch (BeanToXmlException e) {
			if(log.isErrorEnabled()){
				log.error("BeanToXmlException",e);
			}
			
			StringBuffer sb = new StringBuffer(Constants.XML_FILE_HEADER);
			sb.append("<searchKeywordRes")
			  .append(" result=\"")         .append(ResponseCode.SUCCESS)         .append("\"")
			  .append(">");
			
			List adList = searchKeywordRes.getAdvertises();
			if(adList != null && adList.size() > 0){
				sb.append("<advertises>");
				List<Advertise> ads = (List<Advertise>)adList.iterator().next();
				for (Iterator iter = ads.iterator(); iter.hasNext();) {
					Advertise advertise = (Advertise) iter.next();
					if(advertise != null){
						sb.append("<advertise")
						  .append(" adId=\"")      .append(advertise.getAdId())      .append("\"")
						  .append(" adCategory=\"").append(advertise.getAdCategory()).append("\"")
						  .append(" adType=\"")    .append(advertise.getAdType())    .append("\"")
						  .append(" adBanner=\"")  .append(advertise.getAdBanner())  .append("\"")
						  .append(" adContent=\"") .append(advertise.getAdContent()) .append("\"")
						  .append(" adDescription=\"") .append(advertise.getAdDescription()) .append("\"")
						  .append(" />");
					}
					
				}
				sb.append("</advertises>");
			}
			
			AdKeywordsList kList = searchKeywordRes.getKeywordsList();
			if(kList != null){
				List list = kList.getKeywordList();
				if(list != null && list.size() > 0){
					sb.append("<keywordsList>");
					for (Iterator iter = list.iterator(); iter.hasNext();) {
						AdKeywords ky = (AdKeywords) iter.next();
						if(ky != null){
							sb.append("<keyword")
							  .append(" adKeywordId=\"").append(ky.getAdKeywordId()).append("\"")
							  .append(" content=\"")    .append(ky.getContent())    .append("\"")
							  .append(" />");	
						}
						
						
					}
					sb.append("</keywordsList>");
				}
			}
			
			AdMatrixList mList = searchKeywordRes.getMatrixList();
			if(mList != null){
				List list = mList.getMatrixList();
				if(list != null && list.size() > 0){
					sb.append("<matrixList>");
					for (Iterator iter = list.iterator(); iter.hasNext();) {
						AdMatrix mt = (AdMatrix) iter.next();
						if(mt != null){
							sb.append("<matrix")
							  .append(" adKeywordId=\"").append(mt.getAdKeywordId()).append("\"")
							  .append(" adId=\"")       .append(mt.getAdId())       .append("\"")
							  .append(" weight=\"")     .append(mt.getWeight())     .append("\"")
							  .append(" />");	
						}
						
						
					}
					sb.append("</matrixList>");
				}
			}
			
			
			sb.append("</searchKeywordRes>");
			out.write(sb.toString().getBytes());
			
		} catch (XmlToBeanException e) {
			if(log.isErrorEnabled()){
				log.error("XmlToBeanException",e);
			}
			StringBuffer sb = new StringBuffer(Constants.XML_FILE_HEADER);
			sb.append("<searchKeywordRes result=\"")
			  .append(ResponseCode.MAPPING_EXCEPTION)
			  .append("\"/>");
			out.write(sb.toString().getBytes());

		} catch (Exception e) {
			if(log.isErrorEnabled()){
				log.error("process search keywords error",e);
			}
			StringBuffer sb = new StringBuffer(Constants.XML_FILE_HEADER);
			sb.append("<searchKeywordRes result=\"")
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
	private void excute(HttpServletRequest request, HttpServletResponse response, List fileItems,SearchKeywordRes searchKeywordRes) throws Exception {
		
		SearchKeywordReq searchKeywordReq;
		if(fileItems == null || fileItems.size() < 1){
			searchKeywordRes.setResult(ResponseCode.PARAMETER_ERROR);
			return;
		}
		
		FileItem item = (FileItem)fileItems.iterator().next();
		if(item == null || item.getSize() < 1){
			searchKeywordRes.setResult(ResponseCode.PARAMETER_ERROR);
			return;
		}
		
		searchKeywordReq = (SearchKeywordReq) XmlToBeanUtil.parseByCastor(item.getInputStream());
		
		if(searchKeywordReq == null){
			searchKeywordRes.setResult(ResponseCode.PARAMETER_ERROR);
			return;
		}
		
		String[] adKeywords = searchKeywordReq.getKeywords();

		if(adKeywords == null || adKeywords.length < 1){
			searchKeywordRes.setResult(ResponseCode.PARAMETER_ERROR);
			return;
		}
		
		Long memberId = null;
		ClientInfo ci = (ClientInfo) request.getSession().getAttribute(Constants.SESSION_CLIENT_INFO);
		if(ci != null){
			memberId = ci.getMemberId();
		}
		//track search keywords
		track(searchKeywordReq, adKeywords);
		
		//check whether need advertise  
		if(searchKeywordReq.getNeedAdvertise() == true){
			getAdvertises(memberId,searchKeywordRes, adKeywords);
		}
		
		searchKeywordRes.setResult(ResponseCode.SUCCESS);
		
	}
	

	
	private void getAdvertises(Long memberId, SearchKeywordRes searchKeywordRes, String[] adKeywords) throws Exception{
		
		//first query advertise matrix
		
		String[] words = (String[])KeywordUtil.getKeywordsArray(adKeywords[0]);
		boolean same = false; 
		if(words != null || words.length > 1){
			for (int i = 0; i < words.length; i++) {
				if(adKeywords.equals(words[i])){
					same = true;
					break;
				}
			}
		}
		
		List matrixList = null;
		//first query by all word
		if(same){
			matrixList = AdvertiseServiceUtil.listAdvertiseMatrix(new String[]{adKeywords[0]});
		}
		//second query by words after participle
		if(matrixList == null || matrixList.size() < 1){
			matrixList = AdvertiseServiceUtil.listAdvertiseMatrix(words);	
		}
		
		//last get default advertise and set them to response
		if(matrixList == null || matrixList.size() < 1){
			
			getDefaultAdvertise(searchKeywordRes,adKeywords[0],words);
			return;
		}
		
		//set matrix
		List matrix = new ArrayList();
		for(Iterator it = matrixList.iterator(); it.hasNext();){
			
			VBADMatrix  m = (VBADMatrix)it.next();
			if(m == null){
				continue;
			}
			
			AdMatrix am = new AdMatrix();
			am.setAdId(m.getPrimaryKey().getAdId());
			am.setAdKeywordId(m.getPrimaryKey().getAdKeywordId());
			am.setWeight(m.getWeight());
			matrix.add(am);
		}
		AdMatrixList ml = new AdMatrixList();
		ml.setMatrixList(matrix);
		searchKeywordRes.setMatrixList(ml);
		
		
		//second query adveritse
		
		List advertiseList = AdvertiseServiceUtil.listAdvertise(matrixList,true,memberId);
		if(advertiseList != null && advertiseList.size() > 0){
			
			List advertises = new ArrayList();
			
			for(int i = 0; i < advertiseList.size(); i++){
				
				VBAdvertise vbAdvertise = (VBAdvertise)advertiseList.get(i);
				if(vbAdvertise == null){
					continue;
				}
				
				Advertise  advertise = new Advertise();
				advertise.setAdBanner(vbAdvertise.getBanner());
				advertise.setAdCategory(vbAdvertise.getCategory());
				advertise.setAdContent(vbAdvertise.getContent());
				advertise.setAdId(vbAdvertise.getAdId());
				advertise.setAdType(vbAdvertise.getType());
				advertise.setAdDescription(vbAdvertise.getDescription());
				advertises.add(advertise);
			}
			
			searchKeywordRes.setAdvertises(advertises);
		}
		
		
		//last query keywords
		List keywordList = AdvertiseServiceUtil.listADKeywords(matrixList, true);
		if(keywordList != null && keywordList.size() > 0){
			List keywords = new ArrayList();
			for(Iterator it = keywordList.iterator(); it.hasNext();){
				VBADKeywords vk = (VBADKeywords) it.next();
				if(vk == null){
					continue;
				}
//				ADKeywords ky = new ADKeywords();
				AdKeywords ky = new AdKeywords();
				ky.setAdKeywordId(vk.getAdKeywordId());
				ky.setContent(vk.getContent());
				keywords.add(ky);
			}
			AdKeywordsList kl = new AdKeywordsList();
			kl.setKeywordList(keywords);
			searchKeywordRes.setKeywordsList(kl);
		}
		
	}
	
	private String[] participle(String[] keywords){
		List list = new ArrayList();
		for (int i = 0; i < keywords.length; i++) {
			String keyword = keywords[i];
			String[] kys = keyword.split(StringUtil.SPACE);
			for (int j = 0; j < kys.length; j++) {
				list.add(kys[j]);
			}
		}
		return (String[])list.toArray(new String[list.size()]);
	}
	
	private void track(SearchKeywordReq searchKeywordReq, String[] searchKeywords) throws Exception{
		try {
			StringBuffer sb = new StringBuffer(searchKeywords[0]);
			for(int i = 1; i < searchKeywords.length; i++){
				sb.append(StringUtil.COMMA).append(searchKeywords[i]);
			}
			StringBuffer keyword = new StringBuffer(sb.length());
			for(int i = 0; i < sb.length(); i++){
				if(sb.charAt(i) == '\\'){
					continue;
				}
				keyword.append(sb.charAt(i));
			}
			VBSearchTrack track = new VBSearchTrack();
			track.setMemberId(searchKeywordReq.getMemberId());
			track.setEngineId(searchKeywordReq.getSearchEngineId());
			track.setKeywords(keyword.toString());
			track.setBrowser(searchKeywordReq.getBrowser());
			SearchServiceUtil.createSearchTrack(track);	
		} catch (Exception e) {
			if(log.isErrorEnabled()){
				log.error("track search keywords error", e);
			}
		}
		
	}
	
	private void getDefaultAdvertise(SearchKeywordRes skRes,String keyword,String[] words) throws UnsupportedEncodingException{
		
		//get default advertise
		List<VBAdvertise> ads = AdvertiseServiceUtil.listAdvertiseByType(Constants.ADVERTISE_TYPE_DEFAULT);
		if(ads == null || ads.size() < 1){
			return;
		}
	
		List<Advertise> adsList = new ArrayList<Advertise>();
		for (Iterator iter = ads.iterator(); iter.hasNext();) {
			VBAdvertise vbAdvertise = (VBAdvertise) iter.next();
			if(vbAdvertise == null){
				continue;
			}
			for (int i = 0; i < words.length; i++) {
				addToList(adsList,vbAdvertise,words[i]);
			}
			addToList(adsList,vbAdvertise,keyword);
		}
		
		skRes.setAdvertises(adsList);
	}
	
	private void addToList(List<Advertise> adsList,VBAdvertise vbAdvertise,String keyword) throws UnsupportedEncodingException{
		if(keyword == null){
			keyword = "";
		}
		Advertise  advertise = new Advertise();
		advertise.setAdBanner(vbAdvertise.getBanner());
		advertise.setAdCategory(vbAdvertise.getCategory());
		//replace keywords in url(like:http://viewbar.agloco.com/ads/wikia.jsp?keywords={-[+]-})
		//which can display different ads by keywords
		advertise.setAdContent(vbAdvertise.getContent().replace(Constants.ADVERTISE_REPLACE_STRING, URLEncoder.encode(keyword,"UTF-8")));
		advertise.setAdId(vbAdvertise.getAdId());
		advertise.setAdType(vbAdvertise.getType());
		advertise.setAdDescription(vbAdvertise.getDescription());
		adsList.add(advertise);
	}

}
