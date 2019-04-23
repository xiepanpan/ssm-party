package com.baobao.common.model.andshow;

import java.util.List;

public class NewActList {
	
	private Integer actId;

	private String createName;
	
	private String actName;
	
	private String actContent;
	
	private String actStartTime;
	
	private String actEndTime;
	
	private String actAddress;
	
	private Integer goodNumber;
	
	private Integer commentNumber;
	
	private Integer goodState;
	
	private Integer collectState;
	
	private Integer collectNumber;
	
	private List<String> branNames;
	
	private List<String> typeNames;
	
	private Integer actState;

	public Integer getActId() {
		return actId;
	}

	public void setActId(Integer actId) {
		this.actId = actId;
	}

	public String getActName() {
		return actName;
	}

	public void setActName(String actName) {
		this.actName = actName;
	}

	public String getActContent() {
		return actContent;
	}

	public void setActContent(String actContent) {
		this.actContent = actContent;
	}

	public String getActStartTime() {
		return actStartTime;
	}

	public void setActStartTime(String actStartTime) {
		 int i = actStartTime.lastIndexOf(".");
			this.actStartTime = actStartTime.substring(0, i);
	}

	public String getActEndTime() {
		return actEndTime;
	}

	public void setActEndTime(String actEndTime) {
		 int i = actEndTime.lastIndexOf(".");
		this.actEndTime = actEndTime.substring(0, i);
	}

	public String getActAddress() {
		return actAddress;
	}

	public void setActAddress(String actAddress) {
		this.actAddress = actAddress;
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

	public Integer getCommentNumber() {
		return commentNumber;
	}

	public void setCommentNumber(Integer commentNumber) {
		this.commentNumber = commentNumber;
	}

	public String getCreateName() {
		return createName;
	}

	public void setCreateName(String createName) {
		this.createName = createName;
	}

	public Integer getGoodState() {
		return goodState;
	}

	public void setGoodState(Integer goodState) {
		this.goodState = goodState;
	}

	public Integer getCollectNumber() {
		return collectNumber;
	}

	public void setCollectNumber(Integer collectNumber) {
		this.collectNumber = collectNumber;
	}

	public Integer getActState() {
		return actState;
	}

	public void setActState(Integer actState) {
		this.actState = actState;
	}
	
	
	
}
