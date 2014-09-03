package com.agloco;

import com.agloco.util.PropsUtil;

public interface Constants {

	public static final StringBuffer AGLOCO_AESKEY = new StringBuffer("agloco");
	
	public final static int    ADVERTISE_COMPARE_MAX_NUMBER  = PropsUtil.getInt(PropertiesKeys.ADVERTISE_COMPARE_MAX_NUMBER,5);
	public final static int    ADVERTISE_DOWNLOAD_MAX_NUMBER = PropsUtil.getInt(PropertiesKeys.ADVERTISE_DOWNLOAD_MAX_NUMBER,5);
	public final static double ADVERTISE_WEIGHT_INCREASE     = PropsUtil.getDouble(PropertiesKeys.ADVERTISE_WEIGHT_INCREASE,100);
	public final static String ADVERTISE_TYPE_URL            = "url";
	public final static String ADVERTISE_TYPE_JAVASCRIPT     = "js";
	public final static String ADVERTISE_TYPE_PIC            = "pic";
	public final static String ADVERTISE_TYPE_FLASH          = "flash";
	public final static String ADVERTISE_TYPE_VIDEO          = "video";
	public final static String ADVERTISE_TYPE_TEXT           = "text";
	public final static String ADVERTISE_TYPE_DEFAULT        = "def";
	public final static String ADVERTISE_REPLACE_STRING      = "{-[+]-}";
	public static final String DATABASE_CHARSET = "UTF-8";
	
	public static final String LOG_OPERATE_LOGIN       = "login";
	public static final String LOG_OPERATE_LOGOUT      = "logout";
	public static final String LOG_OPERATE_AUTO_LOGIN  = "autologin";
	
	public static final String RESPONSE_RESULT_SUCCESS = "success";
	public static final String RESPONSE_RESULT_FAILURE = "failure";
	
	public static final String REQUEST_FILE_ITEMS         = "fileItems";
	public static final String REQUEST_SERVICE_NAME       = "serviceName";
	public static final String LOGIN_SERVICE              = "loginService";
	public static final String AUTO_LOGIN_SERVICE         = "autoLoginService";
	public static final String AUTO_UPDATE_SERVICE        = "autoUpdateService";
	public static final String PERFORMANCE_SERVICE        = "performanceService";
	public static final String VIEWBAR_CHECK_SERVICE      = "viewbarCheckService";
	public static final String DOWNLOAD_COUNT_SERVICE     = "downloadCountService";

	public static final String SESSION_MEMBER_ID      = "session.member.id";
	public static final String SESSION_CLIENT_INFO    = "session.client.info";
	
	public static final String VIEWBAR_PERFORMANCE_TYPE = "perfo";
	public static final int VIEWBAR_RULE_ACTIVE         = 1;
	public static final int VIEWBAR_FAVORITE_ACTIVE     = 1;
	public static final String VIEWBAR_AUTO_UPDATE_FLAG = "viewbar_auto_update_flag";
	
	public static final String PERFORMANCE_CPU = "cpuPerformance";
	public static final String PERFORMANCE_MEM = "memPerformance";
	public static final String PERFORMANCE_DOWNLOAD_COUNT = "downloadCount";
	public static final String PERFORMANCE_ONLINE_COUNT = "onlineCount";
	
	public static final String AUTO_UPDATE_START = "autoUpdateStart";
	public static final String AUTO_UPDATE_END   = "autoUpdateEnd";
	
	public static final String XML_FILE_HEADER = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>";
	
	public static final int AFFILIATEPICKNUM = 1000;

	public final static int HTTP_CONNECTION_TIMEOUT = PropsUtil.getInt(PropertiesKeys.HTTP_CONNECTION_TIMEOUT, 20000);
	public final static String ADVERTISE_TEMPLATE_BASENAME_COUNTRY = "advertise.template.basename.country.";
	public final static String ADVERTISE_TEMPLATE_JSNAME_COUNTRY = "advertise.template.jsname.country.";
	public final static String DEFAULT_ADVERTISE_TEMPLATE_BASENAME = "ads-wikia";
	public final static String DEFAULT_ADVERTISE_TEMPLATE_JSNAME = "ads-wikia.js";

}

