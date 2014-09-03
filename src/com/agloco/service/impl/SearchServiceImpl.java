package com.agloco.service.impl;

import java.util.List;

import com.agloco.dao.util.SearchDAOUtil;
import com.agloco.model.VBSearchEngine;
import com.agloco.model.VBSearchTrack;
import com.agloco.service.SearchService;

/**
 * 
 * @author terry_zhao
 * @version 1.0
 */
public class SearchServiceImpl implements SearchService {

	//searche engine
	public void createSearchEngine(VBSearchEngine sEngine) {
		SearchDAOUtil.createSearchEngine(sEngine);
	}
	public void deleteSearchEngine(VBSearchEngine sEngine) {
		SearchDAOUtil.deleteSearchEngine(sEngine);
	}
	public VBSearchEngine getSearchEngine(Long engineId) {
		return SearchDAOUtil.getSearchEngine(engineId);
	}
	public VBSearchEngine getSearchEngine(String engineName) {
		return SearchDAOUtil.getSearchEngine(engineName);
	}
	public void updateSearchEngine(VBSearchEngine sEngine) {
		SearchDAOUtil.updateSearchEngine(sEngine);
	}
	public List listAllSearchEngine(){
		return SearchDAOUtil.listAllSearchEngine();
	}
	
	//search track
	public void createSearchTrack(VBSearchTrack sTrack) {
		SearchDAOUtil.createSearchTrack(sTrack);
	}
	public void deleteSearchTrack(VBSearchTrack sTrack) {
		SearchDAOUtil.deleteSearchTrack(sTrack);
	}
	public VBSearchTrack getSearchTrack(Long TrackId) {
		return SearchDAOUtil.getSearchTrack(TrackId);
	}
	public void updateSearchTrack(VBSearchTrack sTrack) {
		SearchDAOUtil.updateSearchTrack(sTrack);
	}
	public List ListSearchTrack(Long memberId) {
		return SearchDAOUtil.ListSearchTrack(memberId);
	}

}
