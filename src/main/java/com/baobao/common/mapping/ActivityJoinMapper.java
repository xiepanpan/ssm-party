package com.baobao.common.mapping;

import org.apache.ibatis.annotations.Param;

import com.baobao.common.model.ActivityJoin;

public interface ActivityJoinMapper {
    int deleteByPrimaryKey(Integer activityJoinId);

    int insert(ActivityJoin record);

    int insertSelective(ActivityJoin record);

    ActivityJoin selectByPrimaryKey(Integer activityJoinId);

    int updateByPrimaryKeySelective(ActivityJoin record);

    int updateByPrimaryKey(ActivityJoin record);
    
    int addJoin(@Param("actId")Integer actId,@Param("userIds")Integer[] userIds);
}