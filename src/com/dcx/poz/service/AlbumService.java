package com.dcx.poz.service;

import java.util.List;

import com.dcx.poz.model.AlbumGroup;
import com.dcx.poz.model.AlbumPhoto;
import com.dcx.poz.model.Pager;

public interface AlbumService {

	Pager<AlbumGroup> getAlbumGroupPager(AlbumGroup param, int pageNum, int pageSize);

	void saveAlbumGroup(AlbumGroup albumGroup);

	List<AlbumPhoto> getPhotoListByGroupId(int parseInt);
 

}
