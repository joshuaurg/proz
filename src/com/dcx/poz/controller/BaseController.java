package com.dcx.poz.controller;

import java.util.HashMap;
import java.util.Map;
import com.dcx.poz.util.ErrorCodeUtil;
import com.dcx.poz.util.JsonUtil;

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

}
