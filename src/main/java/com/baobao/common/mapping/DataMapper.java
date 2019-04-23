package com.baobao.common.mapping;

import java.util.List;

import com.baobao.common.cmd.DataCmd;
import com.baobao.common.model.andshow.ActBranchCountModel;
import com.baobao.common.model.andshow.CountScore;

public interface DataMapper {
	
	
	List<ActBranchCountModel> getActBranchCount(DataCmd cmd);
	
	
	List<CountScore>  getCountScore(DataCmd cmd);
	}
