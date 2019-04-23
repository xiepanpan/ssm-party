package com.baobao.common.model.andshow;

import java.util.Date;
import java.util.List;

public class ActDetail {

	private Integer actId;
	
	private String name;
	
	private String content;
	
	private Date startTime;
	
	private Date endTime;
	
	private String address;
	
	private List<String> branNames;
	
	private List<String> typeNames; 
	
	private Integer goodNumber;
	
	private Integer collectState;
	
	private List<ActComment> comments;


	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public List<String> getBranNames() {
		return branNames;
	}

	public void setBranNames(List<String> branNames) {
		this.branNames = branNames;
	}

	public List<String> getTypeNames() {
		return typeNames;
	}

	public void setTypeNames(List<String> typeNames) {
		this.typeNames = typeNames;
	}

	public Integer getGoodNumber() {
		return goodNumber;
	}

	public void setGoodNumber(Integer goodNumber) {
		this.goodNumber = goodNumber;
	}

	public Integer getCollectState() {
		return collectState;
	}

	public void setCollectState(Integer collectState) {
		this.collectState = collectState;
	}

	public List<ActComment> getComments() {
		return comments;
	}

	public void setComments(List<ActComment> comments) {
		this.comments = comments;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getActId() {
		return actId;
	}

	public void setActId(Integer actId) {
		this.actId = actId;
	}

	
	
	
}
