package com.baobao.common.mapping;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.baobao.common.model.ActPushRecModel;

public interface ActPushRecModelMapper {
   

	int insert(ActPushRecModel record);

	int insertSelective(ActPushRecModel record);

	ActPushRecModel selectByPrimaryKey(Integer actPushRecId);

	int updateByPrimaryKeySelective(ActPushRecModel record);

	int updateByPrimaryKey(ActPushRecModel record);

	int deleteByPrimaryKey(Integer actPushRecId);
	
	int batchInsertRecord(List<ActPushRecModel> list);
	
	int updatePushStatus(ActPushRecModel actRecord);
	
	int deleteByActId(Integer actPushActid);
	
	List<Map<Integer,String>> getSelectedBranches(@Param("roleGrade")Integer roleGrade,@Param("actPushActid")Integer actPushActid);
}