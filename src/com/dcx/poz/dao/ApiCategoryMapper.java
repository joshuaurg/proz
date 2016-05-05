package com.dcx.poz.dao;

import java.util.List;

import com.dcx.poz.model.ApiCategory;

public interface ApiCategoryMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ApiCategory record);

    int insertSelective(ApiCategory record);

    ApiCategory selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ApiCategory record);

    int updateByPrimaryKey(ApiCategory record);

	List<ApiCategory> getApiCategoryList();
}