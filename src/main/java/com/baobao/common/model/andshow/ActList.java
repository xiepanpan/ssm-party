package com.baobao.common.model.andshow;

import java.util.List;


public class ActList {
	
	private Integer actId;

	private String createName;
	
	private String actContent;
	
	private String actStartTime;
	
	private List<String> branNames;
	
	private List<String> typeNames;
	
	
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

	public Integer getActId() {
		return actId;
	}

	public void setActId(Integer actId) {
		this.actId = actId;
	}

	public String getCreateName() {
		return createName;
	}

	public void setCreateName(String createName) {
		this.createName = createName;
	}
	
	
}
