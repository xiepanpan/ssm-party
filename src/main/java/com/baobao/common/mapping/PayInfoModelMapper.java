package com.baobao.common.mapping;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baobao.common.model.ContributionRecordModel;
import com.baobao.common.model.PayInfoModel;

public interface PayInfoModelMapper {
    int deleteByPrimaryKey(Integer payId);

    int insert(PayInfoModel record);

    int insertSelective(PayInfoModel record);

    PayInfoModel selectByPrimaryKey(Integer payId);

    int updateByPrimaryKeySelective(PayInfoModel record);

    int updateByPrimaryKey(PayInfoModel record);
    
    List<PayInfoModel>  getPayList();
    
    /**
     * 查询每年已缴的信息数据
     * @return
     */
    List<ContributionRecordModel> getPayModel(@Param("payTel") String tel);
    
    Float getMonMoney(@Param("payTel") String tel);
    
    Float getYearMoney(@Param("payTel") String tel);
    
    Float getAllMoney(@Param("payTel") String tel);
    List<ContributionRecordModel> getPayModelByYear(HashMap<String, Object> hashMap);
    
    int deleteBatch(List<Integer> list);
    
    List<PayInfoModel> getPayInfoBypartId(@Param("brId") Integer brId);
}