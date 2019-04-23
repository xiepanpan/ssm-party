package com.baobao.common.mapping;

import java.util.ArrayList;
import java.util.List;

import com.baobao.common.model.ImageBoModel;
import com.baobao.common.model.ImageTypeModel;

public interface ImageTypeModelMapper {
    int deleteByPrimaryKey(Integer imageId);

    int insert(ImageTypeModel record);

    int insertSelective(ImageTypeModel record);

    ImageTypeModel selectByPrimaryKey(Integer imageId);

    int updateByPrimaryKeySelective(ImageTypeModel record);

    int updateByPrimaryKey(ImageTypeModel record);
    
    ArrayList<ImageTypeModel> selectAll();
 }