/*******************************************************************************
 * Project : hbjiandang
 * Create on 2017年9月4日 上午10:34:37
 * Copyright (c) 2014 - 2016 粮达网
 * 注意：本内容仅限于粮达网内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
package com.baobao.common.cmd;

import java.util.Date;

/**
 * <P>TODO</P>
 * @author 夏思明
 * @date 2017年9月4日 上午10:34:37
 */
public class ActivityCondition{
	private Integer id;
	private Integer state;
	private Integer actMemberId;
	private String reports;
	private Date actRepottime;
	private Integer type;//报告提交类型
	private String comment;
	private Date actComtime;
	private Integer actComId;//评论人
	private Integer page = 1;
	private Integer rows = 10;
	private Integer startIndex;
	private Integer endIndex;
	/**
	 * @return the state
	 */
	public Integer getState() {
		return state;
	}
	/**
	 * @param state the state to set
	 */
	public void setState(Integer state) {
		this.state = state;
	}
	/**
	 * @return the page
	 */
	
	/**
	 * @param page the page to set
	 */
	
	/**
	 * @return the rows
	 */
	
	/**
	 * @param rows the rows to set
	 */
	
	/**
	 * @return the actMemberId
	 */
	public Integer getActMemberId() {
		return actMemberId;
	}
	/**
	 * @param actMemberId the actMemberId to set
	 */
	public void setActMemberId(Integer actMemberId) {
		this.actMemberId = actMemberId;
	}
	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * @return the reports
	 */
	public String getReports() {
		return reports;
	}
	/**
	 * @param reports the reports to set
	 */
	public void setReports(String reports) {
		this.reports = reports;
	}
	/**
	 * @return the type
	 */
	public Integer getType() {
		return type;
	}
	/**
	 * @param type the type to set
	 */
	public void setType(Integer type) {
		this.type = type;
	}
	/**
	 * @return the actRepottime
	 */
	public Date getActRepottime() {
		return actRepottime;
	}
	/**
	 * @param actRepottime the actRepottime to set
	 */
	public void setActRepottime(Date actRepottime) {
		this.actRepottime = actRepottime;
	}
	/**
	 * @return the comment
	 */
	public String getComment() {
		return comment;
	}
	/**
	 * @param comment the comment to set
	 */
	public void setComment(String comment) {
		this.comment = comment;
	}
	/**
	 * @return the actComtime
	 */
	public Date getActComtime() {
		return actComtime;
	}
	/**
	 * @param actComtime the actComtime to set
	 */
	public void setActComtime(Date actComtime) {
		this.actComtime = actComtime;
	}
	/**
	 * @return the actComId
	 */
	public Integer getActComId() {
		return actComId;
	}
	/**
	 * @param actComId the actComId to set
	 */
	public void setActComId(Integer actComId) {
		this.actComId = actComId;
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
	public Integer getStartIndex() {
		return startIndex;
	}
	public void setStartIndex(Integer startIndex) {
		this.startIndex = startIndex;
	}
	public Integer getEndIndex() {
		return endIndex;
	}
	public void setEndIndex(Integer endIndex) {
		this.endIndex = endIndex;
	}
	
}
