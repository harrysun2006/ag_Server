package com.agloco.service;

import java.util.List;

import com.agloco.model.VBFavorite;

/**
 * @author author E-mail:zhaon12@gmail.com
 * @version createDate:Apr 5, 2007 10:18:21 AM
 * content
 */
public interface FavoriteService extends BaseService {
	
	public void createFavorite(VBFavorite favorite);
	public void updateFavorite(VBFavorite favorite);
	public VBFavorite getFavorite(Long favoriteId);
	public VBFavorite getFavorite(String url);
	public List listFavorite(int active);
	
}
