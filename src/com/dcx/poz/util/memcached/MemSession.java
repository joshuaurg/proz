package com.dcx.poz.util.memcached;

import java.util.Map;


public class MemSession {
	
	// 会话ID
	private String sid      = null;
	// 存放本会话的所有信息
	private Map sessionInfo = null;
	
	/**
	 * 
	 * @param sid:会话id
	 * @param isCreate:是否创建
	 * @param expireType:过期类型
	 */
	public MemSession(String sid, boolean isCreate, String expireType) {
		this.sid = sid;
		this.sessionInfo = SessionService.getInstance().getSession(sid, isCreate,expireType);
	}

	public static MemSession getSession(String sid,String expireType) {
		MemSession session = null;
		session = new MemSession(sid, true,expireType);
		return session;
	}
	
	public static MemSession getSession(String sid,boolean isCreate,String expireType) {
		
		return new MemSession(sid,isCreate,expireType);
	}
	
	public static void removeSession(String sid) {
		SessionService.getInstance().removeSession(sid);
	}
	
	public Object getAttribute(String key) {
		return this.sessionInfo.get(key);
	}
	
	/**
	 * 
	 * @description 清空所有的session
	 * @author dcx
	 * @date 2016年2月26日 下午4:56:31
	 */
	public static void removeAllSession() {
		SessionService.getInstance().flushAll();		
	}
	
	/**
	 * 
	 * @description 获取所有会话信息
	 * @author dcx
	 * @date 2016年2月26日 下午4:57:04
	 * @return
	 */
	public Map getMap() {
		return sessionInfo;
	}
	
	public void removeAttribute(String key,String expireType) {
		if (key == null || key.trim().length() <= 0) {
			return;
		}
		this.sessionInfo.remove(key);
		SessionService.getInstance().saveSession(this.sid, this.sessionInfo,expireType);
	}

	public void setAttribute(String key, Object value,String expireType) {
		if (key == null || key.trim().length() <= 0 || value == null) {
			return;
		}
		this.sessionInfo.put(key, value);
		SessionService.getInstance().saveSession(this.sid, this.sessionInfo,expireType);
	}
}
