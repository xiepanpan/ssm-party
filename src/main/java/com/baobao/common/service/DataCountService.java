package com.baobao.common.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baobao.common.cmd.DataCmd;
import com.baobao.common.mapping.DataMapper;
import com.baobao.common.model.andshow.ActBranchCountModel;
import com.baobao.common.model.andshow.CountScore;

@Service
public class DataCountService {

	@Autowired
	private DataMapper dataMapper;
	
	/**
	 * 
	 * 获取党支部的排序
	 * @author 袁子龙（15071746320）
	 * @param type  1为月 2为年
	 * @return List<ActBranchCountModel> 党支部排序集合
	 * @date 2018年6月11日
	 */
	public List<ActBranchCountModel> getActBranchCountModels(DataCmd cmd){
		return dataMapper.getActBranchCount(cmd);
	}

	
	/**
	 * 
	 * 获取党员的排序
	 * @author 袁子龙（15071746320）
	 * @param type  1为月 2为年
	 * @return List<CountScore> 党员排序集合
	 * @date 2018年6月11日
	 */
	public List<CountScore> getCountScores(DataCmd cmd){
		if (null==cmd.getType()) {
			cmd.setType(1);
		}
		return dataMapper.getCountScore(cmd);
	}
}
