package com.baobao.common.model;

public class ResultModel<T> {
	/**
	 * 返回的数据对应个model
	 */
    private T data ; 
    /**
     * 出错或信息的编号
     */
    private int info; 
    /**
     * 出错的原因
     */
    private String message; 
    /**
     * 查询是否成功，成功为true，否则为false
     */
    private boolean status;
    
    private Integer pageIndex;
    
    private Integer pageTotal;
    
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	public int getInfo() {
		return info;
	}
	public void setInfo(int info) {
		this.info = info;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public Integer getPageIndex() {
		return pageIndex;
	}
	public void setPageIndex(Integer pageIndex) {
		this.pageIndex = pageIndex;
	}
	public Integer getPageTotal() {
		return pageTotal;
	}
	public void setPageTotal(Integer pageTotal) {
		this.pageTotal = pageTotal;
	} 
    
	
}

