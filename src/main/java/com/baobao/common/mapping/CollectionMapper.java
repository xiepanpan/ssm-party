package com.baobao.common.mapping;

import org.apache.ibatis.annotations.Param;

import com.baobao.common.model.Collection;

public interface CollectionMapper {
    int deleteByPrimaryKey(Integer collectionId);

    int insert(Collection record);

    int insertSelective(Collection record);

    int getCollectNumber(Integer id);
    
    Collection selectByPrimaryKey(Integer collectionId);
    
    Collection selectByUserAndObject(@Param("userId")Integer userId,@Param("id")Integer id);

    int updateByPrimaryKeySelective(Collection record);

    int updateByPrimaryKey(Collection record);
}