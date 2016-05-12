package com.dcx.poz.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ResourceLoader {
	
	public static Properties paramsConfig = new Properties();
	
	static{
		try {
			paramsConfig.load(ResourceLoader.class.getClassLoader().getResourceAsStream("/systemConfig.properties"));
			
		} catch (FileNotFoundException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}
	}
}
