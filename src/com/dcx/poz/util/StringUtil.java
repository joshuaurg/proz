package com.dcx.poz.util;

import java.util.List;
import java.util.Random;

import com.dcx.poz.model.AlbumGroup;
import com.dcx.poz.model.Poem;

public class StringUtil {

	private static String randChars = "0123456789abcdefghigklmnopqrstuvtxyzABCDEFGHIGKLMNOPQRSTUVWXYZ";
	private static String randCharsWithoutBig = "0123456789abcdefghigklmnopqrstuvtxyz";
	private static Random random = new Random();	
	
	/**
	 * 
	 * @description 获取指定长度的随机字符串
	 * @author dcx
	 * @date 2016年2月26日 下午6:02:22
	 * @param length
	 * @param isOnlyNum
	 * @return
	 */
	public static String getRandStr(int length, boolean isOnlyNum) {
		int size = isOnlyNum ? 10 : 62;
		StringBuffer hash = new StringBuffer(length);
		for (int i = 0; i < length; i++) {
			hash.append(randChars.charAt(random.nextInt(size)));
		}
		return hash.toString();
	}
	
	public static boolean isEmpty(String str) {
		return (str!=null && !"".equals(str))?false:true;
	}

	public static <T> boolean isEmpty(List<T> rows) {
		if(rows!=null && rows.size()>0){
			return false;
		}
		return true;
	}

	public static boolean checkLength(String target, int minLen, int maxLen) {
		if(target==null){
			return false;
		}
		if(target.length() < minLen || target.length() > maxLen){
			 return false;
		}
		return true;
	}
 
}
