package com.baobao.common.mapping;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baobao.common.model.StudyRecordModel;
import com.baobao.common.model.StudyRecordNewModel;

public interface StudyRecordModelMapper {
    int deleteByPrimaryKey(Integer sturecordId);

    int insert(StudyRecordModel record);

    int insertSelective(StudyRecordModel record);

    StudyRecordModel selectByPrimaryKey(Integer sturecordId);

    int updateByPrimaryKeySelective(StudyRecordModel record);

    int updateByPrimaryKey(StudyRecordModel record);
    
    StudyRecordModel getMaxDay(@Param("stuMemberId") Integer userId);
    
    int updateByUserId(StudyRecordModel record);
    
    StudyRecordModel selectByUserId(@Param("stuMemberId") Integer userId);
     StudyRecordModel selectTodayTime(@Param("stuMemberId") Integer userId);
    List<StudyRecordModel> allDays(@Param("stuMemberId") Integer userId);
    
    List<StudyRecordModel> selectStuReList(@Param("stuMemberId") Integer userId);
    /**
     * 
     * @param userId
     * @return查询一年的学习记录
     */
    int  countYearRe(@Param("stuMemberId") Integer userId);
    
    int countMonthRe(@Param("stuMemberId") Integer userId);
    
    List<StudyRecordNewModel> getStuNewList(@Param("stuMemberId") Integer userId);
}