package com.agloco.service.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.context.ApplicationContext;

import com.agloco.Constants;
import com.agloco.model.VBADDTrack;
import com.agloco.model.VBADKeywords;
import com.agloco.model.VBADMatrix;
import com.agloco.model.VBADMatrixPK;
import com.agloco.model.VBADVTrack;
import com.agloco.model.VBAdvertise;
import com.agloco.service.AdvertiseService;
import com.agloco.spring.SpringUtil;

/**
 * 
 * @author terry_zhao
 * @version 1.0	
 */
public class AdvertiseServiceUtil {

	//advertise
	public static void createAdvertise(VBAdvertise advertise){
		getAdvertiseService().createAdvertise(advertise);
	}
	public static void updateAdvertise(VBAdvertise advertise){
		getAdvertiseService().updateAdvertise(advertise);
	}
	public static void deleteAdvertise(VBAdvertise advertise){
		getAdvertiseService().deleteAdvertise(advertise);
	}

	public static VBAdvertise getAdvertise(String advertiseId){
		return getAdvertiseService().getAdvertise(advertiseId);
	}
	public static List<VBAdvertise> listAdvertiseByType(String type){
		return getAdvertiseService().listAdvertiseByType(type);
	}
	public static List listAdvertise(List matrixList,boolean logDownloadCount,Long memberId){
		return getAdvertiseService().listAdvertise(matrixList, logDownloadCount,memberId);
	}
	
	public static List listADKeywords(List matrixList,boolean logSearchCount){
		return getAdvertiseService().listADKeywords(matrixList, logSearchCount);
	}
	
	private static List listAdvertiseMatrix(List matrixList) {
		return getAdvertiseService().listAdvertiseMatrix(matrixList);
	}
	public static List listAdvertiseMatrix(String[] keywords) {
		if(keywords == null || keywords.length < 1){
			return null;
		}
		
		//first list all ad maxtrix by keyword
		Map map = new HashMap();
		for(int i = 0; i < keywords.length; i++){
			List l = listADMatrix(keywords[i]);
			map.put(String.valueOf(i), l);
		}
		
		List total = new ArrayList();
		//compare from all lists
		for(int i = 0; i < keywords.length; i++){
			
			//get list from map 
			List original = (List)map.get(String.valueOf(i));
			if(original == null || original.size() < 1){
				continue;
			}
			
			//max compare number of every list
			int max = Constants.ADVERTISE_COMPARE_MAX_NUMBER < original.size() ? Constants.ADVERTISE_COMPARE_MAX_NUMBER :original.size() ;
			for(int j = 0; j < max; j++){
				VBADMatrix matrix = (VBADMatrix) original.get(j);
				//compare with other lists
				for(int p = 0; p < keywords.length; p++){
					
					if(p == i){
						continue;
					}
					
					List compare = (List) map.get(String.valueOf(p));
					if(compare == null || compare.size() < 1){
						continue;
					}
					
					for(Iterator it = compare.iterator(); it.hasNext();){
						VBADMatrix temp = (VBADMatrix)it.next();
						if(temp.getPrimaryKey().getAdId().equals(matrix.getPrimaryKey().getAdId())){
							matrix.setWeight(new Double(matrix.getWeight().doubleValue() + Constants.ADVERTISE_WEIGHT_INCREASE));
							continue;
						}
					}
//					if(compare.contains((VBADMatrix)original.get(j))){
//						matrix.setWeight(new Double(1 + matrix.getWeight().longValue()));
//					}
					
				}
				
				total.add(matrix);
			}
			
		}
		
		//total advertise matrix 
		if(total == null || total.size() < 1){
			return null;
		}
		
		//get a certain number max weight of total list
		List matrixList = new ArrayList();
		int maxDownload =  Constants.ADVERTISE_DOWNLOAD_MAX_NUMBER > total.size() ? total.size() : Constants.ADVERTISE_DOWNLOAD_MAX_NUMBER;
		for(int k = 0; k < maxDownload; k++){
			
			double maxWeight = 0;
			int maxIndex = -1;
			for(int i = 0; i < total.size(); i++){
				VBADMatrix matrix = (VBADMatrix)total.get(i);
				if(matrix.getWeight().doubleValue() > maxWeight){
					maxWeight = matrix.getWeight().doubleValue();
					maxIndex = i;
				}
			}	
			
			//check whether have max weight
			if(maxIndex != -1){
				
				//check whether have duplicated advertise
				boolean needAdd = true;
				VBADMatrix maxMT = (VBADMatrix)total.get(maxIndex); 
				if(matrixList != null && matrixList.size() > 0){
					for(Iterator it = matrixList.iterator(); it.hasNext();){
						VBADMatrix mt = (VBADMatrix)it.next();
						if(maxMT.getPrimaryKey().getAdId().equals(mt.getPrimaryKey().getAdId())){ 
						   needAdd = false;
						   total.remove(maxIndex);
						   k--;
						   if(maxMT.getWeight().doubleValue() > mt.getWeight().doubleValue()){
								matrixList.remove(mt);
								matrixList.add(maxMT);
								
						   }
						}
					}	
				}
				if(needAdd){
					matrixList.add(maxMT);
					total.remove(maxIndex);	
				}
				
			}
			
		}

		return listAdvertiseMatrix(matrixList);
	}
	
	
	//advertise keywords
	public static void createADKeywords(VBADKeywords keyword){
		getAdvertiseService().createADKeywords(keyword);
	}
	public static void updateADKeywords(VBADKeywords keyword){
		getAdvertiseService().updateADKeywords(keyword);
	}
	public static void deleteADKeywords(VBADKeywords keyword){
		getAdvertiseService().deleteADKeywords(keyword);
	}
	public static VBADKeywords getADKeywords(Long keywordId){
		return getAdvertiseService().getADKeywords(keywordId);
	}
	public static  VBADKeywords getADKeywords(String keyword){
		return getAdvertiseService().getADKeywords(keyword);
	}
	
	//advertise matrix
	public static void createADMatrix(VBADMatrix adMatrix){
		getAdvertiseService().createADMatrix(adMatrix);
	}
	public static void updateADMatrix(VBADMatrix adMatrix){
		getAdvertiseService().updateADMatrix(adMatrix);
	}
	public static void deleteADMatrix(VBADMatrix adMatrix){
		getAdvertiseService().deleteADMatrix(adMatrix);
	}
	public static  VBADMatrix getADMatrix(VBADMatrixPK pk){
		return getAdvertiseService().getADMatrix(pk);	
	}
	public static  List listADMatrix(Long adKeywordId){
		return getAdvertiseService().listADMatrix(adKeywordId);
	}
	public static  List listADMatrix(String keyword){
		return getAdvertiseService().listADMatrix(keyword);
	}
	
	//advertise download track
	public static  void createADDownloadTrack(VBADDTrack dTrack){
		getAdvertiseService().createADDownloadTrack(dTrack);
	}
	public static  void deleteADDownloadTrack(VBADDTrack dTrack){
		getAdvertiseService().deleteADDownloadTrack(dTrack);
	}
	public static  void updateADDownloadTrack(VBADDTrack dTrack){
		getAdvertiseService().updateADDownloadTrack(dTrack);
	}
	public static  VBADDTrack getADDownloadTrack(Long trackId){
		return getAdvertiseService().getADDownloadTrack(trackId);
	}
	

	//advertise visit track
	public static  void createADVisitTrack(VBADVTrack vTrack){
		getAdvertiseService().createADVisitTrack(vTrack);
	}
	public static  void deleteADVisitTrack(VBADVTrack vTrack){
		getAdvertiseService().deleteADVisitTrack(vTrack);
	}
	public static  void updateADVisitTrack(VBADVTrack vTrack){
		getAdvertiseService().updateADVisitTrack(vTrack);
	}
	public static  VBADVTrack getADVisitTrack(Long trackId){
		return getAdvertiseService().getADVisitTrack(trackId);
	}
	
	private static AdvertiseService getAdvertiseService(){
		ApplicationContext ctx = SpringUtil.getContext();
		AdvertiseServiceUtil util = (AdvertiseServiceUtil) ctx.getBean(AdvertiseServiceUtil.class.getName());
		return util.advertiseService;
	}
	

	public void setAdvertiseService(AdvertiseService advertiseService) {
		this.advertiseService = advertiseService;
	}
	private AdvertiseService advertiseService;
}
