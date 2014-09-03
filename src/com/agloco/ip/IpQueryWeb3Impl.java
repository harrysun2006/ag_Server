package com.agloco.ip;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.httpclient.HostConfiguration;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpConnectionManager;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.ProxyHost;
import org.apache.commons.httpclient.SimpleHttpConnectionManager;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpConnectionManagerParams;
import org.apache.commons.httpclient.params.HttpMethodParams;

import com.agloco.Constants;
import com.agloco.PropertiesKeys;
import com.agloco.util.PropsUtil;

/**
 * implement ip2CountryService via query ip from www.ip2location.com web site
 * this demo version is limited to 20 lookups per day only.
 * @author harry_sun
 *
 */
public class IpQueryWeb3Impl implements Ip2CountryService {

	private final static String IP_QUERY_URL = "http://www.ip2location.com/free.asp";
	private final static Pattern COUNTRY_CODE_PATTERN = Pattern.compile(".*?</tr>\\s*<tr><td.*?>.*?</td><td.*?>(\\w+)</td>", Pattern.DOTALL);

	public String ip2Country(String ip) throws IpMappingException {
		String countryCode = null;
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

			PostMethod method = new PostMethod(IP_QUERY_URL);
      NameValuePair[] fields = {
	      new NameValuePair("ipaddresses", ip),
      };
      method.setRequestBody(fields);
			client.executeMethod(method);
			String html = new String(method.getResponseBody());

			int s = html.indexOf("Country (Short)");
			int e = html.indexOf("</table>", s);
			Matcher m = COUNTRY_CODE_PATTERN.matcher(html.subSequence(s, e));
			if (m.find()) {
				countryCode = m.group(1);
			}
			if (countryCode == null) throw new IpMappingException("Do NOT know what country this ip comes from www.ip2location.com!");
		} catch(Exception e) {
			throw new IpMappingException(e);
		}
		return countryCode;
	}

	public void updateIpCountry(String ip, String country) throws Exception {
	}
}
