package com.baobao.common.mapping;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.baobao.common.cmd.BranchCondition;
import com.baobao.common.cmd.PartyCmd;
import com.baobao.common.model.BranchInfoModel;
import com.baobao.common.model.MemRoleModel;
import com.baobao.common.model.MemberInfoModel;
import com.baobao.common.model.andshow.TaskBranch;

public interface BranchInfoModelMapper {
    int deleteByPrimaryKey(Integer branchId);

    int insert(BranchInfoModel record);

    int insertSelective(BranchInfoModel record);

    BranchInfoModel selectByPrimaryKey(Integer branchId);

    int updateByPrimaryKeySelective(BranchInfoModel record);

    int updateByPrimaryKey(BranchInfoModel record);
    
    int deleteBYSoft(Integer branchId);
    
    List<BranchInfoModel> getBypage(BranchCondition cmd);

    ArrayList<BranchInfoModel> getBranchList(BranchCondition cmd);
    
    List<BranchInfoModel> getBranchInfoModels(Integer branchFatherId);
    
    List<Integer> getBranchId(Integer branchFatherId);
    
    List<BranchInfoModel> getBranchInfoModelsByCondition(PartyCmd partyCmd);
    
    int insertBranchInfoBatch(List<BranchInfoModel> list);
    
  /*  *//**
	 * @param miModelList
	 * @return
	 *//*
	int importExcelByBranchName(List<MemberInfoModel> miModelList);
    */
    int getListByName(@Param("branchName") String branchName);
    int getListById(@Param("branchId") Integer brId);
    
    List<Map<Integer, String>> getBranchChildId(Integer branchFatherId);
    
    List<Map<String,Integer>> getBranchesInfo(@Param("start")Integer start,@Param("end") Integer end);
    //根据支部的名称去查找当前的支部ID
    Integer getBranchIdsByName(@Param("branchName") String branchName);
    //批量删除支部
    Integer deleteBatch(List<Integer> list); 
    //根据人员Id查询支部ID
    Integer getBrIdByTel(@Param("memberTel")String memberTel);
    
    
    public List<TaskBranch> getTaskBranch();
}