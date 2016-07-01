package com.dcx.poz.controller;

import java.util.Date;
import java.util.List;
import java.util.SortedSet;
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

import com.dcx.poz.dao.AlbumGroupMapper;
import com.dcx.poz.model.AlbumGroup;
import com.dcx.poz.model.LordSong;
import com.dcx.poz.model.Poem;
import com.dcx.poz.service.AlbumService;
import com.dcx.poz.service.BeliefService;
import com.dcx.poz.util.ConstantUtil;
import com.dcx.poz.util.DateUtil;
import com.dcx.poz.util.JsonUtil;
import com.dcx.poz.util.MediaUtil;
import com.dcx.poz.util.PageEntity;
import com.dcx.poz.util.PageResult;
import com.dcx.poz.util.QiniuUtil;
import com.dcx.poz.util.StringUtil;
import com.dcx.poz.util.redis.RedisClientTemplate;
import com.google.gson.Gson;

@Controller
@RequestMapping(value="/belief",produces = {"application/json;charset=UTF-8"})
public class BeliefController extends BaseController{

	@Autowired
	private RedisClientTemplate redis;
	@Autowired
	private BeliefService beliefService;
	@Autowired
	private AlbumService albumServie;
	
	
	@RequestMapping(value = "/lordsong/back/album/list/view",method = RequestMethod.GET)
    public String lordsongAlbumGroupListView(HttpServletRequest request) throws Exception {
  
    	return "/back/lordsong/lordSongAlbumGroupList";
    }
	
	/**
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/lordsong/back/album/list",method = RequestMethod.GET)
	@ResponseBody
    public String getLordSongAlbumList(HttpServletRequest request) throws Exception {
		String page = request.getParameter("page");
		String rows = request.getParameter("rows");
		
		AlbumGroup albumGroup = new AlbumGroup();
		albumGroup.setType(2);
		PageEntity<AlbumGroup> pageEntity = new PageEntity<AlbumGroup>();
		pageEntity.setRows(Integer.parseInt(rows));
		pageEntity.setPage(Integer.parseInt(page));
		pageEntity.setOffset();
		pageEntity.setParams(albumGroup);
		
		PageResult<AlbumGroup> pageResult = beliefService.getLordSongAlbumList(pageEntity);
		Gson gson = new Gson();
		return gson.toJson(pageResult); 
    }
	
	@RequestMapping(value = "/lordsong/back/album/add/view",method = RequestMethod.GET)
    public String addLordsongAlbumGroupView(HttpServletRequest request) throws Exception {
  
    	return "/back/lordsong/addLordsongAlbumGroup";
    }
	
	@RequestMapping(value = "/lordsong/back/album/edit/view",method = RequestMethod.GET)
    public String editLordsongAlbumGroupView(HttpServletRequest request) throws Exception {
  
    	return "/back/lordsong/editLordsongAlbumGroup";
    }
	
	@RequestMapping(value = "/lordsong/back/album/add",method = RequestMethod.POST)
    public String addLordsongAlbumGroup(HttpServletRequest request) throws Exception {
		String name = request.getParameter("name");
		String type  = request.getParameter("type");
		
		AlbumGroup albumGroup = new AlbumGroup();
		if(!StringUtil.isEmpty(type)){
			String id  = request.getParameter("id");
			albumGroup.setId(Integer.parseInt(id));
			if(!StringUtil.isEmpty(name)){
				albumGroup.setName(name);
			}
			String path = this.qiniuUpload(request, "profile", ConstantUtil.MediaType.JPG, ConstantUtil.MediaPrefix.LORD_SONG, 1);
			if(!StringUtil.isEmpty(path)){
				albumGroup.setProfile(path);
			}
			albumServie.updateAlbumGroup(albumGroup);
		}else{
			albumGroup.setCreTime(DateUtil.date2Str(new Date(), DateUtil.DATETIME_PATTERN));
			albumGroup.setDelFlag(0);
			albumGroup.setName(name);
			String path = this.qiniuUpload(request, "profile", ConstantUtil.MediaType.JPG, ConstantUtil.MediaPrefix.LORD_SONG, 1);
			if(!StringUtil.isEmpty(path)){
				albumGroup.setProfile(path);
			}
			albumGroup.setType(2);
			albumServie.saveAlbumGroup(albumGroup);
		}
    	return "redirect:/belief/lordsong/back/album/list/view";
    }
	
	@RequestMapping(value = "/lordsong/back/list",method = RequestMethod.GET)
    public String lordsongListView(HttpServletRequest request) throws Exception {
  
    	return "/back/lordsong/lordSongList";
    }
	
	@RequestMapping(value = "/back/lordsong/list",method = RequestMethod.GET)
	@ResponseBody
    public String getLordSongList(HttpServletRequest request) throws Exception {
		String page = request.getParameter("page");
		String rows = request.getParameter("rows");
		
		LordSong lordSong = new LordSong();
		PageEntity<LordSong> pageEntity = new PageEntity<LordSong>();
		pageEntity.setRows(Integer.parseInt(rows));
		pageEntity.setPage(Integer.parseInt(page));
		pageEntity.setOffset();
		pageEntity.setParams(lordSong);
		
		PageResult<LordSong> pageResult = beliefService.getLordSongList(pageEntity);
		Gson gson = new Gson();
		return gson.toJson(pageResult); 
    }
	
	@RequestMapping(value = "/lordsong/back/add",method = RequestMethod.POST)
    public String addLordSong(HttpServletRequest request) throws Exception {
    	String title = request.getParameter("title"); 
    	String author = request.getParameter("author");
    	String albumId = request.getParameter("albumId");
    	 
    	Integer playTime = null;
    	LordSong lordsong = new LordSong();
    	lordsong.setAlbumId(Integer.parseInt(albumId));
    	lordsong.setTitle(title);
    	lordsong.setAuthor(author);
    	lordsong.setDelFlag(0);
    	lordsong.setCreTime(DateUtil.date2Str(new Date(), DateUtil.DATETIME_PATTERN));
    	String songUrl = this.qiniuUpload(request, "song", ConstantUtil.MediaType.MP3, ConstantUtil.MediaPrefix.LORD_SONG, 2);
		if(!StringUtil.isEmpty(songUrl)){
			lordsong.setSongUrl(songUrl);
		}
		String songPicUrl = this.qiniuUpload(request, "songPic", ConstantUtil.MediaType.JPG, ConstantUtil.MediaPrefix.LORD_SONG, 1);
		if(!StringUtil.isEmpty(songPicUrl)){
			lordsong.setSongPicUrl(songPicUrl);
		}
    	lordsong.setPlayTime(playTime);
    	beliefService.saveLordSong(lordsong);
    	return "redirect:/belief/lordsong/back/list";
    }
	@RequestMapping(value = "/lordsong/back/add/view",method = RequestMethod.GET)
    public String addLordSongView(HttpServletRequest request) throws Exception {
		AlbumGroup albumGroup = new AlbumGroup();
		albumGroup.setType(2);
		List<AlbumGroup> albums = albumServie.getAllLordSongAlbumGroup(albumGroup);
		request.setAttribute("albums", albums);
    	return "/back/lordsong/addLordSong";
    }
	
	@RequestMapping(value = "/front/lordsong/list",method = RequestMethod.GET)
	@ResponseBody
    public String getLordSongPage(HttpServletRequest request) throws Exception {
		List<LordSong> list = beliefService.getLordSongPage();
		if(!StringUtil.isEmpty(list)){
			for(LordSong lordSong : list){
				if(!StringUtil.isEmpty(lordSong.getSongPicUrl())){
					lordSong.setSongPicUrl(ConstantUtil.QINIU_IMG_PREFIX + lordSong.getSongPicUrl());
				}
				if(!StringUtil.isEmpty(lordSong.getSongUrl())){
					lordSong.setSongUrl(ConstantUtil.QINIU_AUDIO_PREFIX + lordSong.getSongUrl());
				}
				lordSong.setCreTime(DateUtil.date2Str(lordSong.getCreTime(), DateUtil.DATETIME_PATTERN));
			}
		}
    	return JsonUtil.object2String(list);
    }
	
	@RequestMapping(value = "/front/lordsong/album/list",method = RequestMethod.GET)
	@ResponseBody
    public String getLordSongAlbumPage(HttpServletRequest request) throws Exception {
		AlbumGroup albumGroup = new AlbumGroup();
		albumGroup.setType(ConstantUtil.AlbumType.LORDSONG);
		List<AlbumGroup> list = albumServie.getAllLordSongAlbumGroup(albumGroup);
		if(!StringUtil.isEmpty(list)){
			for(AlbumGroup group : list){
				group.setProfile(ConstantUtil.QINIU_IMG_PREFIX + group.getProfile());
			}
		}
    	return JsonUtil.object2String(list);
    }
	
	@RequestMapping(value = "/front/lordsong/album/{albumGroupId}",method = RequestMethod.GET)
	@ResponseBody
    public String getLordSongListByAlbumId(HttpServletRequest request,@PathVariable("albumGroupId") String albumGroupId) throws Exception {
		List<LordSong> lordSongList = beliefService.getLordSongListByAlbumId(Integer.parseInt(albumGroupId));
		if(!StringUtil.isEmpty(lordSongList)){
			for(LordSong lordSong : lordSongList){
				lordSong.setSongUrl(ConstantUtil.QINIU_AUDIO_PREFIX + lordSong.getSongUrl());
				lordSong.setSongPicUrl(ConstantUtil.QINIU_IMG_PREFIX + lordSong.getSongPicUrl());
			}
		}
    	return JsonUtil.object2String(lordSongList);
    }
}
