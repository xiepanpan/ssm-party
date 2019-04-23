package com.baobao.common.mapping;

import java.util.ArrayList;
import java.util.List;



import org.apache.ibatis.annotations.Param;

import com.baobao.common.model.AppraisalModel;


public interface AppraisalInfoModelMapper {
  
    int insert(AppraisalModel record);

    int insertSelective(AppraisalModel record);

    AppraisalModel selectByPrimaryKey(Integer aId);
    
    List<AppraisalModel> selectAll(@Param("uid") Integer userId);

    int updateByPrimaryKeySelective(AppraisalModel record);

    int updateByPrimaryKey(AppraisalModel record);
}