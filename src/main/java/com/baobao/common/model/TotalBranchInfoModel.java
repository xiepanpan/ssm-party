package com.baobao.common.model;

import java.util.List;

public class TotalBranchInfoModel {
	private Integer jjnum;//积极分子党员数量
	private Integer ybnum;//预备党员数量
	private Integer zsnum;//正式党员数量
	private String schBrName;//校级名称
	List<TotalPartInfoModel> partList;
	public Integer getJjnum() {
		return jjnum;
	}
	public void setJjnum(Integer jjnum) {
		this.jjnum = jjnum;
	}
	public Integer getYbnum() {
		return ybnum;
	}
	public void setYbnum(Integer ybnum) {
		this.ybnum = ybnum;
	}
	public Integer getZsnum() {
		return zsnum;
	}
	public void setZsnum(Integer zsnum) {
		this.zsnum = zsnum;
	}
	public String getSchBrName() {
		return schBrName;
	}
	public void setSchBrName(String schBrName) {
		this.schBrName = schBrName;
	}
	public List<TotalPartInfoModel> getPartList() {
		return partList;
	}
	public void setPartList(List<TotalPartInfoModel> partList) {
		this.partList = partList;
	}
	
}
