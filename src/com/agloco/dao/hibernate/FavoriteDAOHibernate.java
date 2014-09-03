package com.agloco.dao.hibernate;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.agloco.dao.FavoriteDAO;
import com.agloco.model.VBFavorite;

/**
 * @author author E-mail:zhaon12@gmail.com
 * @version createDate:Apr 5, 2007 10:13:51 AM content
 */
public class FavoriteDAOHibernate extends HibernateDaoSupport implements
		FavoriteDAO {

	public List listFavorite(int active) {

		String selectSQL = "from VBFavorite fa where fa.active = ?";
		return getHibernateTemplate().find(selectSQL, Integer.valueOf(active));
	}

	public void createFavorite(VBFavorite favorite) {
		getHibernateTemplate().save(favorite);
	}

	public VBFavorite getFavorite(Long favoriteId) {
		String selectSQL = "from VBFavorite vbf where vbf.favoriteId = ?";
		List list = getHibernateTemplate().find(selectSQL, favoriteId);
		if (list == null || list.size() < 1) {
			return null;
		}
		return (VBFavorite) list.iterator().next();
	}

	public VBFavorite getFavorite(String url) {
		String selectSQL = "from VBFavorite vbf where vbf.url = ?";
		List list = getHibernateTemplate().find(selectSQL, url);
		if (list == null || list.size() < 1) {
			return null;
		}
		return (VBFavorite) list.iterator().next();
	}

	public void updateFavorite(VBFavorite favorite) {
		getHibernateTemplate().update(favorite);
	}

}
