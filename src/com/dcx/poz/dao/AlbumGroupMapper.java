package com.dcx.poz.dao;

import java.util.List;
import com.dcx.poz.model.AlbumGroup;
import com.dcx.poz.model.PagerParam;
import com.dcx.poz.util.PageEntity;

public interface AlbumGroupMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AlbumGroup record);

    int insertSelective(AlbumGroup record);

    AlbumGroup selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AlbumGroup record);

    int updateByPrimaryKey(AlbumGroup record);

	List<AlbumGroup> getAlbumGroupPager(PagerParam<AlbumGroup> pagerParam);

	Integer getAlbumGroupCount(AlbumGroup param);
 
	List<AlbumGroup> getAlbumGroupList(PageEntity<AlbumGroup> pageEntity);

	Integer getAlbumGroupCount(PageEntity<AlbumGroup> pageEntity);

	List<AlbumGroup> getAlbumGroups(AlbumGroup albumGroup);
}