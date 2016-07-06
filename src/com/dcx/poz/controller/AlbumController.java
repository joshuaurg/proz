package com.dcx.poz.controller;

import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import com.dcx.poz.model.AlbumGroup;
import com.dcx.poz.model.AlbumPhoto;
import com.dcx.poz.service.AlbumService;
import com.dcx.poz.util.ConstantUtil;
import com.dcx.poz.util.DateUtil;
import com.dcx.poz.util.PageEntity;
import com.dcx.poz.util.PageResult;
import com.dcx.poz.util.QiniuUtil;
import com.dcx.poz.util.StringUtil;
import com.dcx.poz.util.redis.RedisClientTemplate;
import com.drew.imaging.ImageMetadataReader;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.Tag;
import com.drew.metadata.exif.ExifIFD0Directory;
import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
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
public class AlbumController extends BaseController{

	@Autowired
	private RedisClientTemplate redis;
	@Autowired
	private AlbumService albumService;
	
	@RequestMapping("/back/album/group/list/view")
	public String albumListView(HttpServletRequest request){

		return "/back/album/group/list";
	}
	@RequestMapping("/back/album/group/list")
	@ResponseBody
	public String getAlbumGroupList(HttpServletRequest request){
		String page = request.getParameter("page");
		String rows = request.getParameter("rows");
		
		AlbumGroup albumGroup = new AlbumGroup();
		albumGroup.setType(1);
		PageEntity<AlbumGroup> pageEntity = new PageEntity<AlbumGroup>();
		pageEntity.setRows(Integer.parseInt(rows));
		pageEntity.setPage(Integer.parseInt(page));
		pageEntity.setOffset();
		pageEntity.setParams(albumGroup);
		
		PageResult<AlbumGroup> pageResult = albumService.getAlbumGroupList(pageEntity);
		Gson gson = new Gson();
		return gson.toJson(pageResult); 
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
		albumGroup.setType(ConstantUtil.AlbumType.PHOTO);
		String path = this.qiniuUpload(request, "profile", ConstantUtil.MediaType.JPG, ConstantUtil.MediaPrefix.ALBUM, 1);
		if(!StringUtil.isEmpty(path)){
			albumGroup.setProfile(path);
		}
		albumService.saveAlbumGroup(albumGroup);
		return "redirect:/album/back/album/group/list/view";
	}
	
	@RequestMapping("/back/album/photo/list")
	public String photoList(HttpServletRequest request){
		String groupid = request.getParameter("groupid");
		List<AlbumPhoto> photoList = albumService.getPhotoListByGroupId(Integer.parseInt(groupid));
		request.setAttribute("photoList", photoList);
		request.setAttribute("groupid", groupid);
		return "/back/album/photo/list";
	}

	@RequestMapping(value = "/back/album/photo/upload/view",method = RequestMethod.GET)
	public String uploadPhotoView(HttpServletRequest request){
		String groupid = request.getParameter("groupid");
		List<AlbumPhoto> photoList = albumService.getPhotoListByGroupId(Integer.parseInt(groupid));
		if(!StringUtil.isEmpty(photoList)){
			for(AlbumPhoto photo : photoList){
				photo.setUrl(ConstantUtil.QINIU_IMG_PREFIX + photo.getUrl());
			}
		}
		request.setAttribute("photoList", photoList);
		request.setAttribute("groupid", groupid);
		return "/back/album/photo/upload";
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
                	JSONObject resJson = QiniuUtil.qiniuUpload(bytes,".jpg",ConstantUtil.MediaPrefix.ALBUM);
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
        return "redirect:/album/back/album/photo/list?groupid="+groupId;
    }
    
    @RequestMapping(value = "/front/album/photo/group/list",method = RequestMethod.GET)
    @ResponseBody
    public String getAlbumList(HttpServletRequest request) {
    	AlbumGroup albumGroup = new AlbumGroup();
		albumGroup.setType(ConstantUtil.AlbumType.PHOTO);
    	List<AlbumGroup> albumList = albumService.getAlbumGroups(albumGroup);
    	if(!StringUtil.isEmpty(albumList)){
    		for (AlbumGroup group : albumList) {
    			group.setProfile(ConstantUtil.QINIU_IMG_PREFIX + group.getProfile());
			}
    	}
    	Gson gson = new Gson();
    	return gson.toJson(albumList);
    }
    @RequestMapping(value="/front/album/photo/list",method = RequestMethod.POST)
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
