package com.dcx.poz.service.impl;

import com.dcx.poz.dao.AlbumGroupMapper;
import com.dcx.poz.dao.AlbumPhotoMapper;
import com.dcx.poz.dao.AlbumSelectMapper;
import com.dcx.poz.model.AlbumGroup;
import com.dcx.poz.model.AlbumPhoto;
import com.dcx.poz.model.Pager;
import com.dcx.poz.model.PagerParam;
import com.dcx.poz.service.AlbumService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 
 * @description 
 * @author dcx
 * @date 2016年2月26日 下午10:12:48
 */
@Service
@Transactional
public class AlbumServiceImpl implements AlbumService {

	@Autowired
	AlbumGroupMapper albumGroupMapper;
	@Autowired
	AlbumPhotoMapper albumPhotoMapper;
	@Autowired
	AlbumSelectMapper albumSelectMapper;
	
	@Override
	public Pager<AlbumGroup> getAlbumGroupPager(AlbumGroup param, int pageNum, int pageSize) {
		PagerParam<AlbumGroup> pagerParam = new PagerParam<AlbumGroup>();
		pagerParam.setSearchModel(param);
		pagerParam.setOffset(pageSize * (pageNum -1));
		pagerParam.setPageSize(pageSize);
		List<AlbumGroup> data = albumGroupMapper.getAlbumGroupPager(pagerParam);
		Integer totalRecord = albumGroupMapper.getAlbumGroupCount(param);
		//获取总页数
		int totalPage = totalRecord / pageSize;
		if(totalRecord % pageSize !=0){
			totalPage++;
		}
		// 组装pager对象
		Pager<AlbumGroup> result = new Pager<AlbumGroup>(pageSize, pageNum,totalRecord, totalPage, data);
		return result;
	}
	@Override
	public void saveAlbumGroup(AlbumGroup albumGroup) {
		albumGroupMapper.insertSelective(albumGroup);		
	}
	@Override
	public List<AlbumPhoto> getPhotoListByGroupId(int groupId) {
		return albumPhotoMapper.getPhotoListByGroupId(groupId);
	}
	@Override
	public void saveAlbumPhoto(AlbumPhoto albumPhoto) {
		albumPhotoMapper.insertSelective(albumPhoto);		
	}
	
	
}
