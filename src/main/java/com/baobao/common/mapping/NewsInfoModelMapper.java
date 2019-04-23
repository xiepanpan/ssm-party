package com.baobao.common.mapping;

import java.util.ArrayList;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.baobao.common.cmd.SearchCondition;
import com.baobao.common.model.NewsInfoModel;

public interface NewsInfoModelMapper {
    int deleteByPrimaryKey(Integer tid);

    int insert(NewsInfoModel record);
    
    ArrayList<NewsInfoModel> selectNewsByType(@Param("newsType")Integer  type);
    
    int insertSelective(NewsInfoModel record);

    NewsInfoModel selectByPrimaryKey(Integer tid);

    int updateByPrimaryKeySelective(NewsInfoModel record);

    int updateByPrimaryKey(NewsInfoModel record);
    
    List<NewsInfoModel> selectAllNews(@Param("newsType")Integer newsType);
    
	ArrayList<NewsInfoModel> getNews(SearchCondition cmd);
	
	int updateByPrimaryKey(String tid);
	
	int deleteBySoft(Integer tid);
	
	int deleteBatch(List<Integer> list);
}