package com.dcx.poz.controller;

import java.util.Date;
import java.util.List;
import java.util.SortedSet;
import javax.servlet.http.HttpServletRequest;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import com.dcx.poz.model.LordSong;
import com.dcx.poz.model.Poem;
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
	
	@RequestMapping(value = "/lordsong/back/list",method = RequestMethod.GET)
    public String lordsongListView(HttpServletRequest request) throws Exception {
  
    	return "/back/lordsong/lordSongList";
    }
	
	@RequestMapping(value = "/back/lordsong/list",method = RequestMethod.GET)
	@ResponseBody
    public String getLordSongList(HttpServletRequest request) throws Exception {
		String page = request.getParameter("page");
		String rows = request.getParameter("rows");
		String params = request.getParameter("params");
		
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
    	 
    	MultipartFile  songFile = null;
    	MultipartFile  songPicFile = null;
    	String songUrl = null;
    	String songPicUrl = null;
    	Integer playTime = null;
    	if(request instanceof MultipartHttpServletRequest){
			MultipartHttpServletRequest mur = (MultipartHttpServletRequest) request;
			songFile = mur.getFile("song");
			songPicFile = mur.getFile("songPic");
			if(songFile.isEmpty()){
				songFile = null;
			}else{
				byte[] bytes = songFile.getBytes();
				JSONObject resJson =  QiniuUtil.qiniuUploadWithOps(bytes,".mp3",ConstantUtil.ImagePrefix.ALBUM);
				songUrl = resJson.getString("key");
				playTime = Integer.parseInt(MediaUtil.getMediaPlayTime(MediaUtil.multipartFile2File(songFile)).get("length"));
			}
			if(songPicFile.isEmpty()){
				songPicFile = null;
			}else{
				byte[] bytes = songPicFile.getBytes();
				JSONObject resJson =  QiniuUtil.qiniuUpload(bytes,".jpg",ConstantUtil.ImagePrefix.LORD_SONG);
				songPicUrl = resJson.getString("key");
			}
		}
    	LordSong lordsong = new LordSong();
    	lordsong.setTitle(title);
    	lordsong.setAuthor(author);
    	lordsong.setDelFlag(0);
    	lordsong.setCreTime(DateUtil.date2Str(new Date(), DateUtil.DATETIME_PATTERN));
    	lordsong.setSongPicUrl(songPicUrl);
    	lordsong.setSongUrl(songUrl);
    	lordsong.setPlayTime(playTime);
    	beliefService.saveLordSong(lordsong);
    	return "redirect:/belief/lordsong/back/list";
    }
	@RequestMapping(value = "/lordsong/back/add/view",method = RequestMethod.GET)
    public String addLordSongView(HttpServletRequest request) throws Exception {
  
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
	
}
