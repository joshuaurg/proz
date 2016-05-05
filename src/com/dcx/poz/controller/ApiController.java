package com.dcx.poz.controller;

import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

import com.dcx.poz.model.Api;
import com.dcx.poz.model.ApiCategory;
import com.dcx.poz.service.ApiService;
import com.dcx.poz.util.redis.RedisClientTemplate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

/**  
 * 创建时间：2015-1-28 下午1:17:27  
 * @author andy  
 * @version 2.2  
 */
@Controller
@RequestMapping(value="/api",produces = {"application/json;charset=UTF-8"})
public class ApiController {

	@Autowired
	private ApiService apiService;
	@Autowired
	private RedisClientTemplate redis;
	
	
	@RequestMapping("/list")
	public String getApiList(HttpServletRequest request){		
		List<ApiCategory> apiCategoryList = apiService.getApiListWithCategory();
		request.setAttribute("apiCategoryList", apiCategoryList);
		return "/back/api/list";
	}
	
	@RequestMapping("/add")
	public String toAddApi(HttpServletRequest request){
		List<ApiCategory> cateList = apiService.getApiCategoryList();
		request.setAttribute("cateList", cateList);
		return "/back/api/add";
	}
	
	@RequestMapping("/save")
	public String saveApi(HttpServletRequest req,@ModelAttribute("api") Api api){
		api.setDelFlag(0);
		apiService.saveApi(api);
		return "redirect:/api/list";
	}
	
	@RequestMapping("/update")
	public String toUpdateApi(HttpServletRequest request){
		
		return "/back/api/update";
	}
	
	@RequestMapping("/category/list")
	public String getApiCategoryList(HttpServletRequest request){
		List<ApiCategory> cateList = apiService.getApiCategoryList();
		request.setAttribute("cateList", cateList);
		return "/back/api/cateList";
	}
	
	@RequestMapping("/category/add")
	public String addApiCategory(HttpServletRequest request){
		
		return "/back/api/addApiCategory";
	}
	
	@RequestMapping("/category/save")
	public String saveApiCategory(HttpServletRequest request){
		String name = request.getParameter("name");
		String desc = request.getParameter("desc");
		ApiCategory apiCategory = new ApiCategory();
		apiCategory.setName(name);
		apiCategory.setDescription(desc);
		apiCategory.setCreTime(new Date());
		apiCategory.setDelFlag(0);
		apiService.saveApiCategory(apiCategory);
		return "redirect:/api/category/list";
	}
}
