package com.dcx.poz.controller;

import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import com.dcx.poz.model.AlbumGroup;
import com.dcx.poz.model.AlbumPhoto;
import com.dcx.poz.model.Pager;
import com.dcx.poz.service.AlbumService;
import com.dcx.poz.util.ConstantUtil;
import com.dcx.poz.util.DateUtil;
import com.dcx.poz.util.QiniuUtil;
import com.dcx.poz.util.redis.RedisClientTemplate;
import com.drew.imaging.ImageMetadataReader;
import com.drew.imaging.jpeg.JpegMetadataReader;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.Tag;
import com.drew.metadata.exif.ExifIFD0Directory;
import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.UploadManager;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;


 
@Controller
@RequestMapping(value="/album",produces = {"application/json;charset=UTF-8"})
public class AlbumController {

	@Autowired
	private RedisClientTemplate redis;
	@Autowired
	private AlbumService albumService;
	
	@RequestMapping("/back/album/group/list")
	public String albumList(HttpServletRequest request){
		String pageNumStr = request.getParameter("pageNum"); 
		String pageSizeStr = request.getParameter("pageSize");
		int pageNum = ConstantUtil.DEFAULT_PAGE_NUM; //显示第几页数据
		int pageSize = ConstantUtil.DEFAULT_PAGE_SIZE;  // 每页显示多少条记录
		if(pageNumStr!=null && !"".equals(pageNumStr.trim())){
			pageNum = Integer.parseInt(pageNumStr);
		}
		if(pageSizeStr!=null && !"".equals(pageSizeStr.trim())){
			pageSize = Integer.parseInt(pageSizeStr);
		}
		AlbumGroup param = new AlbumGroup();
		//调用service 获取查询结果
		Pager<AlbumGroup> result = albumService.getAlbumGroupPager(param,pageNum, pageSize);
		// 返回结果到页面
		request.setAttribute("result", result); 
		return "/back/album/group/list";
	}
	@RequestMapping("/back/album/group/addview")
	public String addAlbumGroupView(HttpServletRequest request){
		 
		return "/back/album/group/add";
	}
	@RequestMapping("/back/album/group/updateview")
	public String updateAlbumGroupView(HttpServletRequest request){
		
		return "/back/album/group/update";
	}
	@RequestMapping("/back/album/group/save")
	public String saveAlbumGroup(HttpServletRequest request){
		String name = request.getParameter("name");
		AlbumGroup albumGroup = new AlbumGroup();
		albumGroup.setDelFlag(0);
		albumGroup.setCreTime(DateUtil.date2Str(new Date(), DateUtil.DATETIME_PATTERN));
		albumGroup.setName(name);
		albumService.saveAlbumGroup(albumGroup);
		return "redirect:/album/back/album/group/list";
	}
	
	@RequestMapping("/back/album/photo/list")
	public String photoList(HttpServletRequest request){
		String groupid = request.getParameter("groupid");
		List<AlbumPhoto> photoList = albumService.getPhotoListByGroupId(Integer.parseInt(groupid));
		request.setAttribute("photoList", photoList);
		request.setAttribute("groupid", groupid);
		return "/back/album/photo/list";
	}

    @RequestMapping(value = "/back/album/photo/upload",method = RequestMethod.POST)
    public String uploadPhoto(MultipartHttpServletRequest request) {
    	String groupId = request.getParameter("groupId");
        try {
            Iterator<String> itr = request.getFileNames();
            while (itr.hasNext()) {
                String uploadedFile = itr.next();
                MultipartFile file = request.getFile(uploadedFile);
                String mimeType = file.getContentType();
                byte[] bytes = file.getBytes();
                Metadata metadata = ImageMetadataReader.readMetadata(file.getInputStream());
                Directory exif = metadata.getFirstDirectoryOfType(ExifIFD0Directory.class);
                Collection<Tag> tags =  exif.getTags();
                Iterator<Tag> iter =  tags.iterator();
                
                AlbumPhoto albumPhoto = new AlbumPhoto();
                //逐个遍历每个Tag
                while(iter.hasNext()){
	                 Tag tag = (Tag)iter.next();
	                 String desc = tag.getDescription(); //标签信息
	                 if (tag.hasTagName() && (tag.getTagName().equals("Date/Time Original") || tag.getTagName().equals("Date/Time"))) {  
	                	 albumPhoto.setTakeTime(desc);
		             }else{
		            	 albumPhoto.setTakeTime(DateUtil.date2Str(new Date(), DateUtil.DATETIME_PATTERN));
		             }
                }
                try {
                	JSONObject resJson = QiniuUtil.qiniuUpload(bytes,".jpg",ConstantUtil.ImagePrefix.ALBUM);
                	albumPhoto.setUrl(resJson.getString("key"));
                	albumPhoto.setDelFlag(0);
                	albumPhoto.setGroupId(Integer.parseInt(groupId));
                	albumService.saveAlbumPhoto(albumPhoto);
                } catch (QiniuException e) {
                        Response r = e.response;
                        // 请求失败时打印的异常的信息
                        System.out.println(r.toString());
                        try {
                            //响应的文本信息
                          System.out.println(r.bodyString());
                        } catch (QiniuException e1) {
                            //ignore
                        }
                    }
            }
        }catch(Exception e){
        	
        }
        return "redirect:/album/back/album/photo/list";
    }
    
    @RequestMapping(value = "/front/album/list",method = RequestMethod.GET)
    @ResponseBody
    public String getAlbumList(HttpServletRequest request) {
    	List<AlbumGroup> albumList = albumService.getAllAlbumList();
    	Gson gson = new Gson();
    	return gson.toJson(albumList);
    }
    @RequestMapping("/front/album/photo/list")
    @ResponseBody
	public String getPhotoList(HttpServletRequest request){
		String groupid = request.getParameter("groupid");
		List<AlbumPhoto> photoList = albumService.getPhotoListByGroupId(Integer.parseInt(groupid));
		if(photoList!=null && photoList.size()>0){
			for(AlbumPhoto albumPhoto : photoList){
				albumPhoto.setUrl(ConstantUtil.QINIU_IMG_PREFIX + albumPhoto.getUrl());
			}
		}
		Gson gson = new Gson();
    	return gson.toJson(photoList);
	}
}
