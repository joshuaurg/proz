package com.dcx.poz.dao;

import com.dcx.poz.model.AlbumSelect;

public interface AlbumSelectMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AlbumSelect record);

    int insertSelective(AlbumSelect record);

    AlbumSelect selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AlbumSelect record);

    int updateByPrimaryKey(AlbumSelect record);
}