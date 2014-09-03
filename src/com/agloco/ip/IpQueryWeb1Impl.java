package com.agloco.ip;

import java.text.MessageFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.httpclient.HostConfiguration;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpConnectionManager;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.ProxyHost;
import org.apache.commons.httpclient.SimpleHttpConnectionManager;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpConnectionManagerParams;

import com.agloco.Constants;
import com.agloco.PropertiesKeys;
import com.agloco.util.PropsUtil;

/**
 * implement ip2CountryService via query ip from ip.webmasterhome.cn web site
 * @author harry_sun
 *
 */
public class IpQueryWeb1Impl implements Ip2CountryService {

	private final static String IP_QUERY_PATTERN = "http://ip.webmasterhome.cn/ipWhois.asp?ip={0}";
	private final static Pattern COUNTRY_CODE_PATTERN = Pattern.compile("[cC]ountry:\\s*(\\w+)");

	public String ip2Country(String ip) throws IpMappingException {

		//ProxyUtil.setHttpProxy();
		try {
			HttpClient client = new HttpClient();
			HttpConnectionManager manager = new SimpleHttpConnectionManager();
			HttpConnectionManagerParams params = new HttpConnectionManagerParams();
			params.setConnectionTimeout(Constants.HTTP_CONNECTION_TIMEOUT);
			manager.setParams(params);
			client.setHttpConnectionManager(manager);

			HostConfiguration conf = new HostConfiguration();
			client.setHostConfiguration(conf);
			String host = PropsUtil.get(PropertiesKeys.HTTP_PROXY_HOST);
			int port = PropsUtil.getInt(PropertiesKeys.HTTP_PROXY_PORT);
			if (host != null && port > 0) {
				ProxyHost proxy = new ProxyHost(host, port);
				conf.setProxyHost(proxy);
			}

			HttpMethod method = new GetMethod(MessageFormat.format(IP_QUERY_PATTERN, new Object[]{ip}));
			client.executeMethod(method);
			String html = new String(method.getResponseBody(), "GB2312");

			Matcher m = COUNTRY_CODE_PATTERN.matcher(html);
			if (m.find()) {
				return m.group(1);
			} else {
				throw new IpMappingException("Do NOT know what country this ip comes from ip.webmasterhome.cn!");
			}
		} catch(Exception e) {
			throw new IpMappingException(e);
		}
	}

	public void updateIpCountry(String ip, String country) throws Exception {
	}
}
