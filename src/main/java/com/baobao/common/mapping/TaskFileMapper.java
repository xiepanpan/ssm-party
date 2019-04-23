package com.baobao.common.mapping;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baobao.common.model.TaskFile;

public interface TaskFileMapper {
    int deleteByPrimaryKey(Integer taskFileId);

    int insert(TaskFile record);

    int insertSelective(TaskFile record);

    TaskFile selectByPrimaryKey(Integer taskFileId);

    int updateByPrimaryKeySelective(TaskFile record);

    int updateByPrimaryKey(TaskFile record);
    
    int insertList(List<TaskFile> list);
    
    int insertTask(@Param("fileIds")Integer[] fileIds,@Param("taskId")Integer taskId);
    
    int insertTask(@Param("fileIds")List<Integer> fileIds,@Param("taskId")Integer taskId);
    
}