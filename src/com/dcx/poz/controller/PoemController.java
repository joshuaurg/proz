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
	
	@RequestMapping(value = "/back/view/list",method = RequestMethod.GET)
    public String poemListView(HttpServletRequest request) {
		return "/back/poem/poemList";
    }
	
    @RequestMapping(value = "/back/list",method = RequestMethod.GET)
    @ResponseBody
    public String getPoemList(HttpServletRequest request) {
		String page = request.getParameter("page");
		String rows = request.getParameter("rows");
		String params = request.getParameter("params");
		
		Poem poem = new Poem();
		PageEntity<Poem> pageEntity = new PageEntity<Poem>();
		pageEntity.setRows(Integer.parseInt(rows));
		pageEntity.setPage(Integer.parseInt(page));
		pageEntity.setOffset();
		pageEntity.setParams(poem);
		
		PageResult<Poem> pageResult = poemService.getPoemList(pageEntity);
		Gson gson = new Gson();
		return gson.toJson(pageResult);
    }
    @RequestMapping(value = "/back/view/add",method = RequestMethod.GET)
    public String addPoemView(HttpServletRequest request) {
     
    	return "/back/poem/addPoem";
    }
    @RequestMapping(value = "/back/add",method = RequestMethod.POST)
    public String addPoem(HttpServletRequest request) throws IOException {
    	String title = request.getParameter("title"); 
    	String author = request.getParameter("author");
    	String content = request.getParameter("content");
    	MultipartFile  image = null;
    	String profileImg = null;
    	if(request instanceof MultipartHttpServletRequest){
			MultipartHttpServletRequest mur = (MultipartHttpServletRequest) request;
			image = mur.getFile("profileImg");
			if(image.isEmpty()){
				image = null;
			}else{
				byte[] bytes = image.getBytes();
				JSONObject resJson =  QiniuUtil.qiniuUpload(bytes,".jpg",ConstantUtil.MediaPrefix.ALBUM);
				profileImg = resJson.getString("key");
			}
		}
    	Poem poem = new Poem();
    	poem.setTitle(title);
    	poem.setAuthor(author);
    	poem.setContent(content);
    	poem.setCreTime(new Date());
    	poem.setDelFlag(0);
    	if(!StringUtil.isEmpty(profileImg)){
    		poem.setProfileImg(profileImg);
    	}
        poemService.savePoem(poem);
    	return "redirect:/poem/back/view/list";
    }
    @RequestMapping(value = "/back/del/{id}",method = RequestMethod.POST)
    @ResponseBody
    public String delPoem(HttpServletRequest request,@PathVariable("id") String id) {
    	poemService.delPoem(id);
    	 
    	return success();
    }
    @RequestMapping(value = "/back/update/view/{id}",method = RequestMethod.GET)
    public String updatePoemView(HttpServletRequest request) {
    	 
    	return "/back/poem/updatePoem";
    }
    @RequestMapping(value = "/back/update/{id}",method = RequestMethod.GET)
    public String updatePoem(HttpServletRequest request,@PathVariable("id") String id) throws IOException {
    	String title = request.getParameter("title"); 
    	String author = request.getParameter("author");
    	String content = request.getParameter("content");
    	MultipartFile  image = null;
    	String profileImg = null;
    	if(request instanceof MultipartHttpServletRequest){
			MultipartHttpServletRequest mur = (MultipartHttpServletRequest) request;
			image = mur.getFile("profileImg");
			if(image.isEmpty()){
				image = null;
			}else{
				byte[] bytes = image.getBytes();
				JSONObject resJson =  QiniuUtil.qiniuUpload(bytes,".jpg",ConstantUtil.MediaPrefix.POEM);
				profileImg = resJson.getString("key");
			}
		}
    	Poem poem = new Poem();
    	poem.setId(Integer.parseInt(id));
    	if(!StringUtil.isEmpty(title)){
    		poem.setTitle(title);
    	}
    	if(!StringUtil.isEmpty(author)){
    		poem.setAuthor(author);
    	}
    	if(!StringUtil.isEmpty(content)){
    		poem.setContent(content);
    	}
    	poem.setDelFlag(0);
    	if(!StringUtil.isEmpty(profileImg)){
    		poem.setProfileImg(profileImg);
    	}
        poemService.updatePoem(poem);
        return "redirect:/poem/back/view/list";
    }
    @RequestMapping(value = "/back/view/{id}",method = RequestMethod.GET)
    public String viewPoem(HttpServletRequest request) {
    	
    	return "/back/poem/poem";
    }
   
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
