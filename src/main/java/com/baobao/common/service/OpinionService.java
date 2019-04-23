package com.baobao.common.service;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.baobao.common.mapping.OpinionMapper;
import com.baobao.common.model.Opinion;

@Service
public class OpinionService {
	
	@Autowired
	private OpinionMapper opinionMapper;
	
	@Autowired
	private HttpServletRequest request;
	
	
	/**
	 * 
	 * 添加意见
	 * @author 袁子龙（15071746320）
	 * @param opinion 意见的model
	 * @return Boolean
	 * @date 2018年6月7日
	 */
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
	public Boolean addOpinion(Opinion opinion){
		Integer userId = (Integer) request.getSession().getAttribute("userId");
		opinion.setOpinionUserId(userId);
		opinion.setOpinionTime(new Date());
		return opinionMapper.insertSelective(opinion)>0?true:false;
	}

	/**
	 * 
	 * 获取所有的意见
	 * @author 袁子龙（15071746320）
	 * @param 
	 * @return List<Opinion>
	 * @date 2018年6月7日
	 */
	@Transactional(readOnly=true)
	public List<Opinion> getOpinions(){
		return opinionMapper.selectAll();
	}
	
	/**
	 * 
	 * 删除意见
	 * @author 袁子龙（15071746320）
	 * @param id 意见的id
	 * @return Boolean
	 * @date 2018年6月7日
	 */
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
	public Boolean deleteOpinion(Integer id){
		return opinionMapper.deleteByPrimaryKey(id)>0?true:false;
	}
}
