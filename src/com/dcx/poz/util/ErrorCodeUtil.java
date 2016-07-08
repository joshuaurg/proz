package com.dcx.poz.util;

import java.util.HashMap;
import java.util.Map;

/**
 * 错误码
 *
 * @since 1.0
 * @version 1.0
 */
public class ErrorCodeUtil{
	
	private static ErrorCodeProperties pro = ErrorCodeProperties.getInstance();

	public static final String e10000 = "10000";   // 系统错误
	
	public static final String e10010 = "10010";   // 时间戳不能为空
	public static final String e10011 = "10011";   // 时间戳格式不正确
	public static final String e10012 = "10012";   // 时间戳范围不合法
	
	public static final String e10020 = "10020";   // 正在重入攻击
	public static final String e10021 = "10021";   // 请求签名不能为空
	public static final String e10022 = "10022";   // 请求签名不正确
	
	public static String errorMsg(String errorCode){
		Map<String, Object> ret = new HashMap<String, Object>();
		return errorMsg(ret,errorCode);
	}
	
	public static String errorMsg(Map<String, Object> map, String errorCode) {
		map.put("code", errorCode);
		map.put("status", "error");
		map.put("msg", pro.getProperties(errorCode));
		return JsonUtil.object2String(map);
	}
	
}
