package com.agloco.dao.util;

import java.util.List;

import org.springframework.context.ApplicationContext;

import com.agloco.dao.MOptionDAO;
import com.agloco.model.VBMOption;
import com.agloco.spring.SpringUtil;

/**
 * @author author E-mail:zhaon12@gmail.com
 * @version createDate:Apr 5, 2007 3:40:38 PM content
 */
public class MOptionDAOUtil {

	private MOptionDAO moptionDAO;

	public static void createMOption(VBMOption mOption) {
		getMoptionDAO().createMOption(mOption);
	}

	public static void updateMOption(VBMOption mOption) {
		getMoptionDAO().updateMOption(mOption);
	}

	public static VBMOption getMOption(Long memberId, String name) {
		return getMoptionDAO().getMOption(memberId, name);
	}

	public static List listMOpton(Long memberId) {
		return getMoptionDAO().listMOpton(memberId);
	}

	public static MOptionDAO getMoptionDAO() {
		ApplicationContext ctx = SpringUtil.getContext();
		MOptionDAOUtil util = (MOptionDAOUtil) ctx.getBean(MOptionDAOUtil.class
				.getName());
		return util.moptionDAO;
	}

	public void setMoptionDAO(MOptionDAO optionDAO) {
		moptionDAO = optionDAO;
	}

}
