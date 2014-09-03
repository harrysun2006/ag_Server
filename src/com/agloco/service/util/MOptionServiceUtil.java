package com.agloco.service.util;

import java.util.List;

import org.springframework.context.ApplicationContext;

import com.agloco.model.VBMOption;
import com.agloco.service.MOptionService;
import com.agloco.spring.SpringUtil;

/**
 * @author author E-mail:zhaon12@gmail.com
 * @version createDate:Apr 5, 2007 3:46:34 PM content
 */
public class MOptionServiceUtil {

	private MOptionService moptionService;

	public static void createMOption(VBMOption mOption) {
		getMoptionService().createMOption(mOption);
	}

	public static void updateMOption(VBMOption mOption) {
		getMoptionService().updateMOption(mOption);
	}

	public static VBMOption getMOption(Long memberId, String name) {
		return getMoptionService().getMOption(memberId, name);
	}

	public static List listMOpton(Long memberId) {
		return getMoptionService().listMOpton(memberId);
	}

	public static MOptionService getMoptionService() {
		ApplicationContext ctx = SpringUtil.getContext();
		MOptionServiceUtil util = (MOptionServiceUtil) ctx
				.getBean(MOptionServiceUtil.class.getName());
		return util.moptionService;
	}

	public void setMoptionService(MOptionService optionService) {
		moptionService = optionService;
	}

}
