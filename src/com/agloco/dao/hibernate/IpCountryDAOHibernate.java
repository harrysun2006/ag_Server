package com.agloco.dao.hibernate;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.agloco.dao.IpCountryDAO;
import com.agloco.model.VBIpCountry;
import com.agloco.util.IpAddressUtil;

public class IpCountryDAOHibernate extends HibernateDaoSupport implements
		IpCountryDAO {

	public void addIpCountry(VBIpCountry ipCountry) {
		getHibernateTemplate().save(ipCountry);
	}

	public VBIpCountry getIpCountry(String ip) throws Exception {
		Long ipNum = new Long(IpAddressUtil.ip2Long(ip));

		List list = getHibernateTemplate().find(
				"from VBIpCountry where beginIp <= ? and endIp >= ?",
				new Object[] { ipNum, ipNum });
		if (list.size() >= 1) {
			VBIpCountry ipCountry = (VBIpCountry) list.get(0);
			return ipCountry;
		} else {
			return null;
		}
		/*
		 * String hql = "from VBIpCountry where beginIp <= :beginIp and endIp >=
		 * :endIp"; Query query = getSession().createQuery(hql);
		 * query.setParameter("beginIp", ipNum); query.setParameter("endIp", ipNum);
		 * return query.list();
		 */
	}
}
