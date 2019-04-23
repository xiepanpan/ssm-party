package com.baobao.common.mapping;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baobao.common.model.CollectionModel;
import com.baobao.common.model.NewsInfoModel;

public interface CollectionModelMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CollectionModel record);

    int insertSelective(CollectionModel record);

    CollectionModel selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CollectionModel record);

    int updateByPrimaryKey(CollectionModel record);
    
    int updateByNewId(CollectionModel record);
    /**
     * 根据用户和新闻id查询是否被收藏
     * <p>TODO</p>
     * @param record
     * @return
     * @author 夏思明
     */
    int selectByNUId(CollectionModel record);
    
    List<NewsInfoModel> getStudyCollection(@Param("userId")Integer userId,@Param("start")Integer start,@Param("end")Integer end);
    
}