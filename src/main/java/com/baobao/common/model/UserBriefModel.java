package com.baobao.common.model;

import java.util.List;

public class UserBriefModel{
    private int id; //用户ID
    private String name; //用户名
    private String iconUrl; //用户头像url
    private String gender; //性别
    private int rolesType; //用户角色名
    private int partyClassification;// 党员类型
    private int branchId;
    private String partyOrganizationName; //所属党组织名称
    private String userPass;
    private String rolesName;
    private String partyClassificationName;
    private List<Long> groupId;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIconUrl() {
		return iconUrl;
	}
	public void setIconUrl(String iconUrl) {
		this.iconUrl = iconUrl;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public int getRolesType() {
		return rolesType;
	}
	public void setRolesType(int rolesType) {
		this.rolesType = rolesType;
	}
	public String getPartyOrganizationName() {
		return partyOrganizationName;
	}
	public void setPartyOrganizationName(String partyOrganizationName) {
		this.partyOrganizationName = partyOrganizationName;
	}

	public int getPartyClassification() {
		return partyClassification;
	}
	public void setPartyClassification(int partyClassification) {
		this.partyClassification = partyClassification;
	}
	public String getUserPass() {
		return userPass;
	}
	public void setUserPass(String userPass) {
		this.userPass = userPass;
	}
	public String getRolesName() {
		return rolesName;
	}
	public void setRolesName(String rolesName) {
		this.rolesName = rolesName;
	}
	public String getPartyClassificationName() {
		return partyClassificationName;
	}
	public void setPartyClassificationName(String partyClassificationName) {
		this.partyClassificationName = partyClassificationName;
	}
	public int getBranchId() {
		return branchId;
	}
	public void setBranchId(int branchId) {
		this.branchId = branchId;
	}
	public List<Long> getGroupId() {
		return groupId;
	}
	public void setGroupId(List<Long> groupId) {
		this.groupId = groupId;
	}
    
}

