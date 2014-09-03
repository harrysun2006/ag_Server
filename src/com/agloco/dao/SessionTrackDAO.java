package com.agloco.dao;

import com.agloco.model.VBSessionTrack;
/**
 * 
 * @author terry_zhao
 * @version 1.0
 */
public interface SessionTrackDAO {

	public void createSessionTrack(VBSessionTrack track);
	public void deleteSessionTrack(VBSessionTrack track);
	public void updateSessionTrack(VBSessionTrack track);
	public VBSessionTrack getSessionTrack(Long memberId);
	public VBSessionTrack getSessionTrack(String sessionId);
	
}
