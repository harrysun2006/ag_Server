package com.agloco.model;

import java.util.Calendar;

/**
 * 
 * @author terry_zhao
 *
 */
public class TemporaryMember extends MemberModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7489525274306264892L;
	private String referralCode;
	private Integer mailCount;
	private Calendar lastMailTime;
	private String mailResult;
	
	public Calendar getLastMailTime() {
		return lastMailTime;
	}
	public void setLastMailTime(Calendar lastMailTime) {
		this.lastMailTime = lastMailTime;
	}
	public Integer getMailCount() {
		return mailCount;
	}
	public void setMailCount(Integer mailCount) {
		this.mailCount = mailCount;
	}
	public String getMailResult() {
		return mailResult;
	}
	public void setMailResult(String mailResult) {
		this.mailResult = mailResult;
	}
	public String getReferralCode() {
		return referralCode;
	}
	public void setReferralCode(String referralCode) {
		this.referralCode = referralCode;
	}
}
