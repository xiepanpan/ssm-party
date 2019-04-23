package com.baobao.common.mapping;

import org.apache.ibatis.annotations.Param;

import com.baobao.common.model.ActTypeRelation;

public interface ActTypeRelationMapper {
    int deleteByPrimaryKey(Integer actTypeRelationId);

    int insert(ActTypeRelation record);

    int insertSelective(ActTypeRelation record);

    ActTypeRelation selectByPrimaryKey(Integer actTypeRelationId);

    int updateByPrimaryKeySelective(ActTypeRelation record);

    int updateByPrimaryKey(ActTypeRelation record);

    int addRelation(@Param("actId")Integer actId,@Param("typeIds")Integer[] typeIds);
}