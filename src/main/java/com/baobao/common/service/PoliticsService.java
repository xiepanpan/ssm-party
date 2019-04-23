/**
 * 2017 2017年8月24日 
 */
package com.baobao.common.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baobao.common.cmd.SearchCondition;
import com.baobao.common.mapping.NewsInfoModelMapper;
import com.baobao.common.model.NewsInfoModel;

/**
 * <P>
 *  新闻管理-时政学习service
 * </P>
 * @author 袁杨柳 (17607177525)
 * 2017年8月24日 下午1:49:37
 */
@Service
public class PoliticsService {
	@Autowired
	private NewsInfoModelMapper newsInfoModelMapper;
	
	/**
	 * <p>
	 * 时政学习列表查询
	 * </p>
	 * @param cmd 入参 
	 * @return 
	 * @author 袁杨柳(17607177525)
	 */
	public ArrayList<NewsInfoModel> getNews(SearchCondition cmd) {
		// TODO Auto-generated method stub
		return newsInfoModelMapper.getNews(cmd);
	}
	
}
