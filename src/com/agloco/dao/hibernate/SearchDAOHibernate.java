package com.agloco.dao.hibernate;

import java.util.Calendar;
import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.agloco.dao.SearchDAO;
import com.agloco.model.VBSearchEngine;
import com.agloco.model.VBSearchTrack;

/**
 * 
 * @author terry_zhao
 * @version 1.0
 */
public class SearchDAOHibernate extends HibernateDaoSupport implements SearchDAO{
	
	//searche engine
	public void createSearchEngine(VBSearchEngine sEngine){
		getHibernateTemplate().save(sEngine);
	}
	public void deleteSearchEngine(VBSearchEngine sEngine){
		getHibernateTemplate().delete(sEngine);
	}
	public void updateSearchEngine(VBSearchEngine sEngine){
		getHibernateTemplate().saveOrUpdate(sEngine);
	}
	public VBSearchEngine getSearchEngine(Long engineId){
		return (VBSearchEngine) getHibernateTemplate().get(VBSearchEngine.class, engineId);
	}
	public VBSearchEngine getSearchEngine(String engineName){
		List list = getHibernateTemplate().find("from VBSearchEngine se where se.name=?", engineName);
		if(list != null && list.size() > 0){
			return (VBSearchEngine)list.iterator().next();
		}
		return null;
	}
	public List listAllSearchEngine(){
		return getHibernateTemplate().find("from VBSearchEngine se order by se.engineId");
	}
	
	//search track
	public void createSearchTrack(VBSearchTrack sTrack){
		sTrack.setCreateDate(Calendar.getInstance());
		getHibernateTemplate().save(sTrack);
	}
	public void deleteSearchTrack(VBSearchTrack sTrack){
		getHibernateTemplate().delete(sTrack);
	}
	public void updateSearchTrack(VBSearchTrack sTrack){
		getHibernateTemplate().saveOrUpdate(sTrack);
	}
	public VBSearchTrack getSearchTrack(Long TrackId){
		return (VBSearchTrack)getHibernateTemplate().get(VBSearchTrack.class, TrackId);
	}
	public List ListSearchTrack(Long memberId){
		return getHibernateTemplate().find("from VBSearchTrack st where st.memberId", memberId);
	}
}
