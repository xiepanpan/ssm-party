package com.baobao.common.mapping;

import java.util.List;

import com.baobao.common.model.HelpInfoModel;

public interface HelpInfoModelMapper {
    int deleteByPrimaryKey(Integer helpId);

    int insert(HelpInfoModel record);

    int insertSelective(HelpInfoModel record);

    HelpInfoModel selectByPrimaryKey(Integer helpId);

    int updateByPrimaryKeySelective(HelpInfoModel record);

    int updateByPrimaryKey(HelpInfoModel record);
    
    List<HelpInfoModel> getHelpList();
    
    Integer deleteBatch(List<Integer> list);
}