package com.baobao.common.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.baobao.common.mapping.PartyBranchModelMapper;
import com.baobao.common.model.PartyBranchModel;

@Service
public class PartyService {
	@Autowired
	private PartyBranchModelMapper partyBranchMapper;
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public int insertPartBranch(PartyBranchModel record){
		int i = 0 ;
		i = this.partyBranchMapper.insertSelective(record);
		return i;
	}
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public int updatePartBranch(PartyBranchModel record){
		int i = 0 ;
		i = this.partyBranchMapper.updateByPrimaryKeySelective(record);
		return i;
		
	}
	public List<PartyBranchModel> selectListBySearch(String searchValue){
		List<PartyBranchModel> list = new ArrayList<PartyBranchModel>();
		list = this.partyBranchMapper.selectListBySearch(searchValue);
		return list;
		
	}
	
}
