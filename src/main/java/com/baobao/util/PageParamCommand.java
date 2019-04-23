package com.baobao.util;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

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
	
	private Integer brId;

	private String searchValue;
	
	/**
	 * 支部名称
	 */
	private String branchName;
	/**
	 * 支部Id
	 */
	private String branchId;

	private List<Integer> brIds;
	
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

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}
	
	public String getBranchId() {
		return branchId;
	}
	
	public void setBranchId(String branchId) {
		this.branchId = branchId;
	}

	public List<Integer> getBrIds() {
		return brIds;
	}

	public void setBrIds(List<Integer> brIds) {
		this.brIds = brIds;
	}

	public Integer getBrId() {
		return brId;
	}

	public void setBrId(Integer brId) {
		this.brId = brId;
	}

/*	public void setLimitIndex(Integer limitIndex) {
		this.limitIndex = limitIndex;
	}*/
	
	
	
}
