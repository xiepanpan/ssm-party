package com.baobao.common.mapping;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baobao.common.cmd.ActCmd;
import com.baobao.common.model.Activity;
import com.baobao.common.model.andshow.ActComment;
import com.baobao.common.model.andshow.ActCount;
import com.baobao.common.model.andshow.ActDetail;
import com.baobao.common.model.andshow.ActList;
import com.baobao.common.model.andshow.FinishedActList;
import com.baobao.common.model.andshow.NewActList;

public interface ActivityMapper {
    int deleteByPrimaryKey(Integer activityId);

    int insert(Activity record);

    int insertSelective(Activity record);

    Activity selectByPrimaryKey(Integer activityId);
    
    List<Integer> selectFinish();
    
    int ActFinishByIds(List<Integer> actIds);
    
    int ActFinish();

    int updateByPrimaryKeySelective(Activity record);

    int updateByPrimaryKey(Activity record);
    
    List<NewActList> getFinishedActList(ActCmd cmd);
    
    List<NewActList> getDoingActList(Integer userId);
    
    
    List<ActComment> getComment(@Param("actId")Integer actId,@Param("userId")Integer userId);
    
    ActDetail getActDetail(@Param("actId")Integer actId,@Param("userId")Integer userId);
    
    
    List<ActCount> getActCount();
}