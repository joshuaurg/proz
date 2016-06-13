package com.dcx.poz.controller;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.dcx.poz.util.StringUtil;
import com.dcx.poz.util.redis.RedisClientTemplate;

/**
 * 
 * @description 秒杀控制器
 * @author DCX
 * @date 2016年6月13日 上午10:18:01
 */
@Controller
@RequestMapping(value="/sk",produces = {"application/json;charset=UTF-8"})
public class SecondKillController extends BaseController {

	@Autowired
	private RedisClientTemplate redis;
	
	/**
	 * 
	 * @description 往redis中添加秒杀请求
	 * @author DCX
	 * @date 2016年6月13日 上午10:19:12
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/add",method = RequestMethod.GET)
    @ResponseBody
    public String addSecondSkillRequest(HttpServletRequest request) {
		/**
		 * 把uid和商品id存入redis
		 * 	uid:用System.currentTimeMillis()代替
		 *  商品id：用
		 */
		redis.rpush("secondKillReq", System.currentTimeMillis()+"_"+StringUtil.getRandStr(3, true));
		return success();
    }
	
}
