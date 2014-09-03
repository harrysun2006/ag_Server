package com.agloco.service;

import com.agloco.model.VBSessionTrack;

public interface SessionTrackService extends BaseService {

	public void createSessionTrack(VBSessionTrack track);
	public void deleteSessionTrack(VBSessionTrack track);
	public void updateSessionTrack(VBSessionTrack track);
	public VBSessionTrack getSessionTrack(Long memberId);
	public VBSessionTrack getSessionTrack(String sessionId);
}
