package com.baobao.common.mapping;

import com.baobao.common.model.TempActInfoModel;

public interface TempActInfoModelMapper {
    int deleteByPrimaryKey(Integer tempactId);

    int insert(TempActInfoModel record);

    int insertSelective(TempActInfoModel record);

    TempActInfoModel selectByPrimaryKey(Integer tempactId);

    int updateByPrimaryKeySelective(TempActInfoModel record);

    int updateByPrimaryKey(TempActInfoModel record);
    
    int deleteByActId(Integer tempactActid);
}