package com.baobao.common.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baobao.common.mapping.GoodMapper;
import com.baobao.common.model.Good;

@Service
public class GoodService {
	
	@Autowired
	private GoodMapper goodMapper;
	
	@Autowired
	private HttpServletRequest request;
	
	
	/**
	 * 
	 * 点赞
	 * @author 袁子龙（15071746320）
	 * @param id 点赞对象id
	 * @return Boolean
	 * @date 2018年6月6日
	 */
	public Boolean addGood(Integer id){
		Integer userId = (Integer) request.getSession().getAttribute("userId");
		Good good = new Good();
		good.setGoodObjectId(id);
		good.setGoodUserId(userId);
		return goodMapper.insertSelective(good)>0?true:false;
	}
	
	public Integer getGoodNumber(Integer id){
		return goodMapper.getGoodNumber(id);
	}
	
	/**
	 * 
	 * 查询是否已经点赞
	 * @author 袁子龙（15071746320）
	 * @param id 点赞对象id
	 * @return Boolean
	 * @date 2018年6月6日
	 */
	public Boolean getGood(Integer id){
		Integer userId = (Integer) request.getSession().getAttribute("userId");
		return null!=goodMapper.getGood(userId, id)?true:false;
	}
}
