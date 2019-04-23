package com.baobao.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.baobao.common.model.Activity;
import com.baobao.common.model.ActivityType;
import com.baobao.common.model.ResultModel;
import com.baobao.common.model.andshow.ActComment;
import com.baobao.common.model.andshow.ActCount;
import com.baobao.common.model.andshow.ActDetail;
import com.baobao.common.model.andshow.ActList;
import com.baobao.common.model.andshow.FinishedActList;
import com.baobao.common.model.andshow.NewActList;
import com.baobao.common.service.ActService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

@RequestMapping("/act")
@Controller
public class ActController {

	
	
	@Autowired
	private ActService actService;
	
	/*
	 * 获取活动的分类页面
	 */
	@RequestMapping("/getActTypeHtml")
	public ModelAndView getActTypeHtml(){
		ModelAndView mav = new ModelAndView();
		mav.setViewName("actType");
		List<ActivityType> types = actService.getType();
		mav.addObject("types", types);
		mav.setViewName("actType");
		return mav;
	}
	
	/*
	 * 修改活动分类
	 */
	@RequestMapping("/updateType")
	public ModelAndView updateType(Integer id){
		ModelAndView mav = new ModelAndView();
		ActivityType type = actService.getTypeById(id);
		mav.setViewName("dialog/type_edit");
		mav.addObject("type", type);
		return mav;
	}
	
	/*
	 * 删除活动分类
	 */
	@RequestMapping("/deleteById")
	@ResponseBody
	public Object deleteById(Integer id){
		ResultModel resultModel = new ResultModel();
		try {
			if (actService.deleteById(id)) {
				resultModel.setStatus(true);
			}else {
				resultModel.setStatus(false);
			}
		} catch (Exception e) {
			e.printStackTrace();
			resultModel.setStatus(false);
		}
		
		
		return resultModel;
	}
	
	
	/*
	 * 网页添加活动分类
	 */
	@RequestMapping("/saveActType")
	@ResponseBody
	public Object saveActType(ActivityType type){
		ResultModel resultModel = new ResultModel();
		try {
			if (actService.saveActType(type)) {
				resultModel.setStatus(true);
			}else {
				resultModel.setStatus(false);
			}
		} catch (Exception e) {
			e.printStackTrace();
			resultModel.setStatus(false);
		}
		
		
		return resultModel;
	}
	
	
	
	/*
	 * app获取活动分类
	 */
	@RequestMapping("/getActType")
	@ResponseBody
	public Object getActType(){
		ResultModel resultModel = new ResultModel();
		try {
			resultModel.setData(actService.getType());
			resultModel.setMessage("获取活动分类成功");
		} catch (Exception e) {
			e.printStackTrace();
			resultModel.setStatus(false);
			resultModel.setMessage("获取活动分类失败");
		}
		return resultModel;
	}
	
	
	/*
	 * 创建活动
	 */
	@RequestMapping("/createAct")
	@ResponseBody
	public Object createAct(Activity activity, Integer[] typeIds,
			Integer[] userIds, Integer[] fileIds,Integer[] branchIds){
		ResultModel resultModel = new ResultModel();
		try {
			if (actService.createAct(activity, typeIds, userIds, fileIds, branchIds)) {
				resultModel.setMessage("活动创建成功");
				resultModel.setStatus(true);
			}else {
				resultModel.setStatus(false);
				resultModel.setMessage("活动创建失败");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			resultModel.setStatus(false);
			resultModel.setMessage("活动创建异常");
		}
		return resultModel;
	}
	
	/*
	 * 获取进行中的活动
	 */
	@RequestMapping("/getDoingActList")
	@ResponseBody
	public Object getDoingActList(){
		ResultModel<List<NewActList>> resultModel = new ResultModel<List<NewActList>>();
		try {
			resultModel.setData(actService.getDoingActLists());
			resultModel.setMessage("获取数据成功");
			resultModel.setStatus(true);
		} catch (Exception e) {
			e.printStackTrace();
			resultModel.setStatus(false);
			resultModel.setMessage("获取数据异常");
		}
		return resultModel;
	}

	/*
	 * 获取完成的活动
	 */
	@RequestMapping("/getFinActList")
	@ResponseBody
	public Object getFinActList(@RequestParam(defaultValue="1",required=false)Integer pageIndex,Integer typeId){
		ResultModel<List<NewActList>> resultModel = new ResultModel<List<NewActList>>();
		try {
			Page page = PageHelper.startPage(pageIndex, 10);
			 resultModel.setData(actService.getFinshedActLists(typeId));
			 resultModel.setPageIndex(pageIndex);
			 resultModel.setPageTotal(page.getPages());
			 resultModel.setStatus(true);
			 resultModel.setMessage("获取数据成功");
		} catch (Exception e) {
			e.printStackTrace();
			resultModel.setMessage("获取数据异常");
			resultModel.setStatus(false);
		}
		return resultModel;
	}

	/*
	 * 获取评论接口
	 */
	@RequestMapping("/getComment")
	@ResponseBody
	public Object getComment(Integer actId){
		ResultModel<List<ActComment>> resultModel = new ResultModel<List<ActComment>>();
		try {
			resultModel.setData(actService.getActComments(actId));
			resultModel.setMessage("获取数据成功");
			resultModel.setStatus(true);
		} catch (Exception e) {
			e.printStackTrace();
			resultModel.setStatus(false);
			resultModel.setMessage("获取数据异常");
		}
		return resultModel;
	}
	
	/*
	 * 获取活动的详情
	 */
	@RequestMapping("/getActDetail")
	@ResponseBody
	public Object getActDetail(Integer actId){
		ResultModel<ActDetail> resultModel = new ResultModel<ActDetail>();
		try {
			resultModel.setData(actService.getActDetail(actId));
			resultModel.setMessage("获取数据成功");
			resultModel.setStatus(true);
		} catch (Exception e) {
			e.printStackTrace();
			resultModel.setStatus(false);
			resultModel.setMessage("获取数据异常");
		}
		return resultModel;
	}
	
	
	/*
	 * 获取活动的统计
	 */
	@RequestMapping("/getActCount")
	@ResponseBody
	public Object getActCount(){
		ResultModel<List<ActCount>> resultModel = new ResultModel<List<ActCount>>();
		try {
			resultModel.setData(actService.getActCounts());
			resultModel.setMessage("获取数据成功");
			resultModel.setStatus(true);
		} catch (Exception e) {
			e.printStackTrace();
			resultModel.setStatus(false);
			resultModel.setMessage("获取数据异常");
		}
		return resultModel;
	}
}
