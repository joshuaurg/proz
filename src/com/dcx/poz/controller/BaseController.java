package com.dcx.poz.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONObject;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.dcx.poz.util.ConstantUtil;
import com.dcx.poz.util.ErrorCodeUtil;
import com.dcx.poz.util.JsonUtil;
import com.dcx.poz.util.MediaUtil;
import com.dcx.poz.util.QiniuUtil;

/**
 * 
 * @author DiWu
 *
 */
public class BaseController
{
	
	/**
	 * 返回成功结果
	 * @param result
	 * @return
	 */
	protected String success(Map<String, Object> result)
	{
		result.put("status", "suc");
		return JsonUtil.object2String(result);
	}

	/**
	 * 返回成功结果
	 * @return
	 */
	protected String success()
	{
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("ret", "succ");
		return JsonUtil.object2String(result);
	}

	/**
	 * 返回错误结果
	 * 
	 * @param errorCode 错误码
	 * @return
	 */
	protected String error(String errorCode)
	{
		Map<String, Object> result = new HashMap<String, Object>();
		return ErrorCodeUtil.errorMsg(result, errorCode);
	}
	
	/**
	 * 上传文件到七牛
	 * @param request
	 * @param fileName
	 * @param extension
	 * @param imagePrefix
	 * @param mediaType : 1图片 2音频 3视频
	 * @return
	 */
	protected String qiniuUpload(HttpServletRequest request,String fileName,String extension,String prefix,Integer mediaType){
		MultipartFile  multiFile = null;
    	String url = null;
    	try {
    		if(request instanceof MultipartHttpServletRequest){
    			MultipartHttpServletRequest mur = (MultipartHttpServletRequest) request;
    			multiFile = mur.getFile(fileName);
    			if(!multiFile.isEmpty()){
    				byte[] bytes = multiFile.getBytes();
    				JSONObject resJson = QiniuUtil.qiniuUploadWithOps(bytes,extension,mediaType, prefix);
    				url = resJson.getString("key");
    			}
    		}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return url;
	}

}
