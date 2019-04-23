package com.baobao.common.cmd;

public class BranchCondition {
	/**
	 *  当前页，默认第一页
	 */
	private int page = 1;
	/**
	 *  页面大小，默认10条
	 */
	private int rows = 10;
	/**
	 * 搜索条件
	 */
	private String searchValue;
	
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getRows() {
		return rows;
	}
	public void setRows(int rows) {
		this.rows = rows;
	}
	/**
	 * @return the searchValue
	 */
	public String getSearchValue() {
		return searchValue;
	}
	/**
	 * @param searchValue the searchValue to set
	 */
	public void setSearchValue(String searchValue) {
		this.searchValue = searchValue;
	}
	
}
