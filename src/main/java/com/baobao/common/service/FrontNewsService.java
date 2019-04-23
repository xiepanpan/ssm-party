/*******************************************************************************
 * Project : hbjiandang
 * Create on 2017年8月26日 下午5:59:48
 * Copyright (c) 2014 - 2016 粮达网
 * 注意：本内容仅限于粮达网内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
package com.baobao.common.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;

import com.baobao.common.cmd.SearchCondition;
import com.baobao.common.mapping.NewsInfoModelMapper;
import com.baobao.common.model.NewsInfoModel;

/**
 * <P>TODO</P>
 * @author 夏思明
 * @date 2017年8月26日 下午5:59:48
 */
@Service
public class FrontNewsService {
	private Logger log = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private NewsInfoModelMapper newsInfoMapper;
	@Transactional(readOnly = true)
	public List<NewsInfoModel> getAllFrontNews(SearchCondition cmd){
		log.info("查询方法");
		List<NewsInfoModel> list = this.newsInfoMapper.getNews(cmd);
		return list;
		
	
	}
	/**
	 * 根据类别查询
	 * <p>TODO</p>
	 * @param newsType
	 * @return
	 * @author 夏思明
	 */
	@Transactional(readOnly = true)
	public List<NewsInfoModel> getNewsBytype(Integer newsType){
		List<NewsInfoModel> list = this.newsInfoMapper.selectAllNews(newsType);
		return list;
		
	}
	
	@Transactional(readOnly = true)
	public NewsInfoModel searchById(Integer tId){
		return this.newsInfoMapper.selectByPrimaryKey(tId);
	}
}
