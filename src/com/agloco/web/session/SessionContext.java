/**
 * Copyright (c) 2000-2006 Liferay, LLC. All rights reserved.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.agloco.web.session;

import java.util.Map;

import javax.servlet.http.HttpSession;

import com.agloco.Constants;
import com.agloco.util.CollectionFactory;

/**
 * 
 * @author Erick Kong
 * @see SessionContext.java
 * @createDate: 2007-4-3
 * @version 1.0
 */

public class SessionContext {
	
	public static HttpSession get(String sessionId) {
		return _instance._get(sessionId);
	}

	public static void put(String sessionId, HttpSession ses) {
		_instance._put(sessionId, ses);
	}

	public static HttpSession remove(String sessionId) {
		return _instance._remove(sessionId);
	}
	
	public static int size()
	{
		return _instance._size();
	}

	private int _size()
	{
		return _sessionPool.size();
	}

	private SessionContext() {
		_sessionPool = CollectionFactory.getSyncHashMap();
	}

	private HttpSession _get(String sessionId) {
		return (HttpSession)_sessionPool.get(sessionId);
	}

	private void _put(String sessionId, HttpSession ses) {
		_sessionPool.put(sessionId, ses);
	}

	private HttpSession _remove(String sessionId) {
		return (HttpSession)_sessionPool.remove(sessionId);
	}

	private static SessionContext _instance = new SessionContext();

	private Map _sessionPool;

}