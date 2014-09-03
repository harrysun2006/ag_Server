package com.agloco.dao.util;

import java.util.List;

import org.springframework.context.ApplicationContext;

import com.agloco.dao.AdvertiseDAO;
import com.agloco.model.VBADDTrack;
import com.agloco.model.VBADKeywords;
import com.agloco.model.VBADMatrix;
import com.agloco.model.VBADMatrixPK;
import com.agloco.model.VBADVTrack;
import com.agloco.model.VBAdvertise;
import com.agloco.spring.SpringUtil;

/**
 * 
 * @author terry_zhao
 * @version 1.0
 */
public class AdvertiseDAOUtil {

	//advertise
	public static void createAdvertise(VBAdvertise advertise){
		getAdvertiseDAO().createAdvertise(advertise);
	}
	public static void updateAdvertise(VBAdvertise advertise){
		getAdvertiseDAO().updateAdvertise(advertise);
	}
	public static void deleteAdvertise(VBAdvertise advertise){
		getAdvertiseDAO().deleteAdvertise(advertise);
	}

	public static VBAdvertise getAdvertise(String advertiseId){
		return getAdvertiseDAO().getAdvertise(advertiseId);
	}
	public static List<VBAdvertise> listAdvertiseByType(String type){
		return getAdvertiseDAO().listAdvertiseByType(type);
	}
	
	
	//advertise keywords
	public static void createADKeywords(VBADKeywords keyword){
		getAdvertiseDAO().createADKeywords(keyword);
	}
	public static void updateADKeywords(VBADKeywords keyword){
		getAdvertiseDAO().updateADKeywords(keyword);
	}
	public static void deleteADKeywords(VBADKeywords keyword){
		getAdvertiseDAO().deleteADKeywords(keyword);
	}
	public static VBADKeywords getADKeywords(Long keywordId){
		return getAdvertiseDAO().getADKeywords(keywordId);
	}
	public static  VBADKeywords getADKeywords(String keyword){
		return getAdvertiseDAO().getADKeywords(keyword);
	}
	
	//advertise matrix
	public static void createADMatrix(VBADMatrix adMatrix){
		getAdvertiseDAO().createADMatrix(adMatrix);
	}
	public static void updateADMatrix(VBADMatrix adMatrix){
		getAdvertiseDAO().updateADMatrix(adMatrix);
	}
	public static void deleteADMatrix(VBADMatrix adMatrix){
		getAdvertiseDAO().deleteADMatrix(adMatrix);
	}
	public static  VBADMatrix getADMatrix(VBADMatrixPK pk){
		return getAdvertiseDAO().getADMatrix(pk);	
	}
	public static  List listADMatrix(Long adKeywordId){
		return getAdvertiseDAO().listADMatrix(adKeywordId);
	}
	public static  List listADMatrix(String keyword){
		return getAdvertiseDAO().listADMatrix(keyword);
	}
	
	
	//advertise download track
	public static  void createADDownloadTrack(VBADDTrack dTrack){
		getAdvertiseDAO().createADDownloadTrack(dTrack);
	}
	public static  void deleteADDownloadTrack(VBADDTrack dTrack){
		getAdvertiseDAO().deleteADDownloadTrack(dTrack);
	}
	public static  void updateADDownloadTrack(VBADDTrack dTrack){
		getAdvertiseDAO().updateADDownloadTrack(dTrack);
	}
	public static  VBADDTrack getADDownloadTrack(Long trackId){
		return getAdvertiseDAO().getADDownloadTrack(trackId);
	}
	
	//advertise visit track
	public static  void createADVisitTrack(VBADVTrack vTrack){
		getAdvertiseDAO().createADVisitTrack(vTrack);
	}
	public static  void deleteADVisitTrack(VBADVTrack vTrack){
		getAdvertiseDAO().deleteADVisitTrack(vTrack);
	}
	public static  void updateADVisitTrack(VBADVTrack vTrack){
		getAdvertiseDAO().updateADVisitTrack(vTrack);
	}
	public static  VBADVTrack getADVisitTrack(Long trackId){
		return getAdvertiseDAO().getADVisitTrack(trackId);
	}
	
	private static AdvertiseDAO getAdvertiseDAO(){
		ApplicationContext ctx = SpringUtil.getContext();
		AdvertiseDAOUtil util = (AdvertiseDAOUtil) ctx.getBean(AdvertiseDAOUtil.class.getName());
		return util.advertiseDAO;
	}
	
	public void setAdvertiseDAO(AdvertiseDAO advertiseDAO) {
		this.advertiseDAO = advertiseDAO;
	}
	
	private AdvertiseDAO advertiseDAO;
}
