package com.baobao.common.mapping;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.baobao.common.cmd.PartyCmd;
import com.baobao.common.model.PartyBranchModel;
import com.baobao.common.model.TotalBranchInfoModel;

public interface PartyBranchModelMapper {
    int deleteByPrimaryKey(Integer partyId);

    int insert(PartyBranchModel record);

    int insertSelective(PartyBranchModel record);

    PartyBranchModel selectByPrimaryKey(Integer partyId);
    
    List<PartyBranchModel> selectAll(PartyCmd partyCmd);
    
    List<PartyBranchModel> selectFaculty(PartyCmd partyCmd);

    int updateByPrimaryKeySelective(PartyBranchModel record);

    int updateByPrimaryKey(PartyBranchModel record);
    
    int deleteById(Integer partyId);
    
    List<PartyBranchModel> selectListBySearch(@Param("searchValue")String searchValue);
    
    List<Map<Integer,String>> getChildList(Integer partyBranchType);
    //查询校以及下级支部的所有详情信息
    TotalBranchInfoModel countAllInfo();
    //批量删除
    int updateBatch(List<Integer> list);
    
    List<PartyBranchModel> selectSchoolFaculty();
}