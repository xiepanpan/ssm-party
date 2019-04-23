package com.baobao.util;

import java.util.ArrayList;
import java.util.List;
/**
 * 
 * <P>TODO</P>
 * @author 夏思明
 * @date 2017年8月22日 上午8:56:41
 */
public class PageData<E> extends BaseObject {

	/**
	 * TODO
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 总数
	 */
	private long total;
	
	/**
     * 页面大小
     */
    private int pageSize;
	
	/**
	 * 当前页数
	 */
	private int page;
	
	/**
	 * 总页数
	 */
	private int totalPage;
	
	/**
	 * 数据
	 */
	private List<E> rows = new ArrayList();

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
		countTotalPage();
		
	}

	private void countTotalPage(){
		if (pageSize > 0) {
			totalPage = (int) (total / pageSize + ((total % pageSize == 0) ? 0 : 1));
        } else {
        	totalPage = 0;
        }
	}
	
	public List<E> getRows() {
		return rows;
	}

	public void setRows(List<E> rows) {
		this.rows = rows;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}

	public PageData() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public PageData(Long total, List<E> rows) {
		super();
		this.total = total;
		this.rows = rows;
		countTotalPage();
	}

	public PageData(Long total, int pageSize, Integer page, List<E> rows) {
		super();
		this.total = total;
		this.pageSize = pageSize;
		this.page = page;
		this.rows = rows;
		countTotalPage();
	}
	
	
	
}
