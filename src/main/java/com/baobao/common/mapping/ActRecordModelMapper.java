package com.baobao.common.mapping;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baobao.common.cmd.ActivityCondition;
import com.baobao.common.model.ActRecordModel;
import com.baobao.common.model.ActivityCountModel;
import com.baobao.common.model.ActivityMobile;
import com.baobao.common.model.ChildModel;
import com.baobao.common.model.CommentModel;
import com.baobao.common.model.PartyUserModel;
import com.baobao.common.model.UserResultModel;
import com.github.pagehelper.Page;

public interface ActRecordModelMapper {
    int deleteByPrimaryKey(Integer actRecordId);

    int insert(ActRecordModel record);

    int insertSelective(ActRecordModel record);

    ActRecordModel selectByPrimaryKey(Integer actRecordId);

    int updateByPrimaryKeySelective(ActRecordModel record);

    int updateByPrimaryKey(ActRecordModel record);
    
    int batchInsertRecord(List<ActRecordModel> list);
    List<ActivityMobile> getActivtyModelByState(ActivityCondition cmd);
    /**
     * 修改活动状态
     */
    int updateStateById(ActivityCondition cmd);
    
    int insertReport(ActivityCondition cmd);
    /**
     * 获取报告
     */
    String getReports(ActivityCondition cmd);
    /**
     * 被评论人获取评论
     */
    CommentModel getComments(ActivityCondition cmd);
    
    List<UserResultModel> getPartMembers(@Param("actRecActid") Integer actRecActid);
    /**
     * 根据用户id查询用户半年内的数据
     * @param userId
     * @return
     */
    List<ActivityCountModel> getListForMonths(Integer userId);
    
    int deleteRecordByActId(Integer actRecActid);
}