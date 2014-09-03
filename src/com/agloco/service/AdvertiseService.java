package com.agloco.service;

import java.util.List;

import com.agloco.model.VBADDTrack;
import com.agloco.model.VBADKeywords;
import com.agloco.model.VBADMatrix;
import com.agloco.model.VBADMatrixPK;
import com.agloco.model.VBADVTrack;
import com.agloco.model.VBAdvertise;
import com.agloco.service.impl.AdvertiseServiceImpl;

/**
 * 
 * @author terry_zhao
 * @see AdvertiseServiceImpl
 * @version 1.0
 */
public interface AdvertiseService extends BaseService {

	//advertise
	public void createAdvertise(VBAdvertise advertise);
	public void updateAdvertise(VBAdvertise advertise);
	public void deleteAdvertise(VBAdvertise advertise);
	public VBAdvertise getAdvertise(String advertiseId);
	public List listAdvertise(List matrixList,boolean logDownloadCount,Long memberId);
	public List<VBAdvertise> listAdvertiseByType(String type);
	
	//advertise keywords
	public void createADKeywords(VBADKeywords keyword);
	public void updateADKeywords(VBADKeywords keyword);
	public void deleteADKeywords(VBADKeywords keyword);
	public VBADKeywords getADKeywords(Long keywordId);
	public VBADKeywords getADKeywords(String keyword);
	public List listADKeywords(List matrixList,boolean logSearchCount);
	
	
	//advertise matrix
	public void createADMatrix(VBADMatrix adMatrix);
	public void updateADMatrix(VBADMatrix adMatrix);
	public void deleteADMatrix(VBADMatrix adMatrix);
	public VBADMatrix getADMatrix(VBADMatrixPK pk);
	public List listADMatrix(Long adKeywordId);
	public List listADMatrix(String keyword);
	public List listAdvertiseMatrix(List matrixList);
	
	
	//advertise download track
	public void createADDownloadTrack(VBADDTrack dTrack);
	public void deleteADDownloadTrack(VBADDTrack dTrack);
	public void updateADDownloadTrack(VBADDTrack dTrack);
	public VBADDTrack getADDownloadTrack(Long trackId);
	
	//advertise visit track
	public void createADVisitTrack(VBADVTrack vTrack);
	public void deleteADVisitTrack(VBADVTrack vTrack);
	public void updateADVisitTrack(VBADVTrack vTrack);
	public VBADVTrack getADVisitTrack(Long trackId);
	
}
