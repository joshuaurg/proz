package com.dcx.poz.util.redis;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
/**
 * 
 * @description list实体的序列化和反序列化
 * @author dcx
 * @date 2016年3月14日 上午11:49:28
 * @param <E>
 */
public class ListTranscoder<E extends Serializable> extends SerializeTranscoder {

	@Override
	public byte[] serialize(Object value) {
		if (value == null)
	      throw new NullPointerException("Can't serialize null");
	    
	    List<E> values = (List<E>) value;
	    
	    byte[] results = null;
	    ByteArrayOutputStream bos = null;
	    ObjectOutputStream os = null;
	    
	    try {
	      bos = new ByteArrayOutputStream();
	      os = new ObjectOutputStream(bos);
	      for (E m : values) {
	        os.writeObject(m);
	      }
	      
	      // os.writeObject(null);
	      os.close();
	      bos.close();
	      results = bos.toByteArray();
	    } catch (IOException e) {
	      throw new IllegalArgumentException("Non-serializable object", e);
	    } finally {
	      close(os);
	      close(bos);
	    }
	    
	    return results;
	}

	@Override
	public Object deserialize(byte[] in) {
		List<E> list = new ArrayList<>();
	    ByteArrayInputStream bis = null;
	    ObjectInputStream is = null;
	    try {
	      if (in != null) {
	        bis = new ByteArrayInputStream(in);
	        is = new ObjectInputStream(bis);
	        while (true) {
	          E m = (E)is.readObject();
	          if (m == null) {
	            break;
	          }
	          list.add(m);
	        }
	        is.close();
	        bis.close();
	      }
	    } catch (IOException e) {  
	      logger.info(String.format("Caught IOException decoding %d bytes of data",in == null ? 0 : in.length) + e);  
	    } catch (ClassNotFoundException e) {  
		  logger.info(String.format("Caught CNFE decoding %d bytes of data",in == null ? 0 : in.length) + e);  
	    }  finally {
	      close(is);
	      close(bis);
	    }
	    return  list;
	}
}
