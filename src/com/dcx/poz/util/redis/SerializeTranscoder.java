package com.dcx.poz.util.redis;

import java.io.Closeable;

import org.apache.log4j.Logger;

/**
 * 
 * @description 序列化与反序列化操作
 * @author dcx
 * @date 2016年3月8日 下午5:11:20
 */
public abstract class SerializeTranscoder {

	protected static Logger logger = Logger.getLogger(SerializeTranscoder.class);
		  
	public abstract byte[] serialize(Object value);
	  
	public abstract Object deserialize(byte[] in);
	  
	public void close(Closeable closeable) {
	  if (closeable != null) {
	    try {
	      closeable.close();
	    } catch (Exception e) {
	       logger.info("Unable to close " + closeable, e); 
	    }
	  }
	}
}
