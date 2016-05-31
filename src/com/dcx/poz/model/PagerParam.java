package com.dcx.poz.model;

import java.io.Serializable;
public class PagerParam<T> implements Serializable {

	private static final long serialVersionUID = -8741766802354222579L;
	
	private T searchModel;
	private int offset;
	private int pageSize;
	public T getSearchModel() {
		return searchModel;
	}
	public void setSearchModel(T searchModel) {
		this.searchModel = searchModel;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getOffset() {
		return offset;
	}
	public void setOffset(int offset) {
		this.offset = offset;
	}
}
