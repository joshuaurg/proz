package com.dcx.poz.util;

import java.io.IOException;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;

public class JsonUtil {

	/**
	 * map转换json字符串
	 * @param value
	 * @return
	 */
	public static String object2String(Object value) {
		if(value == null)
			return null;
		ObjectMapper mapper = new ObjectMapper(); 
		// 过滤对象的null属性.
		mapper.getSerializationConfig().disable(SerializationConfig.Feature.WRITE_NULL_MAP_VALUES);		
		try {
			return mapper.writeValueAsString(value);
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
