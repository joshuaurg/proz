package com.dcx.poz.util;

import org.json.JSONObject;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import com.qiniu.util.StringMap;
import com.qiniu.util.UrlSafeBase64;

public class QiniuUtil {
	//密钥配置
	private static Auth auth = Auth.create(ResourceLoader.paramsConfig.getProperty("qiniu_ak"), ResourceLoader.paramsConfig.getProperty("qiniu_sk"));
	
	//简单上传，使用默认策略，只需要设置上传的空间名就可以了
	public static String getUpToken(String bucketName){
		return auth.uploadToken(bucketName);
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
		Response res = uploadManager.put(bytes, prefix+"-"+System.currentTimeMillis() + mediaType, QiniuUtil.getUpToken(ResourceLoader.paramsConfig.getProperty("qiniu_bucketname-pic")));
		JSONObject resJson = new JSONObject(res.bodyString());
		return resJson;
	}
	
	//上传策略中设置persistentOps字段和persistentPipeline字段
    public static String getUpTokenWithOps(String bucketname,String key){
      String fops = "avthumb/mp3/aq/6/ar/16000";
      String pipeline = "media-convertor";
      
      //可以对转码后的文件进行使用saveas参数自定义命名，当然也可以不指定文件会默认命名并保存在当前空间。
      String urlbase64 = UrlSafeBase64.encodeToString(bucketname+":"+key);
      String pfops = fops + "|saveas/"+ urlbase64;
      return auth.uploadToken(bucketname,key,3600,
    		  new StringMap().putNotEmpty("persistentOps", pfops).putNotEmpty("persistentPipeline", pipeline), true);
    }
	  
	public static JSONObject qiniuUploadWithOps(byte[] bytes,String extension,Integer mediaType,String prefix) throws QiniuException {
		Response res =  null;
		String bucketName = ResourceLoader.paramsConfig.getProperty("qiniu_bucketname-other");
		//创建上传对象
		UploadManager uploadManager = new UploadManager();
		String fileName = prefix+"-"+System.currentTimeMillis() + extension;
		try {
			switch (mediaType) {
				case 1:
					bucketName = ResourceLoader.paramsConfig.getProperty("qiniu_bucketname-pic");
					res = uploadManager.put(bytes, fileName, getUpToken(bucketName));
					break;
				case 2:
					bucketName = ResourceLoader.paramsConfig.getProperty("qiniu_bucketname-audio");
					//调用put方法上传
			        res = uploadManager.put(bytes,fileName,getUpTokenWithOps(bucketName,fileName));
					break;	
				case 3:
					bucketName = ResourceLoader.paramsConfig.getProperty("qiniu_bucketname-video");
					//调用put方法上传
			        res = uploadManager.put(bytes,fileName,getUpTokenWithOps(bucketName,fileName));
					break;
				default:
					break;
			}
	    } catch (QiniuException e) {
	          Response r = e.response;
	          // 请求失败时打印的异常的信息
	          System.out.println(r.toString());
	          try {
	              //响应的文本信息
	            System.out.println(r.bodyString());
	          } catch (QiniuException e1) {
	             return null;
	          }
	      }  
		JSONObject resJson = new JSONObject(res.bodyString());
		return resJson;
	}
	
}
