package com.agloco.dao.util;

import org.springframework.context.ApplicationContext;
import com.agloco.dao.IpCountryDAO;
import com.agloco.model.VBIpCountry;
import com.agloco.spring.SpringUtil;

public class IpCountryDAOUtil {

	public static String getIpCountryCode(String ip) throws Exception {
		return getIpCountryDAO().getIpCountry(ip).getCountryCode();
	}

	public static VBIpCountry getIpCountry(String ip) throws Exception {
		return getIpCountryDAO().getIpCountry(ip);
	}

	public static void addIpCountry(VBIpCountry ipcountry) {
		getIpCountryDAO().addIpCountry(ipcountry);
	}

	private IpCountryDAO ipCountryDAO;

	private static IpCountryDAO getIpCountryDAO() {
		ApplicationContext ctx = SpringUtil.getContext();
		IpCountryDAOUtil util = (IpCountryDAOUtil) ctx
				.getBean(IpCountryDAOUtil.class.getName());
		return util.ipCountryDAO;
	}

	public void setIpCountryDAO(IpCountryDAO ipCountryDAO) {
		this.ipCountryDAO = ipCountryDAO;
	}

}
