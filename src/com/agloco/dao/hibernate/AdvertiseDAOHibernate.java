package com.agloco.dao.hibernate;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.agloco.dao.AdvertiseDAO;
import com.agloco.model.VBADDTrack;
import com.agloco.model.VBADKeywords;
import com.agloco.model.VBADMatrix;
import com.agloco.model.VBADMatrixPK;
import com.agloco.model.VBADVTrack;
import com.agloco.model.VBAdvertise;

/**
 * 
 * @author terry_zhao
 * @version 1.0
 */
public class AdvertiseDAOHibernate extends HibernateDaoSupport implements
		AdvertiseDAO {


	//advertise
	public void createAdvertise(VBAdvertise advertise) {
		advertise.setCreateDate(Calendar.getInstance());
		getHibernateTemplate().save(advertise);
	}
	public void deleteAdvertise(VBAdvertise advertise) {
		getHibernateTemplate().delete(advertise);
	}
	public VBAdvertise getAdvertise(String advertiseId) {
		return (VBAdvertise)getHibernateTemplate().get(VBAdvertise.class, advertiseId);
	}
	public void updateAdvertise(VBAdvertise advertise) {
		getHibernateTemplate().saveOrUpdate(advertise);
	}
	
	public List<VBAdvertise> listAdvertiseByType(String type){
		return getHibernateTemplate().find("from VBAdvertise ad where ad.type=?", type);
	}
	
	//advertise keywords
	public void createADKeywords(VBADKeywords keyword) {
		keyword.setCreateDate(Calendar.getInstance());
		getHibernateTemplate().save(keyword);
	}
	public void deleteADKeywords(VBADKeywords keyword) {
		getHibernateTemplate().delete(keyword);
	}
	public VBADKeywords getADKeywords(Long keywordId) {
		return (VBADKeywords)getHibernateTemplate().get(VBADKeywords.class, keywordId);
	}
	public VBADKeywords getADKeywords(String keyword){
		List list = getHibernateTemplate().find("from VBADKeywords kw where kw.content=?", keyword);
		if(list !=null && list.size() > 0){
			return (VBADKeywords)list.iterator().next();
		}
		return null;
	}
	public void updateADKeywords(VBADKeywords keyword) {
		getHibernateTemplate().saveOrUpdate(keyword);
	}
	
	
	//advertise matrix
	public void createADMatrix(VBADMatrix adMatrix) {
		adMatrix.setCreateDate(Calendar.getInstance());
		getHibernateTemplate().save(adMatrix);
	}
	public void deleteADMatrix(VBADMatrix adMatrix) {
		getHibernateTemplate().delete(adMatrix);
	}
	public VBADMatrix getADMatrix(VBADMatrixPK pk) {
		return (VBADMatrix)getHibernateTemplate().get(VBADMatrix.class, pk);
	}
	public void updateADMatrix(VBADMatrix adMatrix) {
		getHibernateTemplate().saveOrUpdate(adMatrix);
	}
	public List listADMatrix(Long adKeywordId){
		return getHibernateTemplate().find("from VBADMatrix mtx where mtx.primaryKey.adKeywordId=?", adKeywordId);
	}
	public List listADMatrix(final String keyword){
		return (List) getHibernateTemplate().execute(new HibernateCallback(){

			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				StringBuffer sql = new StringBuffer();
				sql.append("select mtx from VBADMatrix mtx,VBADKeywords kw ")
				   .append("where mtx.primaryKey.adKeywordId = kw.adKeywordId and ")
				   .append("kw.content like :keyword order by mtx.weight desc");
				Query q = getSession().createQuery(sql.toString());
				q.setParameter("keyword", "%" + keyword + "%");
				return q.list();
			}
			
		});
//		return getHibernateTemplate().find("select mtx from VBADMatrix mtx,VBADKeywords kw " +
//										   "where mtx.primaryKey.adKeywordId = kw.adKeywordId and " +
//										   "kw.content like ? order by mtx.weight desc", "%" + keyword + "%");
		
	}

	//advertise download track
	public void createADDownloadTrack(VBADDTrack dTrack){
		dTrack.setCreateDate(Calendar.getInstance());
		getHibernateTemplate().save(dTrack);
	}
	public void deleteADDownloadTrack(VBADDTrack dTrack){
		getHibernateTemplate().delete(dTrack);
	}
	public void updateADDownloadTrack(VBADDTrack dTrack){
		getHibernateTemplate().saveOrUpdate(dTrack);
	}
	public VBADDTrack getADDownloadTrack(Long trackId){
		return (VBADDTrack)getHibernateTemplate().get(VBADDTrack.class, trackId);
	}
	
	//advertise visit track
	public void createADVisitTrack(VBADVTrack vTrack){
		vTrack.setCreateDate(Calendar.getInstance());
		getHibernateTemplate().save(vTrack);
	}
	public void deleteADVisitTrack(VBADVTrack vTrack){
		getHibernateTemplate().delete(vTrack);
	}
	public void updateADVisitTrack(VBADVTrack vTrack){
		getHibernateTemplate().saveOrUpdate(vTrack);
	}
	public VBADVTrack getADVisitTrack(Long trackId){
		return (VBADVTrack)getHibernateTemplate().get(VBADVTrack.class, trackId);
	}

}
