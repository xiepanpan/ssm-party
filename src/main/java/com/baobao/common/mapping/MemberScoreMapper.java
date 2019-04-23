package com.baobao.common.mapping;

import com.baobao.common.model.MemberScore;

public interface MemberScoreMapper {
    int deleteByPrimaryKey(Integer memberId);

    int insert(MemberScore record);

    int insertSelective(MemberScore record);

    MemberScore selectByPrimaryKey(Integer memberId);

    int updateByPrimaryKeySelective(MemberScore record);

    int updateByPrimaryKey(MemberScore record);
}