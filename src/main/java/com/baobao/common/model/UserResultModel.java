package com.baobao.common.model;

public class UserResultModel {
	private String gender;
	private String iconUrl;
	private Integer id;
	private String name;
	private String dangyuanType;//党员类型
	private String partyOrganizationName;
	private String jobType;
	private Integer activityStatus;
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getIconUrl() {
		return iconUrl;
	}
	public void setIconUrl(String iconUrl) {
		this.iconUrl = iconUrl;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getPartyOrganizationName() {
		return partyOrganizationName;
	}
	public void setPartyOrganizationName(String partyOrganizationName) {
		this.partyOrganizationName = partyOrganizationName;
	}
	
	public String getDangyuanType() {
		return dangyuanType;
	}
	public void setDangyuanType(String dangyuanType) {
		this.dangyuanType = dangyuanType;
	}
	public String getJobType() {
		return jobType;
	}
	public void setJobType(String jobType) {
		this.jobType = jobType;
	}
	public Integer getActivityStatus() {
		return activityStatus;
	}
	public void setActivityStatus(Integer activityStatus) {
		this.activityStatus = activityStatus;
	}
	
	
}
