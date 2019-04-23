package com.baobao.common.mapping;

import com.baobao.common.model.CarouselModel;

public interface CarouselModelMapper {
    int deleteByPrimaryKey(Integer carouselId);

    int insert(CarouselModel record);

    int insertSelective(CarouselModel record);

    CarouselModel selectByPrimaryKey(Integer carouselId);

    int updateByPrimaryKeySelective(CarouselModel record);

    int updateByPrimaryKey(CarouselModel record);
}