package com.baobao.common.model.andshow;

import java.util.List;

public class ActCount {
	
	private String branchName;
	
	private List<ActTypeCount> actTypeCounts;
	
	private Integer allNumber;

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	public List<ActTypeCount> getActTypeCounts() {
		return actTypeCounts;
	}

	public void setActTypeCounts(List<ActTypeCount> actTypeCounts) {
		this.actTypeCounts = actTypeCounts;
	}

	public Integer getAllNumber() {
		return allNumber;
	}

	public void setAllNumber(Integer allNumber) {
		this.allNumber = allNumber;
	}
	
	
	
}
