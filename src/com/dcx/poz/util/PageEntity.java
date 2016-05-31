/*
* -------------------------------------------------------
* Copyright (c) 2015, 北京易易科技有限公司
* All rights reserved.
* 
* FileName：PageEntity.java
* CreateTime:2015年9月10日下午4:06:34
* Description：简要描述本文件的内容
* History：
* Date           Author               Desc
* -------------------------------------------------------
*/
package com.dcx.poz.util;

public class PageEntity<T> {
	private Integer page;//当前页号
	private Integer rows; // 每页大小
	private Integer offset; // 起始行号
	private T params; // 传入的参数
	private String sidx;//排序的列明
	private String sord;//排序的方式
	
	public Integer getPage() {
		return page;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	public Integer getRows() {
		return rows;
	}
	public void setRows(Integer rows) {
		this.rows = rows;
	}
	public Integer getOffset() {
		return offset;
	}
	public void setOffset() {
		if(page<1) page=1;
		this.offset = (page-1)*rows;
	}
	public T getParams() {
		return params;
	}
	public void setParams(T params) {
		this.params = params;
	}
	public String getSidx() {
		return sidx;
	}
	public void setSidx(String sidx) {
		this.sidx = sidx;
	}
	public String getSord() {
		return sord;
	}
	public void setSord(String sord) {
		this.sord = sord;
	}
	
	
	
}
