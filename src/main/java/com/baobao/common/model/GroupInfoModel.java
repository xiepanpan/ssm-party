package com.baobao.common.model;

public class GroupInfoModel {
    private Integer id;

	private Long groupId;

	private String memUsername;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Long getGroupId() {
		return groupId;
	}

	public void setGroupId(Long groupId) {
		this.groupId = groupId;
	}

	public String getMemUsername() {
		return memUsername;
	}

	public void setMemUsername(String memUsername) {
		this.memUsername = memUsername == null ? null : memUsername.trim();
	}

}