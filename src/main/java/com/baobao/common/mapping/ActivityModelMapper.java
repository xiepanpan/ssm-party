package com.baobao.common.mapping;

import java.util.HashMap;
import java.util.List;

import com.baobao.common.cmd.ActivityCondition;
import com.baobao.common.model.ActivityMobile;
import com.baobao.common.model.ActivityModel;

public interface ActivityModelMapper {
    int deleteByPrimaryKey(Integer activityId);

    int insert(ActivityModel record);

    int insertSelective(ActivityModel record);

    ActivityModel selectByPrimaryKey(Integer activityId);

    int updateByPrimaryKeySelective(ActivityModel record);

    int updateByPrimaryKey(ActivityModel record);
    
    List<ActivityMobile> getActByManager(HashMap<String, Integer> map);
    
    int updateBublishStatus(Integer activityId);
    
    List<ActivityModel> getActivitiesByUserId(Integer activityLeaderid);
}