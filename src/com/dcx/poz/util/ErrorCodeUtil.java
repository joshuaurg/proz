package com.dcx.poz.util;

import java.util.HashMap;
import java.util.Map;

/**
 * 错误码
 *
 * @since 1.0
 * @version 1.0
 */
public class ErrorCodeUtil
{
	
	private static ErrorCodeProperties pro = ErrorCodeProperties.getInstance();

	public static final String e10001 = "10001";   // 系统错误
	
	public static String errorMsg(Map<String, Object> map, String errorCode) {
		map.put("errorCode", errorCode);
		map.put("error", pro.getProperties(errorCode));
		return JsonUtil.object2String(map);
	}
	
	public static String errorMsg(String errorCode){
		Map<String, Object> ret = new HashMap<String, Object>();
		ret.put("errorCode", errorCode);
		ret.put("error", pro.getProperties(errorCode));
		return JsonUtil.object2String(ret);
	}

}
