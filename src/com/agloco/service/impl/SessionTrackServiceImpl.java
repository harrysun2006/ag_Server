package com.agloco.service.impl;

import com.agloco.dao.util.SessionTrackDAOUtil;
import com.agloco.model.VBSessionTrack;
import com.agloco.service.SessionTrackService;

public class SessionTrackServiceImpl implements SessionTrackService {

	public void createSessionTrack(VBSessionTrack track) {
		SessionTrackDAOUtil.createSessionTrack(track);
	}

	public void deleteSessionTrack(VBSessionTrack track) {
		SessionTrackDAOUtil.deleteSessionTrack(track);
	}

	public VBSessionTrack getSessionTrack(Long memberId) {
		return SessionTrackDAOUtil.getSessionTrack(memberId);
	}

	public VBSessionTrack getSessionTrack(String sessionId) {
		return SessionTrackDAOUtil.getSessionTrack(sessionId);
	}

	public void updateSessionTrack(VBSessionTrack track) {
		SessionTrackDAOUtil.updateSessionTrack(track);
	}

}
