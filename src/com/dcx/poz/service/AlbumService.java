package com.dcx.poz.service;

import java.util.List;
import com.dcx.poz.model.AlbumGroup;
import com.dcx.poz.model.AlbumPhoto;
import com.dcx.poz.util.PageEntity;
import com.dcx.poz.util.PageResult;

public interface AlbumService {

	void saveAlbumGroup(AlbumGroup albumGroup);

	List<AlbumPhoto> getPhotoListByGroupId(int groupId);

	void saveAlbumPhoto(AlbumPhoto albumPhoto);

	void updateAlbumGroup(AlbumGroup albumGroup);

	List<AlbumGroup> getAllLordSongAlbumGroup(AlbumGroup albumGroup);

	PageResult<AlbumGroup> getAlbumGroupList(PageEntity<AlbumGroup> pageEntity);

	List<AlbumGroup> getAlbumGroups(AlbumGroup albumGroup);
}
