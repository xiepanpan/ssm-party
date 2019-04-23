/*******************************************************************************
 * Project : hbjiandang
 * Create on 2017年8月26日 下午5:58:37
 * Copyright (c) 2014 - 2016 粮达网
 * 注意：本内容仅限于粮达网内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
package com.baobao.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.baobao.common.cmd.SearchCondition;
import com.baobao.common.mapping.NewsInfoModelMapper;
import com.baobao.common.model.ImageBoModel;
import com.baobao.common.model.ImageTypeModel;
import com.baobao.common.model.NewsInfoModel;
import com.baobao.common.service.FrontNewsService;
import com.baobao.common.service.NewsService;
import com.baobao.common.service.PicService;
import com.baobao.util.GetLocalIp;

/**
 * <P>TODO</P>
 * @author 夏思明
 * @date 2017年8月26日 下午5:58:37
 */
@Controller
@RequestMapping("/frontNews")
public class FrontNewsController {
	private Logger log = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private FrontNewsService frontNewsService;
	@Autowired
	private PicService picService;
	@Autowired
	private NewsInfoModelMapper newsInfoModelMapper;
	@Autowired
	private NewsService newsService;
	@RequestMapping("/index.html")
	public ModelAndView frontLook(HttpServletRequest request,SearchCondition cmd){
		ModelAndView model = new ModelAndView("webpages/main");
		try{
		List<NewsInfoModel> result = this.frontNewsService.getAllFrontNews(cmd);
		//暂时先写死查询轮播图片的新闻
		cmd.setNewsType(3);
		List<NewsInfoModel> lunbo = this.newsInfoModelMapper.getNews(cmd);
		ArrayList<ImageTypeModel> imgList = this.picService.showPic();
		model.addObject("result", result);
		model.addObject("lunbo", lunbo);
		model.addObject("imgList", imgList);
		model.addObject("parentUrl", GetLocalIp.imgURL);
		}catch (Exception e){
			log.error("查询新闻失败{}", e);
		}
		return model;
	}
	/**
	 * 
	 * <p>TODO</p>
	 * @param newsType
	 * @return
	 * @author 夏思明
	 */
	@RequestMapping("/lookAlltypes.htm")
	public ModelAndView lookAlltypes(Integer newsType){
		ModelAndView model = new ModelAndView("webpages/list");
		try{
		List<NewsInfoModel> result = this.frontNewsService.getNewsBytype(newsType);
		model.addObject("result", result);
		if(newsType!=null){
		if(newsType == 1||newsType==2){
			model.addObject("newsparent","理论学习");
			if(newsType==1){
				model.addObject("newslist","系列讲话");
			}else{
				model.addObject("newslist","规章制度");
			}
		}else if(newsType == 3||newsType==4){
			model.addObject("newsparent","时政学习");
			if(newsType==3){
				model.addObject("newslist","国内时政");
			}else{
				model.addObject("newslist","省内时政");
			}
		}
		}}catch (Exception e){
			log.error("查询新闻失败{}", e);
		}
		return model;
		
	}
	@RequestMapping("/lookNewsById.htm")
	public ModelAndView lookNewsById(HttpServletRequest request,Integer tId){
		ModelAndView model = new ModelAndView("webpages/details");
		try{
		NewsInfoModel item = this.frontNewsService.searchById(tId);
		Integer count = item.getReadcount();
		count++;
		item.setReadcount(count);
		newsService.updateNews(item);
		if(item!=null){
			if(item.getNewsType()!=null){
				if(item.getNewsType() == 21||item.getNewsType()==22){
					model.addObject("newsparent","理论学习");
					if(item.getNewsType()==21){
						model.addObject("newslist","系列讲话");
					}else{
						model.addObject("newslist","规章制度");
					}
				}else if(item.getNewsType() == 31||item.getNewsType()==32){
					model.addObject("newsparent","时政学习");
					if(item.getNewsType()==31){
						model.addObject("newslist","国内时政");
					}else{
						model.addObject("newslist","省内时政");
					}
				}
			}else{
				if(item.getImgUrl()!=null){
					model.addObject("newsparent","轮播");
				}
			}
		}
		model.addObject("item", item);
		}catch (Exception e){
			log.error("查询新闻失败{}", e);
		}
		return model;
		
	}
}
