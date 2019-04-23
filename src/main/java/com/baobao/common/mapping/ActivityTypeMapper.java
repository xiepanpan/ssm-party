package com.baobao.common.mapping;

import java.util.List;

import com.baobao.common.model.ActivityType;

public interface ActivityTypeMapper {
    int deleteByPrimaryKey(Integer activityTypeId);

    int insert(ActivityType record);

    int insertSelective(ActivityType record);

    ActivityType selectByPrimaryKey(Integer activityTypeId);

    int updateByPrimaryKeySelective(ActivityType record);

    int updateByPrimaryKey(ActivityType record);
    
    int deleteById(Integer id);

    List<ActivityType> getAllType();
}