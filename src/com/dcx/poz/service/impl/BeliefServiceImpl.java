package com.dcx.poz.service.impl;

import com.dcx.poz.dao.AlbumGroupMapper;
import com.dcx.poz.dao.AlbumPhotoMapper;
import com.dcx.poz.dao.AlbumSelectMapper;
import com.dcx.poz.dao.LordSongMapper;
import com.dcx.poz.model.AlbumGroup;
import com.dcx.poz.model.AlbumPhoto;
import com.dcx.poz.model.LordSong;
import com.dcx.poz.model.Pager;
import com.dcx.poz.model.PagerParam;
import com.dcx.poz.model.Poem;
import com.dcx.poz.service.AlbumService;
import com.dcx.poz.service.BeliefService;
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
public class BeliefServiceImpl implements BeliefService {

	@Autowired
	LordSongMapper lordSongMapper;
	
	@Override
	public void saveLordSong(LordSong lordsong) {
		lordSongMapper.insertSelective(lordsong);
		
	}

	@Override
	public PageResult<LordSong> getLordSongList(PageEntity<LordSong> pageEntity) {
		PageResult<LordSong> pageResult=new PageResult<LordSong>();
		List<LordSong> rows = lordSongMapper.getLordSongList(pageEntity);
		 
		pageResult.setRows(rows);
		pageResult.setRecords(lordSongMapper.getLordSongCount(pageEntity));
		pageResult.setPage(pageEntity.getPage());
		pageResult.setTotal(PageUtil.calcPagNum(pageResult.getRecords(), pageEntity.getRows()));
		return pageResult;
	}

	@Override
	public List<LordSong> getLordSongPage() {
		return lordSongMapper.getLordSongPage();
	}


}
