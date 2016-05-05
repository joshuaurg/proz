package com.dcx.poz.util.memcached;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import com.danga.MemCached.MemCachedClient;  
import com.danga.MemCached.SockIOPool;
import com.dcx.poz.util.ConstantUtil;
import com.dcx.poz.util.ResourceLoader;  

public class SessionService {  
	
	private static SessionService instance = null;
	
    private SockIOPool pool = null;
    
    private static Date getExpiryDate(String msessionexpireType) {
		Calendar calendar = Calendar.getInstance();
		long time = calendar.getTimeInMillis();
		time += ConstantUtil.MSessionExpireType.map.get(msessionexpireType) * 60 * 1000;
		calendar.setTimeInMillis(time);

		return calendar.getTime();
	}
    
    public synchronized static SessionService getInstance() {
		if (instance == null) {
			instance = new SessionService();
		}
		return instance;
	}
    
    protected SessionService(){  
    	String[] servers = {ResourceLoader.paramsConfig.getProperty("memcacheIP")};  
        //创建一个连接池  
        pool = SockIOPool.getInstance();
        //设置权重
        Integer[] weights = {3};
        //设置缓存服务器  
        pool.setServers(servers);  
        pool.setWeights( weights );
        //设置初始化连接数，最小连接数，最大连接数以及最大处理时间  
        pool.setInitConn(50);  
        pool.setMinConn(50);
        pool.setMaxConn(500);
        pool.setMaxIdle(1000 * 60 * 60);
        //设置主线程睡眠时间，每3秒苏醒一次，维持连接池大小  
        //maintSleep 千万不要设置成30，访问量一大就出问题，单位是毫秒，推荐30000毫秒。  
        pool.setMaintSleep(3000);  
        //关闭套接字缓存  
        pool.setNagle(false);  
        //连接建立后的超时时间  
        pool.setSocketTO(3000);  
        //连接建立时的超时时间  
        pool.setSocketConnectTO(0);  
        //初始化连接池
        pool.initialize();
    } 
    
    public synchronized boolean sessionExists(String id) {
    	MemCachedClient mcc = this.getMemCachedClient();
    	return mcc.keyExists(id);
	}
    
    public synchronized void removeSession(String id) {
    	MemCachedClient mcc = this.getMemCachedClient();
    	mcc.delete(id);
	} 
    
    public synchronized Map getSession(String id, boolean create,String expireType) {
    	MemCachedClient mcc = this.getMemCachedClient();
    	Map session = (Map) mcc.get(id);
		if (session == null) {
			if (create) {
				session = new HashMap(5);
				mcc.add(id, session, getExpiryDate(expireType));
			}
		}
		return session;
	}
    
    public synchronized void saveSession(String id, Map session,String expireType) {
    	MemCachedClient mcc = this.getMemCachedClient();
    	if (mcc.keyExists(id)) {
			mcc.replace(id, session,getExpiryDate(expireType));
		} else {
			mcc.add(id, session,getExpiryDate(expireType));
		}
	}
    
    public synchronized void saveSession(String id, Map session, Date expiryDate) {
    	MemCachedClient mcc = this.getMemCachedClient();
    	if (mcc.keyExists(id)) {
			mcc.replace(id, session, expiryDate);
		} else {
			mcc.add(id, session, expiryDate);
		}
	}
    
    public synchronized void flushAll(){
    	MemCachedClient mcc = this.getMemCachedClient();
    	mcc.flushAll();
    }
    
    public synchronized void updateExpiryDate(String id,String expireType) {
    	MemCachedClient mcc = this.getMemCachedClient();
    	Map session = (Map) mcc.get(id);
		if (session != null) {
			mcc.replace(id, session, getExpiryDate(expireType));
		}
	}
    
    private synchronized static MemCachedClient getMemCachedClient() {
    	MemCachedClient mc = new MemCachedClient();
		return mc;
	}
    
    protected void finalize() {
		if (this.pool != null) {
			this.pool.shutDown();
		}
	}
}  