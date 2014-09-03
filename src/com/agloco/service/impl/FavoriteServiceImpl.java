package com.agloco.service.impl;

import java.util.List;

import com.agloco.dao.util.FavoriteDAOUtil;
import com.agloco.model.VBFavorite;
import com.agloco.service.FavoriteService;

/**
 * @author author E-mail:zhaon12@gmail.com
 * @version createDate:Apr 5, 2007 10:19:00 AM content
 */
public class FavoriteServiceImpl extends BaseServiceImpl implements
		FavoriteService {

	public List listFavorite(int active) {
		return FavoriteDAOUtil.listFavorite(active);
	}

	public void createFavorite(VBFavorite favorite) {
		FavoriteDAOUtil.createFavorite(favorite);
	}

	public VBFavorite getFavorite(Long favoriteId) {
		return FavoriteDAOUtil.getFavorite(favoriteId);
	}

	public VBFavorite getFavorite(String url) {
		return FavoriteDAOUtil.getFavorite(url);
	}

	public void updateFavorite(VBFavorite favorite) {
		FavoriteDAOUtil.updateFavorite(favorite);
	}
	

}
