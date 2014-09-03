package com.agloco.dao.util;

import java.util.List;

import org.springframework.context.ApplicationContext;

import com.agloco.dao.FavoriteDAO;
import com.agloco.model.VBFavorite;
import com.agloco.spring.SpringUtil;

/**
 * @author author E-mail:zhaon12@gmail.com
 * @version createDate:Apr 5, 2007 10:16:24 AM content
 */
public class FavoriteDAOUtil {

	private FavoriteDAO favoriteDAO;

	public static List listFavorite(int active) {
		return getFavoriteDAO().listFavorite(active);
	}

	public static void createFavorite(VBFavorite favorite) {
		getFavoriteDAO().createFavorite(favorite);
	}

	public static void updateFavorite(VBFavorite favorite) {
		getFavoriteDAO().updateFavorite(favorite);
	}

	public static VBFavorite getFavorite(Long favoriteId) {
		return getFavoriteDAO().getFavorite(favoriteId);
	}

	public static VBFavorite getFavorite(String url) {
		return getFavoriteDAO().getFavorite(url);
	}

	public static FavoriteDAO getFavoriteDAO() {
		ApplicationContext ctx = SpringUtil.getContext();
		FavoriteDAOUtil util = (FavoriteDAOUtil) ctx
				.getBean(FavoriteDAOUtil.class.getName());
		return util.favoriteDAO;
	}

	public void setFavoriteDAO(FavoriteDAO favoriteDAO) {
		this.favoriteDAO = favoriteDAO;
	}

}
