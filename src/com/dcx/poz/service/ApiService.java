package com.dcx.poz.service;

import java.util.List;

import com.dcx.poz.model.Api;
import com.dcx.poz.model.ApiCategory;

/**  
 * 创建时间：2015-1-27 下午5:15:03  
 * @author andy  
 * @version 2.2  
 */

public interface ApiService {

	void saveApiCategory(ApiCategory apiCategory);

	List<ApiCategory> getApiCategoryList();

	void saveApi(Api api);

	List<ApiCategory> getApiListWithCategory();


}
