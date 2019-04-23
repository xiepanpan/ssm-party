package com.baobao.common.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baobao.common.mapping.BranchInfoModelMapper;
import com.baobao.common.mapping.PartyBranchModelMapper;
import com.baobao.common.model.TotalBranchInfoModel;

@Service
public class BranchAppService {
	@Autowired
	private BranchInfoModelMapper branchInfoMapper;
	@Autowired
	private PartyBranchModelMapper partyBranchModelMapper;
	//统计各个支部的人数
	public List<Map<String, Integer>> getBranchesInfo(Integer page){
		Integer start = (page-1)*20;
		Integer end = 20;
		List<Map<String, Integer>> result = new ArrayList<Map<String,Integer>>();
		result = this.branchInfoMapper.getBranchesInfo(start,end);
		return result;
		
	}
	public TotalBranchInfoModel getBranchInfoAll(){
		//获取校级下所有的支部信息
		TotalBranchInfoModel result = new TotalBranchInfoModel();
		result = this.partyBranchModelMapper.countAllInfo();
		return result;
		
	}
}
