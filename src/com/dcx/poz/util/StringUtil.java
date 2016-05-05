package com.dcx.poz.util;

import java.util.Random;

public class StringUtil {

	private String randChars = "0123456789abcdefghigklmnopqrstuvtxyzABCDEFGHIGKLMNOPQRSTUVWXYZ";
	private String randCharsWithoutBig = "0123456789abcdefghigklmnopqrstuvtxyz";
	private Random random = new Random();	
	
	/**
	 * 
	 * @description 获取指定长度的随机字符串
	 * @author dcx
	 * @date 2016年2月26日 下午6:02:22
	 * @param length
	 * @param isOnlyNum
	 * @return
	 */
	public String getRandStr(int length, boolean isOnlyNum) {
		int size = isOnlyNum ? 10 : 62;
		StringBuffer hash = new StringBuffer(length);
		for (int i = 0; i < length; i++) {
			hash.append(randChars.charAt(random.nextInt(size)));
		}
		return hash.toString();
	}
}
