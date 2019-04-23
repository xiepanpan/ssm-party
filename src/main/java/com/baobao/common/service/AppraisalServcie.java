package com.baobao.common.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.SimpleFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.baobao.common.mapping.AppraisalInfoModelMapper;
import com.baobao.common.model.AppraisalModel;

@Service
public class AppraisalServcie {
	@Autowired
	private AppraisalInfoModelMapper appraisalInfoModelMapper;
	@Transactional(readOnly=true)
	public List<AppraisalModel> getAppraisalList(Integer userid){
		List<AppraisalModel> list = this.appraisalInfoModelMapper.selectAll(userid);
		return list;
	}
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public void  insertAppra(AppraisalModel app){
		this.appraisalInfoModelMapper.insertSelective(app);
	}
}
