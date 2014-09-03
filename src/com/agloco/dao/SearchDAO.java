package com.agloco.dao;

import java.util.List;

import com.agloco.dao.hibernate.SearchDAOHibernate;
import com.agloco.model.VBSearchEngine;
import com.agloco.model.VBSearchTrack;

/**
 * 
 * @author terry_zhao
 * @see SearchDAOHibernate
 * @version 1.0
 */
public interface SearchDAO {

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
