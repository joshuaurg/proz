package com.dcx.poz.controller;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.dcx.poz.util.ErrorCodeUtil;
import com.google.gson.Gson;

@Controller
@RequestMapping(value="/auth/check",produces = {"application/json;charset=UTF-8"})
public class ReqHandleResponseController {

	@RequestMapping("/error")
	@ResponseBody
	public String authCheckError(HttpServletRequest request) throws Exception{
		String errorCode = request.getParameter("errorCode");
		Map<String, String> ret = new HashMap<String, String>();
		Gson gson = new Gson();
		
		String errorMsg = ErrorCodeUtil.errorMsg(errorCode);
		JSONObject json = new JSONObject(errorMsg);
		ret.put("status", "error");
		ret.put("msg", json.getString("msg"));
		ret.put("code", json.getString("code"));
		
		return gson.toJson(ret); 
	}
}
