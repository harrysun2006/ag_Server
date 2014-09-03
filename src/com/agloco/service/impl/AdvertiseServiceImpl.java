package com.agloco.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.agloco.dao.util.AdvertiseDAOUtil;
import com.agloco.model.VBADDTrack;
import com.agloco.model.VBADKeywords;
import com.agloco.model.VBADMatrix;
import com.agloco.model.VBADMatrixPK;
import com.agloco.model.VBADVTrack;
import com.agloco.model.VBAdvertise;
import com.agloco.service.AdvertiseService;

/**
 * 
 * @author terry_zhao
 * @version 1.0
 */
public class AdvertiseServiceImpl implements AdvertiseService {

	//advertise
	public void createAdvertise(VBAdvertise advertise){
		AdvertiseDAOUtil.createAdvertise(advertise);
	}
	public void updateAdvertise(VBAdvertise advertise){
		AdvertiseDAOUtil.updateAdvertise(advertise);
	}
	public void deleteAdvertise(VBAdvertise advertise){
		AdvertiseDAOUtil.deleteAdvertise(advertise);
	}
	public VBAdvertise getAdvertise(String advertiseId){
		return AdvertiseDAOUtil.getAdvertise(advertiseId);
	}
	public List<VBAdvertise> listAdvertiseByType(String type){
		return AdvertiseDAOUtil.listAdvertiseByType(type);
	}
	public List listAdvertise(List matrixList, boolean logDownloadCount, Long memberId){
		if(matrixList == null || matrixList.size() < 1){
			return null;
		}
		
		List advertises = new ArrayList();
		for(Iterator it = matrixList.iterator(); it.hasNext();){
			VBADMatrix matrix = (VBADMatrix)it.next();
			VBAdvertise ad = getAdvertise(matrix.getPrimaryKey().getAdId());
			if(ad == null){
				continue;
			}
			if(advertises.contains(ad)){
				continue;
			}
			advertises.add(ad);
			if(logDownloadCount){
				ad.setDownloadCount(new Long(ad.getDownloadCount().longValue()+1));
				updateAdvertise(ad);
			}
			if(memberId != null){
				VBADDTrack dTrack = new VBADDTrack();
				dTrack.setAdId(ad.getAdId());
				dTrack.setMemberId(memberId);
				createADDownloadTrack(dTrack);
			}
		}
		
		return advertises;
	}

	
	//advertise keywords
	public void createADKeywords(VBADKeywords keyword){
		AdvertiseDAOUtil.createADKeywords(keyword); 
	}
	public void updateADKeywords(VBADKeywords keyword){
		AdvertiseDAOUtil.updateADKeywords(keyword);
	}
	public void deleteADKeywords(VBADKeywords keyword){
		AdvertiseDAOUtil.deleteADKeywords(keyword);
	}
	public VBADKeywords getADKeywords(Long keywordId){
		return AdvertiseDAOUtil.getADKeywords(keywordId);
	}
	public VBADKeywords getADKeywords(String keyword){
		return AdvertiseDAOUtil.getADKeywords(keyword);
	}
	public List listADKeywords(List matrixList,boolean logSearchCount){

		if(matrixList == null || matrixList.size() < 1){
			return null;
		}
		
		List keywords = new ArrayList();
		for(Iterator it = matrixList.iterator(); it.hasNext();){
			VBADMatrix matrix = (VBADMatrix)it.next();
			VBADKeywords ky = getADKeywords(matrix.getPrimaryKey().getAdKeywordId());
			if(ky == null){
				continue;
			}
			if(keywords.contains(ky)){
				continue;
			}
			keywords.add(ky);
			if(logSearchCount){
				ky.setSearchCount(new Long(ky.getSearchCount().longValue()+1));
				updateADKeywords(ky);
			}
		}
		
		return keywords;
	}
	
	//advertise matrix
	public void createADMatrix(VBADMatrix adMatrix){
		AdvertiseDAOUtil.createADMatrix(adMatrix);
	}
	public void updateADMatrix(VBADMatrix adMatrix){
		AdvertiseDAOUtil.updateADMatrix(adMatrix);
	}
	public void deleteADMatrix(VBADMatrix adMatrix){
		AdvertiseDAOUtil.deleteADMatrix(adMatrix);
	}
	public VBADMatrix getADMatrix(VBADMatrixPK pk){
		return AdvertiseDAOUtil.getADMatrix(pk);
	}
	public List listADMatrix(Long adKeywordId){
		return AdvertiseDAOUtil.listADMatrix(adKeywordId);
	}
	public List listADMatrix(String keyword){
		return AdvertiseDAOUtil.listADMatrix(keyword);
	}
	public List listAdvertiseMatrix(List matrixList){
		if(matrixList == null || matrixList.size() < 1){
			return null;
		}
		List matrixes = new ArrayList();
		for(Iterator it = matrixList.iterator(); it.hasNext();){
			VBADMatrix matrix = (VBADMatrix)it.next();
			VBADMatrix m = getADMatrix(matrix.getPrimaryKey());
			if(m == null){
				continue;
			}
			matrixes.add(m);
		}
		return matrixes;
	}
	
	//advertise download track
	public void createADDownloadTrack(VBADDTrack dTrack){
		AdvertiseDAOUtil.createADDownloadTrack(dTrack);
	}
	public void deleteADDownloadTrack(VBADDTrack dTrack){
		AdvertiseDAOUtil.deleteADDownloadTrack(dTrack);
	}
	public void updateADDownloadTrack(VBADDTrack dTrack){
		AdvertiseDAOUtil.updateADDownloadTrack(dTrack);
	}
	public VBADDTrack getADDownloadTrack(Long trackId){
		return AdvertiseDAOUtil.getADDownloadTrack(trackId);
	}
	
	//advertise visit track
	public void createADVisitTrack(VBADVTrack vTrack){
		AdvertiseDAOUtil.createADVisitTrack(vTrack);
	}
	public void deleteADVisitTrack(VBADVTrack vTrack){
		AdvertiseDAOUtil.deleteADVisitTrack(vTrack);
	}
	public void updateADVisitTrack(VBADVTrack vTrack){
		AdvertiseDAOUtil.updateADVisitTrack(vTrack);
	}
	public VBADVTrack getADVisitTrack(Long trackId){
		return AdvertiseDAOUtil.getADVisitTrack(trackId);
	}

}
