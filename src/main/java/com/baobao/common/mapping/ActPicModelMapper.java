package com.baobao.common.mapping;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baobao.common.model.ActPicModel;

public interface ActPicModelMapper {
    int deleteByPrimaryKey(Integer actPicId);
    
    int deleteByActId(Integer actId);

    int insert(ActPicModel record);

    int insertSelective(ActPicModel record);

    ActPicModel selectByPrimaryKey(Integer actPicId);

    int updateByPrimaryKeySelective(ActPicModel record);

    int updateByPrimaryKey(ActPicModel record);
    
    List<String> selectImageByBrId(ActPicModel record);
    //根据图片路径删除图片
    int deleteByImgUrl(@Param("actPicUrl")String actPicUrl);
}