
package com.agloco.web.service.model;

/**
 * 
 * @author Erick Kong
 * @see TimeTrackListReq.java
 * @createDate: 2007-4-9
 * @version 1.0
 */

public class TimeTrackListReq extends VBReqList
{
	private Long submitId;

	public Long getSubmitId()
	{
		return this.submitId;
	}

	public void setSubmitId(Long submitId)
	{
		this.submitId = submitId;
	}

}

