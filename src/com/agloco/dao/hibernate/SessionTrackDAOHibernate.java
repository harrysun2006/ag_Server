package com.agloco.dao.hibernate;

import java.util.Calendar;
import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.agloco.dao.SessionTrackDAO;
import com.agloco.model.VBSessionTrack;

/**
 * 
 * @author terry_zhao
 * @version 1.0
 */
public class SessionTrackDAOHibernate extends HibernateDaoSupport implements
		SessionTrackDAO {

	public void createSessionTrack(VBSessionTrack track) {
		track.setModifiedDate(Calendar.getInstance());
		getHibernateTemplate().save(track);
	}

	public void deleteSessionTrack(VBSessionTrack track) {
		getHibernateTemplate().delete(track);
	}

	public VBSessionTrack getSessionTrack(Long memberId) {
		return (VBSessionTrack)getHibernateTemplate().get(VBSessionTrack.class, memberId);
	}

	public VBSessionTrack getSessionTrack(String sessionId) {
		List list = getHibernateTemplate().find("from VBSessionTrack st where st.sessionId=?", sessionId);
		if(list == null || list.size() < 1){
			return null;
		}
		return (VBSessionTrack) list.iterator().next();
	}

	public void updateSessionTrack(VBSessionTrack track) {
		track.setModifiedDate(Calendar.getInstance());
		getHibernateTemplate().saveOrUpdate(track);
	}

}
