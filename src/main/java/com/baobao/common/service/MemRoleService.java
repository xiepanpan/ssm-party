package com.baobao.common.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baobao.common.mapping.MemRoleModelMapper;
import com.baobao.common.model.MemRoleModel;

@Service
public class MemRoleService {
	@Autowired
	private MemRoleModelMapper memRoleModelMapper;
	
	public MemRoleModel getMemRoleModel(Integer userId){
		return memRoleModelMapper.selectByMemberId(userId);
	}
}
