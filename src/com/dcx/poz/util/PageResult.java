package com.dcx.poz.util;

import java.util.List;

public class PageResult<T> {
	private Integer page;//当前页号
	private Integer total;//总页数
	private Integer records;//总条数
	private List<T> rows;//数据
	public Integer getPage() {
		return page;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	public Integer getTotal() {
		return total;
	}
	public void setTotal(Integer total) {
		this.total = total;
	}
	public Integer getRecords() {
		return records;
	}
	public void setRecords(Integer records) {
		this.records = records;
	}
	public List<T> getRows() {
		return rows;
	}
	public void setRows(List<T> rows) {
		this.rows = rows;
	}	
}
