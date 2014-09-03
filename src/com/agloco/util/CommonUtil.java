package com.agloco.util;

import com.agloco.web.session.SessionContext;

public class CommonUtil {

	private static int TRANSACTIONNO = 200;
	private static int ONE_SECOND = 60;
	private static int MINI_SUBMIT_TIME = 5 * ONE_SECOND;
	private static int MAX_SUBMIT_TIME = 15 * ONE_SECOND;

	public static Long getNextSubmitTime() {

		int currentOnlineCount = SessionContext.size();
		if (currentOnlineCount == 0) {
			return new Long(MINI_SUBMIT_TIME);
		}
		long nextSubmitTime = currentOnlineCount / TRANSACTIONNO;
		if (nextSubmitTime < MINI_SUBMIT_TIME) {
			return new Long(MINI_SUBMIT_TIME);
		} else if (nextSubmitTime > MAX_SUBMIT_TIME) {
			return new Long(MAX_SUBMIT_TIME);
		} else {
			return new Long(nextSubmitTime);
		}
	}
}
