package com.baobao.common.mapping;

import java.util.List;

import com.baobao.common.model.Task;
import com.baobao.common.model.andshow.TaskDetail;
import com.baobao.common.model.andshow.TaskList;

public interface TaskMapper {
    int deleteByPrimaryKey(Integer taskId);

    int insert(Task record);

    int insertSelective(Task record);

    Task selectByPrimaryKey(Integer taskId);
    
    Task selectByDeadline(Integer taskId);

    int updateByPrimaryKeySelective(Task record);

    int updateByPrimaryKey(Task record);
    
    int overTask(Integer taskId);
    
    int overTaskByTime();
    
    List<Integer> selectNoSuccess();
    
    int updateState(List<Integer> list);

    List<TaskList> getWillTaskLit(Integer userId);
    
    List<TaskList> getMyTaskLit(Integer userId);
    
    TaskDetail getTaskDetail(Integer taskId);

}