package com.dcx.poz.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

/**  
 * @TODO     错误代码配置文件加载类
 */
public class ErrorCodeProperties {
	private static ErrorCodeProperties env;
	private Properties properties = new Properties();

	private ErrorCodeProperties() {
		try {
			properties.load(new InputStreamReader(this.getClass().getClassLoader().getResourceAsStream("/errorCode.properties"), "UTF-8")); 
		} catch (FileNotFoundException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}
	};
	// 线程同步获得唯一实例
	public static synchronized ErrorCodeProperties getInstance() {
		if (null == env) {
			return new ErrorCodeProperties();
		} else {
			return env;
		}
	}
	public ErrorCodeProperties clone() {
		return getInstance();
	}
	public String getProperties(String key) {
		return properties.getProperty(key);
	}
}
