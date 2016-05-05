package com.dcx.poz.controller;

import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.dcx.poz.util.ConstantUtil;
import com.dcx.poz.util.JsonUtil;
import com.dcx.poz.util.memcached.MemSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**  
 * 创建时间：2015-1-28 下午1:17:27  
 * @author andy  
 * @version 2.2  
 */
@Controller
@RequestMapping(value="/u",produces = {"application/json;charset=UTF-8"})
public class UserController {

	
	
	@RequestMapping("/test")
	public String showUserInfo(){
		MemSession msession = MemSession.getSession("name", true, "default");
		return "/back/mind/mindList";
	}
}


