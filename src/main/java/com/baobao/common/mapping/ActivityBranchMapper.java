package com.baobao.common.mapping;

import org.apache.ibatis.annotations.Param;

import com.baobao.common.model.ActivityBranch;

public interface ActivityBranchMapper {
    int deleteByPrimaryKey(Integer activityBranchRelationId);

    int insert(ActivityBranch record);

    int insertSelective(ActivityBranch record);

    ActivityBranch selectByPrimaryKey(Integer activityBranchRelationId);

    int updateByPrimaryKeySelective(ActivityBranch record);

    int updateByPrimaryKey(ActivityBranch record);
    
    int addBranch(@Param("actId")Integer actId,@Param("branchIds")Integer[] branchIds);
}