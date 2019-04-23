package com.baobao.common.mapping;

import java.util.List;

import com.baobao.common.model.MemRoleModel;

public interface MemRoleModelMapper {
    int deleteByPrimaryKey(Integer relationId);
    
    int deleteByBranchId(Integer branchId);
    
    int deleteByUserId(List<Integer> memeberId);

    int insert(MemRoleModel record);

    int insertSelective(MemRoleModel record);

    MemRoleModel selectByPrimaryKey(Integer relationId);
    
    MemRoleModel selectByMemberId(Integer memberId);

    int updateByPrimaryKeySelective(MemRoleModel record);

    int updateByPrimaryKey(MemRoleModel record);
}