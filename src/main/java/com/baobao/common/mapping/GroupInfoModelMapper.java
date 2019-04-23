package com.baobao.common.mapping;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baobao.common.model.GroupInfoModel;

public interface GroupInfoModelMapper {

    int insert(GroupInfoModel record);

    int insertSelective(GroupInfoModel record);

    GroupInfoModel selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(GroupInfoModel record);

    int updateByPrimaryKey(GroupInfoModel record);
    List<Long> getGroupId(@Param("memUsername")String memUsername);
}