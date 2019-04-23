package com.baobao.common.mapping;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baobao.common.model.TaskFinshFile;

public interface TaskFinshFileMapper {
    int deleteByPrimaryKey(Integer taskFinshFileId);

    int insert(TaskFinshFile record);

    int insertSelective(TaskFinshFile record);
    
    int addTaskFinishFile(@Param("taskJoinId")Integer taskJoinId,@Param("fileIds")Integer[] fileIds);
    
    int addTaskFinishFileList(@Param("taskJoinId")Integer taskJoinId,@Param("fileIds")List<Integer> fileIds);

    TaskFinshFile selectByPrimaryKey(Integer taskFinshFileId);

    int updateByPrimaryKeySelective(TaskFinshFile record);

    int updateByPrimaryKey(TaskFinshFile record);
}