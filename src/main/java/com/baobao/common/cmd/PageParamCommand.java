package com.baobao.common.cmd;

import java.util.Calendar;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;



/**
 * 
 *
 */
public class PageParamCommand extends BaseObject {

	/**
	 * TODO
	 */
	private static final long serialVersionUID = 1L;

	private Integer page=1;
	
	private Integer rows=10;
	
	private Integer limitIndex;
	
	private String dateColumn;
	
	

	private String searchValue;
	
	
	

	public PageParamCommand() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PageParamCommand(Integer page, Integer rows) {
		super();
		this.page = page;
		this.rows = rows;
	}

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

	public String getDateColumn() {
		return dateColumn;
	}

	public void setDateColumn(String dateColumn) {
		this.dateColumn = dateColumn;
	}

	
	
	public String getSearchValue() {
		return searchValue;
	}

	public void setSearchValue(String searchValue) {
		
		if(null!=searchValue){
			this.searchValue = searchValue.trim();
		}else
		{
			this.searchValue=searchValue;
		}
	}

	public Integer getLimitIndex() {
		return (page-1)*rows;
	}

/*	public void setLimitIndex(Integer limitIndex) {
		this.limitIndex = limitIndex;
	}*/
	
	
	
}
