package com.dcx.poz.util;

import java.security.MessageDigest;
import java.util.Iterator;
import java.util.Map;
/**
 * 
 * @author dcx
 * 签名算法-SHA1
 */
public class SignUtil {

	public static boolean checkSignature(Map<String, String> params, String signature){
        StringBuilder sb = new StringBuilder(); 
        Iterator<String> keyIt = params.keySet().iterator();
        while(keyIt.hasNext()){
        	String key = keyIt.next();
        	String value = params.get(key);
        	sb.append(key).append("=").append(value).append("&");
        }
        String content =  sb.substring(0, sb.length()-1);
        MessageDigest md = null;  
        String tmpStr = null;  
        try {  
            md = MessageDigest.getInstance("SHA-1");  
            // 将三个参数字符串拼接成一个字符串进行sha1加密  
            byte[] digest = md.digest(content.toString().getBytes());  
            tmpStr = byteToStr(digest);  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        content = null;  
        // 将sha1加密后的字符串可与signature对比
        return tmpStr != null ? tmpStr.equals(signature.toUpperCase()) : false;  
	}
	 /** 
     * 将字节数组转换为十六进制字符串 
     *  
     * @param byteArray 
     * @return 
     */  
    private static String byteToStr(byte[] byteArray) {  
        String strDigest = "";  
        for (int i = 0; i < byteArray.length; i++) {  
            strDigest += byteToHexStr(byteArray[i]);  
        }  
        return strDigest;  
    }  
  
    /** 
     * 将字节转换为十六进制字符串 
     *  
     * @param mByte 
     * @return 
     */  
    private static String byteToHexStr(byte mByte) {  
        char[] Digit = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };  
        char[] tempArr = new char[2];  
        tempArr[0] = Digit[(mByte >>> 4) & 0X0F];  
        tempArr[1] = Digit[mByte & 0X0F];  
  
        String s = new String(tempArr);  
        return s;  
    }  
}
