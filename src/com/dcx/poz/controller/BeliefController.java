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
