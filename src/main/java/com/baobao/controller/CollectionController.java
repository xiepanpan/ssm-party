package com.baobao.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baobao.common.model.CollectionModel;
import com.baobao.common.model.InformationModel;
import com.baobao.common.model.NewsInfoModel;
import com.baobao.common.model.ResultModel;
import com.baobao.common.model.isSuccess;
import com.baobao.common.service.CollectionService;

/**
 * 收藏
 * @author adminq
 *
 */
@Controller
@RequestMapping("/collection")
public class CollectionController {

	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private CollectionService collectionService;
	
	/**
	 * <p>用户新增收藏</p>
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping("/collectionInformation.app")
	@ResponseBody
	public ResultModel<Object> collectionInformation(HttpServletRequest request,Integer id){
		log.info("collectionInformation新增收藏方法参数:",id);
		ResultModel<Object> model = new ResultModel<Object>(); // 返回的结果集
		model.setData(null);
		model.setInfo(0);
		model.setMessage("");
		try{
			// 获得session
			HttpSession session = request.getSession();
			Integer userId = (Integer)session.getAttribute("userId");
			if(userId!=null){
			collectionService.addCollection(userId,id);
			model.setStatus(true);
			}
		}catch(Exception e){
			model.setStatus(false);
			e.printStackTrace();
			log.error("collectionInformation新增收藏方法报错!",e);
		}
		return model;
	}
	/**
	 * 将资讯收藏取消
	 */
	@RequestMapping("/collectionRemoveInformation.app")
	@ResponseBody
	public ResultModel<Object> collectionRemoveInformation(HttpServletRequest request,Integer id){
		log.info("collectionRemoveInformation取消收藏方法参数:",id);
		ResultModel<Object> model = new ResultModel<Object>();
		model.setData(null);
		model.setInfo(0);
		model.setMessage("");
		try{
			
			Integer userId = (Integer) request.getSession().getAttribute("userId");
			if(userId!=null){
			collectionService.cancelCollection(userId, id);
			model.setStatus(true);
			}
		}catch(Exception e){
			model.setStatus(false);
			e.printStackTrace();
			log.error("collectionRemoveInformation取消收藏方法报错!",e);
		}
		return model;
		
	}
	/**
	 * <p>获取当前用户学习收藏数据</p>
	 * @param request
	 * @param page
	 * @return
	 */
	@RequestMapping("/getStudyCollection.app")
	@ResponseBody
	public ResultModel<List<InformationModel>> getStudyCollection(HttpServletRequest request,Integer page){
		log.info("getStudyCollection获取学习收藏数据方法参数:",page);
		ResultModel<List<InformationModel>> model = new ResultModel<List<InformationModel>>(); // 返回的结果集
		List<InformationModel> newsList = new ArrayList<InformationModel>(); // 新闻集合
		model.setData(null);
		model.setInfo(0);
		model.setMessage("");
		try{
			// 获得session
			HttpSession session = request.getSession();
			Integer userId = (Integer)session.getAttribute("userId");
			if(userId!=null){
			if (page == null || userId == null) {
				model.setMessage("page参数为null或session信息不存在!");
				model.setStatus(false);
				return model;
			}
			newsList=collectionService.getStudyCollection(userId,page);
			model.setData(newsList);
			model.setStatus(true);
			}
		}catch(Exception e){
			model.setStatus(false);
			e.printStackTrace();
			log.error("getStudyCollection获取学习收藏数据方法报错!",e);
		}
		return model;
	}
	@RequestMapping("/isOnCollection.app")
	@ResponseBody
	public Object isOnCollection(HttpServletRequest request,Integer id){
		ResultModel<Boolean> cmodel = new ResultModel<Boolean>();
		boolean flag = false;
		cmodel.setData(flag);
		cmodel.setInfo(0);
		cmodel.setMessage("");
		try {
			Integer userId = (Integer) request.getSession().getAttribute("userId");
			if(userId!=null){
			flag = this.collectionService.isCollected(userId, id);
			cmodel.setData(flag);
			if (flag ==true){
			cmodel.setStatus(true);
			}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.error("isOnCollection判断当前新闻存在方法报错!",e);
		}
		return cmodel;
		
	}


	/*
	 * 修改收藏状态
	 */
	@RequestMapping("/collect")
	@ResponseBody
	public Object collect(Integer actId){
		ResultModel resultModel = new ResultModel();
		try {
			if (collectionService.updateCollection(actId)) {
				resultModel.setData(collectionService.getCollectNumber(actId));
				resultModel.setStatus(true);
				resultModel.setMessage("修改成功");
			}else {
				resultModel.setStatus(false);
				resultModel.setMessage("修改失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
			resultModel.setStatus(false);
			resultModel.setMessage("修改异常");
		}
		return resultModel;
	}

}
