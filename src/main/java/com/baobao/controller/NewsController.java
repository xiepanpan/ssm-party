/*******************************************************************************
 * Project : hbjiandang
 * Create on 2017年8月23日 下午3:56:56
 * Copyright (c) 2014 - 2016 粮达网
 * 注意：本内容仅限于粮达网内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
package com.baobao.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.PageContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.baobao.common.cmd.SearchCondition;
import com.baobao.common.mapping.NewsInfoModelMapper;
import com.baobao.common.model.ImageBoModel;
import com.baobao.common.model.NewsInfoModel;
import com.baobao.common.model.ResultModel;
import com.baobao.common.model.StudyPageModel;
import com.baobao.common.service.NewsService;
import com.baobao.util.GetLocalIp;

/**
 * <P>新闻管理控制类</P>
 * @author 夏思明
 * @date 2017年8月23日 下午3:56:56
 */
@Controller
@RequestMapping("/newsController")
public class NewsController {
	private Logger log = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private NewsService newsService;
	@Autowired
	private NewsInfoModelMapper newsInfoModelMapper;
	
	/**
	 * 查询新闻详情
	 * @param request
	 * @param 新闻newsType字段值  21.22 理论学习  31.32时政学习
	 * @return
	 * @author 夏思明
	 */
	@RequestMapping("/showNews.htm")
	public ModelAndView showNews(HttpServletRequest request,SearchCondition cmd){
		ModelAndView mv=new ModelAndView();
		try{
			request.setCharacterEncoding("utf-8");
			if(cmd.getIsFlag()==1){
				if(cmd.getNewsType()!=3){
				mv.setViewName("newsManager");//访问新闻列表页面
				}else{
					mv.setViewName("lunboNews");
					mv.addObject("parentUrl", GetLocalIp.imgURL);
				}
			}else{
				mv.setViewName("article");//访问新闻详情页面
			}
			ArrayList<NewsInfoModel> newsList =  new ArrayList<NewsInfoModel>();
			newsList = this.newsService.selectNews(cmd);
			mv.addObject("result", newsList);
			mv.addObject("cmd", cmd);
		}catch (Exception e){
			log.error("查询新闻失败{}", e);
		}
		return mv;
	}
	
	/**
	 * 插入新闻
	 */
	@RequestMapping("/insertNews.htm")
	@ResponseBody
	public int insertNews(HttpServletRequest request,NewsInfoModel newsInfo){
		int i = 0;
		try{
		i = this.newsService.insertNewsInfo(newsInfo);
		}catch(Exception e){
			return 0;
		}
		return i;
	}
	
	/**
	 * 新闻插入显示
	 * @param request
	 * @param tId
	 * @return
	 * @author 夏思明
	 */
	@RequestMapping("/showInsertPage.htm")
	public ModelAndView showInsertPage(HttpServletRequest request,Integer ptype){
		ModelAndView modelAndView = new ModelAndView();
		if(ptype==1||ptype==2){
		modelAndView.setViewName("dialog/news_add");
		}else{
			modelAndView.setViewName("dialog/lunbo_add");
		}
		modelAndView.addObject("ptype",ptype);
		return modelAndView;
	}
	
	/**
	 * 新闻编辑
	 * @return 
	 */
	@RequestMapping("/showEditPage.htm")
	public ModelAndView showEditPage(HttpServletRequest request,Integer tId,Integer showType){
		ModelAndView modelAndView = new ModelAndView("dialog/news_edit");
		if(showType==1){
			modelAndView.setViewName("dialog/lunbo_edit");
		}else{
			modelAndView.setViewName("dialog/news_edit");
		}
		NewsInfoModel newsInfo = this.newsInfoModelMapper.selectByPrimaryKey(tId);
		modelAndView.addObject("newsInfo",newsInfo);
		return modelAndView;
	}

	/**
	 * 删除新闻
	 * @param request
	 * @param tId
	 * @return
	 * @author 夏思明
	 */
	@RequestMapping("/deleteById.htm")
	@ResponseBody
	public int deleteById(HttpServletRequest request,Integer tId){
		try{
		 this.newsInfoModelMapper.deleteBySoft(tId);
		}catch(Exception e){
			return  0;
		}
		return 1;
	}
	
	/**
	 * APP获取所有新闻信息及其路径
	 */
	@RequestMapping("/getStudyPage.app")
	@ResponseBody
	public Object getStudyPager(HttpServletRequest request){
		log.info("newsController-getStudyPager方法");
		ResultModel<StudyPageModel> model = new ResultModel<StudyPageModel>(); // 返回的结果集
		try{
			// 查询
			model=newsService.getAllNews(request);
		}catch(Exception e){
			model.setStatus(false);
			e.printStackTrace();
			log.error("getStudyPager方法出错！",e);
		}
		return model;
	}
	
	/**
	 * APP根据路径访问界面(查看新闻详情)
	 */
	@RequestMapping("/appSelectNew.htm")
	@ResponseBody
	public Object selectNew(int newsId){
		log.info("newsController-selectNew方法");
		ModelAndView mv = new ModelAndView();
		NewsInfoModel newsInfo = new NewsInfoModel(); // 当前新闻信息model
		try{
			// 查询
			newsInfo = newsService.selectByprimarykey(newsId);
			// 点击次数
			Integer readCount = newsInfo.getReadcount();
			// 访问当前新闻时点击次数+1
			readCount++;
			newsInfo.setReadcount(readCount);
			// 修改
			newsService.updateNews(newsInfo);
			
			mv.addObject("newsInfo", newsInfo);
			mv.setViewName("webpages/mobile_detail");

		}catch(Exception e){
			e.printStackTrace();
			log.error("selectNew方法出错！",e);
		}
		return mv;
	}

	
	/**
	 * <p>
	 * 修改新闻
	 * </p>
	 * @param record 入参 
	 * @author 袁杨柳(17607177525)
	 */
	@RequestMapping("/updateNews.htm")
	@ResponseBody
	public int updateNews(HttpServletRequest request,NewsInfoModel record){
		try{
			newsService.updateNews(record);
		}catch(Exception e){
			log.error("修改新闻出错", e);
			return 0;
		}
		return 1;
	}
	/**
	 * 插入轮播
	 * @param request
	 * @param cModel
	 * @param myfile
	 * @return
	 * @author 夏思明
	 */
	@RequestMapping("/insertLunbo.htm")
	@ResponseBody
	public int insertLunbo(HttpServletRequest request,ImageBoModel imgBo,@RequestParam(value = "myfile",required = false)MultipartFile myfile){
		try{
		this.newsService.insertImageAll(request, imgBo, myfile);
		}catch(Exception e){
			log.error("插入出错", e);
			return 0;
		}
		return 1;
	}
	/**
	 * 插入轮播的新闻
	 * @param request
	 * @param imgBo
	 * @param myfile
	 * @return
	 * @author 夏思明
	 */
	@RequestMapping("/insertLunboNews.htm")
	@ResponseBody
	public int insertLunbo(HttpServletRequest request,NewsInfoModel newsInfo,@RequestParam(value = "myfile",required = false)MultipartFile myfile){
		try{
		this.newsService.insertImage(request, newsInfo, myfile);
		}catch(Exception e){
			log.error("插入出错", e);
			return 0;
		}
		return 1;
	}
	
	@RequestMapping("/editLunboNews.htm")
	@ResponseBody
	public int editLunboNews(HttpServletRequest request,NewsInfoModel newsInfo,@RequestParam(value = "myfile",required = false)MultipartFile myfile){
		int i = 0;
		try{
			i = this.newsService.updateImage(request, newsInfo, myfile);
			}catch(Exception e){
				log.error("编辑出错", e);
				return 0;
			}
			return i;
	}
	@RequestMapping("/deleteBatch.htm")
	@ResponseBody
	public int deleteBatch(HttpServletRequest request,Integer[] newsIds){
		Integer id = 0;
		try {
			id =	this.newsService.deleteBatch(newsIds);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return id;
		
	}
	
	
	@RequestMapping("/getTextById")
	@ResponseBody
	public Object getTextById(Integer id){
		ResultModel<String>  resultModel = new ResultModel<String>();
		try {
			resultModel.setData(newsService.getTextById(id));
			resultModel.setStatus(true);
			resultModel.setMessage("获取数据成功");
		} catch (Exception e) {
			resultModel.setStatus(false);
			resultModel.setMessage("获取数据异常");
		}
		return resultModel;
	}
}

	