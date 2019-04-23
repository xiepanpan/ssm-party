package com.baobao.common.model;

import java.util.List;

public class TotalPartInfoModel {
	private String partBranchName;
	private Integer yxnum;
	private List<TotalInfoModel> InfoList;
	public String getPartBranchName() {
		return partBranchName;
	}
	public void setPartBranchName(String partBranchName) {
		this.partBranchName = partBranchName;
	}
	public Integer getYxnum() {
		return yxnum;
	}
	public void setYxnum(Integer yxnum) {
		this.yxnum = yxnum;
	}
	public List<TotalInfoModel> getInfoList() {
		return InfoList;
	}
	public void setInfoList(List<TotalInfoModel> infoList) {
		InfoList = infoList;
	}
	
}
