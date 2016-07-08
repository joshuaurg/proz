package com.dcx.poz.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegUitl {
	
	//只能是数字
	private static String digitalReg = "^[0-9]*$";
	
	public static boolean isDigital(String target){
		Pattern pattern = Pattern.compile(digitalReg);
	    Matcher matcher = pattern.matcher(target);
	    return matcher.matches();
	}
}
