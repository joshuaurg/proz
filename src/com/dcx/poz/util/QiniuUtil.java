package com.dcx.poz.util;

import com.qiniu.util.Auth;

public class QiniuUtil {
	//密钥配置
	private static Auth auth = Auth.create(ResourceLoader.paramsConfig.getProperty("qiniu_ak"), ResourceLoader.paramsConfig.getProperty("qiniu_sk"));
	
	//简单上传，使用默认策略，只需要设置上传的空间名就可以了
	public static String getUpToken(){
		return auth.uploadToken(ResourceLoader.paramsConfig.getProperty("qiniu_bucketname"));
	}
}
