package com.agloco.web.session;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import com.agloco.util.CollectionFactory;

/**
 * 
 * @author Erick Kong
 * @see SessionPool.java
 * @createDate: 2007-4-3
 * @version 1.0
 */

public class SessionPool {

	public static Map get(String sessionId) {
		return _instance._get(sessionId);
	}

	public static void put(String sessionId, HttpSession session) {
		_instance._put(sessionId, session);
	}

	public static Map remove(String sessionId) {
		return _instance._remove(sessionId);
	}

	public static int size() {
		return _instance._size();
	}
	
	private int _size()
	{
		return _sessionPool.size();
	}

	private SessionPool() {
		_sessionPool = CollectionFactory.getSyncHashMap();
	}

	private Map _get(String sessionId) {
		Map sessions = (Map)_sessionPool.get(sessionId);

		if (sessions == null) {
			sessions = new LinkedHashMap();

			_sessionPool.put(sessionId, sessions);
		}

		return sessions;
	}

	private void _put(String sessionId, HttpSession session) {
		Map sessions = _get(sessionId);

		if (!sessions.containsKey(session.getId())) {
			sessions.put(session.getId(), session);
		}
	}

	private Map _remove(String sessionId) {
		return (Map)_sessionPool.remove(sessionId);
	}

	private static SessionPool _instance = new SessionPool();

	private Map _sessionPool;

}