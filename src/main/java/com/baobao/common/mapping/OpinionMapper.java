package com.baobao.common.mapping;

import java.util.List;

import com.baobao.common.model.Opinion;

public interface OpinionMapper {
   

	int deleteByPrimaryKey(Integer opinionId);

    int insert(Opinion record);

    int insertSelective(Opinion record);

    Opinion selectByPrimaryKey(Integer opinionId);
    
    List<Opinion> selectAll();

    int updateByPrimaryKeySelective(Opinion record);

    int updateByPrimaryKey(Opinion record);
}