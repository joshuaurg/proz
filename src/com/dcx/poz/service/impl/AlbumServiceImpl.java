package com.dcx.poz.service.impl;

import com.dcx.poz.dao.AlbumGroupMapper;
import com.dcx.poz.dao.AlbumPhotoMapper;
import com.dcx.poz.dao.AlbumSelectMapper;
import com.dcx.poz.model.AlbumGroup;
import com.dcx.poz.model.AlbumPhoto;
import com.dcx.poz.model.Pager;
import com.dcx.poz.model.PagerParam;
import com.dcx.poz.service.AlbumService;
import com.dcx.poz.util.ConstantUtil;
import com.dcx.poz.util.PageEntity;
import com.dcx.poz.util.PageResult;
import com.dcx.poz.util.PageUtil;
import com.dcx.poz.util.StringUtil;

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
	@Override
	public void updateAlbumGroup(AlbumGroup albumGroup) {
		albumGroupMapper.updateByPrimaryKeySelective(albumGroup);
	}
	@Override
	public List<AlbumGroup> getAllLordSongAlbumGroup(AlbumGroup albumGroup) {
		return albumGroupMapper.getAlbumGroups(albumGroup);
	}
	@Override
	public PageResult<AlbumGroup> getAlbumGroupList(PageEntity<AlbumGroup> pageEntity) {
		PageResult<AlbumGroup> pageResult=new PageResult<AlbumGroup>();
		List<AlbumGroup> rows = albumGroupMapper.getAlbumGroupList(pageEntity);
		if(!StringUtil.isEmpty(rows)){
			for(AlbumGroup albumGroup : rows){
				albumGroup.setProfile(ConstantUtil.QINIU_IMG_PREFIX + albumGroup.getProfile());
			}
		}
		pageResult.setRows(rows);
		pageResult.setRecords(albumGroupMapper.getAlbumGroupCount(pageEntity));
		pageResult.setPage(pageEntity.getPage());
		pageResult.setTotal(PageUtil.calcPagNum(pageResult.getRecords(), pageEntity.getRows()));
		return pageResult;
	}
	@Override
	public List<AlbumGroup> getAlbumGroups(AlbumGroup albumGroup) {
		return albumGroupMapper.getAlbumGroups(albumGroup);
	}
	
	
}
