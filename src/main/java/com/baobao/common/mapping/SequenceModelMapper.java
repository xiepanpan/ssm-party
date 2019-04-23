package com.baobao.common.mapping;

import com.baobao.common.model.SequenceModel;

public interface SequenceModelMapper {
    int deleteByPrimaryKey(String name);

    int insert(SequenceModel record);

    int insertSelective(SequenceModel record);

    SequenceModel selectByPrimaryKey(String name);

    int updateByPrimaryKeySelective(SequenceModel record);

    int updateByPrimaryKey(SequenceModel record);
    
    String getId();
}