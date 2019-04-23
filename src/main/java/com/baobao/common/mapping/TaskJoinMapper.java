package com.baobao.common.mapping;

import org.apache.ibatis.annotations.Param;

import com.baobao.common.model.TaskJoin;

public interface TaskJoinMapper {
    int deleteByPrimaryKey(Integer taskJoinId);

    int insert(TaskJoin record);

    int insertSelective(TaskJoin record);
    
    
    int selectCount(Integer taskId);

    TaskJoin selectByPrimaryKey(Integer taskJoinId);
    
    TaskJoin selectByTaskAndUser(@Param("taskId")Integer taskId,@Param("userId")Integer userId);

    int updateByPrimaryKeySelective(TaskJoin record);

    int updateByPrimaryKey(TaskJoin record);
    
    int accept(@Param("taskId")Integer taskId ,@Param("userId")Integer userId);
    
    int finsh(TaskJoin taskJoin);
    
    int insertList(@Param("taskId")Integer taskId ,@Param("userIds")Integer[] userIds);
}