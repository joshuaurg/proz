package com.dcx.poz.controller;

import java.io.IOException;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.dcx.poz.model.AlbumPhoto;
import com.dcx.poz.model.Poem;
import com.dcx.poz.service.PoemService;
import com.dcx.poz.util.ConstantUtil;
import com.dcx.poz.util.DateUtil;
import com.dcx.poz.util.JsonUtil;
import com.dcx.poz.util.PageEntity;
import com.dcx.poz.util.PageResult;
import com.dcx.poz.util.Pager;
import com.dcx.poz.util.QiniuUtil;
import com.dcx.poz.util.StringUtil;
import com.dcx.poz.util.redis.RedisClientTemplate;
import com.drew.imaging.ImageMetadataReader;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.Tag;
import com.drew.metadata.exif.ExifIFD0Directory;
import com.google.gson.Gson;

@Controller
@RequestMapping(value="/poem",produces = {"application/json;charset=UTF-8"})
public class PoemController extends BaseController {

	@Autowired
	private RedisClientTemplate redis;
	@Autowired
	private PoemService poemService;
	
   
    @RequestMapping(value = "/front/list",method = RequestMethod.GET)
    @ResponseBody
    public String getPoemPage(HttpServletRequest request) {
      	String offset=request.getParameter("offset");
		String size=request.getParameter("pageSize");
		
		Pager<Integer> pager = new Pager<Integer>();
		pager.setOffset(Integer.parseInt(offset));
		pager.setSize(Integer.parseInt(size));
    	List<Poem> list = poemService.getPoemPage(pager);
    	return JsonUtil.object2String(list);
    }
    
    @RequestMapping(value = "/front/view/{id}",method = RequestMethod.GET)
    @ResponseBody
    public String getPoem(HttpServletRequest request,@PathVariable("id") String id) {
    	Poem poem = poemService.getPoemById(Integer.parseInt(id));
    	if(poem!=null){
    		poem.setProfileImg(ConstantUtil.QINIU_IMG_PREFIX+poem.getProfileImg());
    	}
    	return JsonUtil.object2String(poem);
    }
    
}
