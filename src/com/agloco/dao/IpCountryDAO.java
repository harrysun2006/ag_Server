package com.agloco.dao;

import com.agloco.model.VBIpCountry;

/**
 * @author Eric_Zhang
 */
public interface IpCountryDAO {

	public VBIpCountry getIpCountry(String ip) throws Exception;

	public void addIpCountry(VBIpCountry ipCountry);
}
