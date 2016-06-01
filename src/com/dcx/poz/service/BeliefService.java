package com.dcx.poz.service;

import java.util.List;

import com.dcx.poz.model.AlbumGroup;
import com.dcx.poz.model.AlbumPhoto;
import com.dcx.poz.model.LordSong;
import com.dcx.poz.model.Pager;
import com.dcx.poz.util.PageEntity;
import com.dcx.poz.util.PageResult;

public interface BeliefService {

	void saveLordSong(LordSong lordsong);

	PageResult<LordSong> getLordSongList(PageEntity<LordSong> pageEntity);

	List<LordSong> getLordSongPage();

	 
 

}
