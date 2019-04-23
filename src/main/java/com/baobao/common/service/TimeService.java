package com.baobao.common.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baobao.common.mapping.ActivityMapper;
import com.baobao.common.mapping.TaskMapper;

@Service
public class TimeService {
	
	
	@Autowired
	private ActivityMapper activityMapper;
	
	@Autowired
	private TaskMapper taskMapper;
	
	
	public void finishAct(){
		activityMapper.ActFinish();
	}
	
	public void finishTask(){
		taskMapper.overTaskByTime();
		List<Integer> list = taskMapper.selectNoSuccess();
		taskMapper.updateState(list);
	}
}
