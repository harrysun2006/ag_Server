package com.agloco.service;

import java.util.List;

import com.agloco.model.VBSearchEngine;
import com.agloco.model.VBSearchTrack;
import com.agloco.service.impl.SearchServiceImpl;

/**
 * 
 * @author terry_zhao
 * @see SearchServiceImpl
 * @version 1.0
 */
public interface SearchService extends BaseService {
	//searche engine
	public void createSearchEngine(VBSearchEngine sEngine);
	public void deleteSearchEngine(VBSearchEngine sEngine);
	public void updateSearchEngine(VBSearchEngine sEngine);
	public VBSearchEngine getSearchEngine(Long engineId);
	public VBSearchEngine getSearchEngine(String engineName);
	public List listAllSearchEngine();
	
	//search track
	public void createSearchTrack(VBSearchTrack sTrack);
	public void deleteSearchTrack(VBSearchTrack sTrack);
	public void updateSearchTrack(VBSearchTrack sTrack);
	public VBSearchTrack getSearchTrack(Long TrackId);
	public List ListSearchTrack(Long memberId);
}
