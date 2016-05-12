package com.dcx.poz.dao;

import java.util.List;

import com.dcx.poz.model.AlbumPhoto;

public interface AlbumPhotoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AlbumPhoto record);

    int insertSelective(AlbumPhoto record);

    AlbumPhoto selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AlbumPhoto record);

    int updateByPrimaryKey(AlbumPhoto record);

	List<AlbumPhoto> getPhotoListByGroupId(int groupId);
}