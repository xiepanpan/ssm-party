/*******************************************************************************
 * Project : hbjiandang
 * Create on 2017年8月29日 上午11:54:57
 * Copyright (c) 2014 - 2016 粮达网
 * 注意：本内容仅限于粮达网内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
package com.baobao.common.service;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.baobao.common.mapping.HelpInfoModelMapper;
import com.baobao.common.model.HelpInfoModel;

/**
 * <P>TODO</P>
 * @author 夏思明
 * @date 2017年8月29日 上午11:54:57
 */
@Service
public class HelpManagerServcice {
	@Autowired
	private HelpInfoModelMapper helpInfoModelMapper;
	
	@Transactional(readOnly = true)
	public List<HelpInfoModel> getHelpModels(){
		List<HelpInfoModel> list = this.helpInfoModelMapper.getHelpList();
		return list;
		
	}
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public int insertHelpModel(HelpInfoModel helpInfo){
		int i = this.helpInfoModelMapper.insertSelective(helpInfo);
		return i;
		
	}
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public int deleteBatch(Integer[] Ids){
		Integer i = 0;
		i = this.helpInfoModelMapper.deleteBatch(Arrays.asList(Ids));
		return i;
		
	}
}
