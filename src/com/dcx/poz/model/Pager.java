package com.dcx.poz.model;

import java.io.Serializable;
import java.util.List;

public class Pager<T> implements Serializable {

	/**
	 * 一页中数据的条数
	 */
	private Integer pageSize;
	
	/**
	 * 起始数据的索引
	 */
	private Integer offset;
	
	/**
	 * 数据
	 */
	private List<T> data;
	
	/**
	 * 当前第几页
	 */
	private Integer currentPage;
	
	/**
	 * 总页数
	 */
	private Integer totalPageSize;
	
	public Pager() {
		super();
	}

	public Pager(Integer pageSize, Integer currentPage) {
		super();
		this.pageSize = pageSize;
		this.currentPage = currentPage;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getOffset() {
		return offset;
	}

	public void setOffset(Integer offset) {
		this.offset = offset;
	}

	public List<T> getData() {
		return data;
	}

	public void setData(List<T> data) {
		this.data = data;
	}

	public Integer getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}

	public Integer getTotalPageSize() {
		return totalPageSize;
	}

	public void setTotalPageSize(Integer totalPageSize) {
		this.totalPageSize = totalPageSize;
	}
	
}
