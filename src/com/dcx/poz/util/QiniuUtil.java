package com.dcx.poz.util;

import org.json.JSONObject;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;

public class QiniuUtil {
	//密钥配置
	private static Auth auth = Auth.create(ResourceLoader.paramsConfig.getProperty("qiniu_ak"), ResourceLoader.paramsConfig.getProperty("qiniu_sk"));
	
	//简单上传，使用默认策略，只需要设置上传的空间名就可以了
	public static String getUpToken(){
		return auth.uploadToken(ResourceLoader.paramsConfig.getProperty("qiniu_bucketname"));
	}
	
	/**
	 * @description 
	 * @author DCX
	 * @date 2016年5月17日 下午1:33:25
	 * @param bytes
	 * @return
	 * @throws QiniuException
	 */
	public static JSONObject qiniuUpload(byte[] bytes,String mediaType,String prefix) throws QiniuException {
		//创建上传对象
		UploadManager uploadManager = new UploadManager();
		//调用put方法上传，这里指定的key和上传策略中的key要一致
		Response res = uploadManager.put(bytes, prefix+"-"+System.currentTimeMillis() + mediaType, QiniuUtil.getUpToken());
		JSONObject resJson = new JSONObject(res.bodyString());
		return resJson;
	}
}
