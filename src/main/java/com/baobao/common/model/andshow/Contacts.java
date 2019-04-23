package com.baobao.common.model.andshow;

import java.util.List;

public class Contacts {
	
	private String branchName;
	
	private List<TaskUser> users;

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	public List<TaskUser> getUsers() {
		return users;
	}

	public void setUsers(List<TaskUser> users) {
		this.users = users;
	}
	
	
	
}
