package com.dcx.poz.util;

import java.util.HashMap;
import java.util.Map;

public class ConstantUtil {

	//授权码过期类型---单位：分钟
	public static class MSessionExpireType{
		public final static Integer DEFAULT = 30*24*60;//授权码-30天
		public final static Integer E10M = 10;//临时授权码-10分钟(expire 10 minute)
		public final static Map<String,Integer> map = new HashMap<String,Integer>();
		static{
			map.put("default", DEFAULT);
			map.put("e10m", E10M);
		}
	}
}
