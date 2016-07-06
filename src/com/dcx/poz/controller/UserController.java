package com.dcx.poz.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

/**  
 * 创建时间：2015-1-28 下午1:17:27  
 * @author andy  
 * @version 2.2  
 */
@Controller
@RequestMapping(value="/u",produces = {"application/json;charset=UTF-8"})
public class UserController {

	@RequestMapping(value = "/login",method = RequestMethod.POST)
	@ResponseBody
	public String login(HttpServletRequest req,HttpServletResponse res){
		String username = req.getParameter("username");
		
		System.out.println("登录中。。。。");
		Gson gson = new Gson();
		
		Map<String, String> ret = new HashMap<String, String>();
		ret.put("ret", "success");
		return gson.toJson(ret);
	}
}


