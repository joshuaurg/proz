package com.dcx.poz.dao;

import java.util.List;

import com.dcx.poz.model.Api;
import com.dcx.poz.model.ApiCategory;

public interface ApiMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Api record);

    int insertSelective(Api record);

    Api selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Api record);

    int updateByPrimaryKeyWithBLOBs(Api record);

    int updateByPrimaryKey(Api record);

	List<ApiCategory> getApiListWithCategory();
}