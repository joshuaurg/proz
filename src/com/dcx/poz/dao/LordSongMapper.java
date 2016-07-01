package com.dcx.poz.dao;

import java.util.List;

import com.dcx.poz.model.AlbumGroup;
import com.dcx.poz.model.LordSong;
import com.dcx.poz.util.PageEntity;

public interface LordSongMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(LordSong record);

    int insertSelective(LordSong record);

    LordSong selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(LordSong record);

    int updateByPrimaryKey(LordSong record);

	List<LordSong> getLordSongList(PageEntity<LordSong> pageEntity);

	Integer getLordSongCount(PageEntity<LordSong> pageEntity);

	List<LordSong> getLordSongPage();

	List<AlbumGroup> getLordSongAlbumList(PageEntity<AlbumGroup> pageEntity);

	Integer getLordSongAlbumCount(PageEntity<AlbumGroup> pageEntity);

	List<LordSong> getLordSongListByAlbumId(int albumGroupId);
}