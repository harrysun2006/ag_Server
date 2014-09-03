
package com.agloco.dao.hibernate;

import java.util.Calendar;
import java.util.List;

import org.hibernate.Query;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.agloco.Constants;
import com.agloco.dao.AffiliateDAO;
import com.agloco.model.VBAFDetail;
import com.agloco.model.VBAffiliate;

/**
 * 
 * @author Erick Kong
 * @see AffiliateDAOHibernate.java
 * @createDate: 2007-4-12
 * @version 1.0
 */

public class AffiliateDAOHibernate extends HibernateDaoSupport implements AffiliateDAO
{
	//AFDetail
	public void createAFDetail(VBAFDetail vbAFDetail)
	{
		// TODO Auto-generated method stub
		getHibernateTemplate().save(vbAFDetail);
	}

	public void updateAFDetail(VBAFDetail vbAFDetail)
	{
		// TODO Auto-generated method stub
		getHibernateTemplate().saveOrUpdate(vbAFDetail);
	}

	public void deleteAFDetail(VBAFDetail vbAFDetail)
	{
		// TODO Auto-generated method stub
		getHibernateTemplate().delete(vbAFDetail);
	}

	public VBAFDetail getAFDetail(Long afDetailId)
	{
		// TODO Auto-generated method stub
		List list = getHibernateTemplate().find("from VBAFDetail");
		if(list!=null & list.size()>0)
			return (VBAFDetail)list.iterator().next();
		return null;
	}

	public List getAFDetails()
	{
		// TODO Auto-generated method stub
		return getHibernateTemplate().find("from VBAFDetail");
	}

	public List getAFDetails(Long affiliateId)
	{
		// TODO Auto-generated method stub
		return getHibernateTemplate().find("from VBAFDetail vbfd where vbfd.affiliateId="+affiliateId);
	}

	public List getNewAFDetails(Calendar afterDate)
	{
		// TODO Auto-generated method stub
		String sql = "from VBAFDetail vbfd where vbfd.createDate > :createDate order by vbfd.createDate asc";
		Query query = getSession().createQuery(sql);
		query.setParameter("createDate", afterDate);
		query.setMaxResults(Constants.AFFILIATEPICKNUM);
		return query.list();
	}

	public List getNewAFDetails(Long affiliateId, Calendar afterDate)
	{
		// TODO Auto-generated method stub
		String sql = "from VBAFDetail vbfd where vbfd.affiliateId=:affiliateId and vbfd.createDate > :createDate order by vbfd.createDate asc";
		Query query = getSession().createQuery(sql);
		query.setParameter("affiliateId", affiliateId);
		query.setParameter("createDate", afterDate);
		query.setMaxResults(Constants.AFFILIATEPICKNUM);
		return query.list();
	}


	//Affiliate
	public void createAffiliate(VBAffiliate vbaffiliate)
	{
		// TODO Auto-generated method stub
		getHibernateTemplate().save(vbaffiliate);
	}

	public void updateAffiliate(VBAffiliate vbaffiliate)
	{
		// TODO Auto-generated method stub
		getHibernateTemplate().saveOrUpdate(vbaffiliate);
	}

	public void deleteAffiliate(VBAffiliate vbaffiliate)
	{
		// TODO Auto-generated method stub
		getHibernateTemplate().delete(vbaffiliate);
	}

	public VBAffiliate getAffiliate(Long affiliateId)
	{
		// TODO Auto-generated method stub
		return (VBAffiliate)getHibernateTemplate().find("from VBAffiliate vbf where vbf.affiliateId="+affiliateId);
	}

	public List getAllAffiliates()
	{
		// TODO Auto-generated method stub
		return getHibernateTemplate().find("from VBAffiliate");
	}

	public List getNewAffiliates(Calendar afterDate)
	{
		// TODO Auto-generated method stub
		String sql = "from VBAffiliate vbf where vbf.createDate > :createDate order by vbf.createDate asc";
		Query query = getSession().createQuery(sql);
		query.setParameter("createDate", afterDate);
		query.setMaxResults(Constants.AFFILIATEPICKNUM);
		return query.list();
	}

}

