package com.dcx.poz.service.impl;

import com.dcx.poz.dao.ApiCategoryMapper;
import com.dcx.poz.dao.ApiMapper;
import com.dcx.poz.model.Api;
import com.dcx.poz.model.ApiCategory;
import com.dcx.poz.service.ApiService;

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
public class ApiServiceImpl implements ApiService {

	@Autowired
	ApiCategoryMapper apiCategoryMapper;
	
	@Autowired
	ApiMapper apiMapper;
	
	@Override
	public void saveApiCategory(ApiCategory apiCategory) {
		apiCategoryMapper.insertSelective(apiCategory);
	}

	@Override
	public List<ApiCategory> getApiCategoryList() {
		return apiCategoryMapper.getApiCategoryList();
	}

	@Override
	public void saveApi(Api api) {
		apiMapper.insertSelective(api);
	}

	@Override
	public List<ApiCategory> getApiListWithCategory() {
		return apiMapper.getApiListWithCategory();
	}
}
