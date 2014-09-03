package com.agloco.util;

import java.net.Authenticator;
import java.net.PasswordAuthentication;
import java.util.Properties;

import com.agloco.PropertiesKeys;

public class ProxyUtil {

	public static void setHttpProxy() {
		String host = PropsUtil.get(PropertiesKeys.HTTP_PROXY_HOST);
		String port = PropsUtil.get(PropertiesKeys.HTTP_PROXY_PORT);
		if (host != null && port != null) {
			Properties sysProps = System.getProperties();
			sysProps.put("proxyHost", host);
			sysProps.put("proxyPort", port);
		}
		String username = PropsUtil.get(PropertiesKeys.HTTP_PROXY_AUTHENTICATOR_USERNAME);
		String password = PropsUtil.get(PropertiesKeys.HTTP_PROXY_AUTHENTICATOR_PASSWORD);
		if (username != null && password != null) {
			Authenticator authenticator = new HttpProxyAuthenticator(username, password);
			Authenticator.setDefault(authenticator);
		}
	}

	private static class HttpProxyAuthenticator extends Authenticator {

		private String username;
		private String password;

		public HttpProxyAuthenticator(String username, String password) {
			this.username = username;
			this.password = password;
		}

		protected PasswordAuthentication getPasswordAuthentication() {
			return new PasswordAuthentication(username, password.toCharArray());
		}

	}
}
