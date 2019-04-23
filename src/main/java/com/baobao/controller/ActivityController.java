/*******************************************************************************
 * Project : hbjiandang
 * Create on 2017年9月4日 上午8:38:27
 * Copyright (c) 2014 - 2016 粮达网
 * 注意：本内容仅限于粮达网内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
package com.baobao.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.baobao.common.cmd.ActivityCondition;
import com.baobao.common.model.ActPushRecModel;
import com.baobao.common.model.ActivityMobile;
import com.baobao.common.model.CommentModel;
import com.baobao.common.model.ResultModel;
import com.baobao.common.service.ActPushService;
import com.baobao.common.service.ActivityService;
import com.baobao.util.ActivityState;
import com.baobao.util.StringDeal;
import com.github.pagehelper.Page;

/**
 * <P>TODO</P>
 * @author 夏思明
 * @date 2017年9月4日 上午8:38:27
 */
@Controller
@RequestMapping("/activity")
public class ActivityController {
	@Autowired
	private ActivityService activityService;
	@Autowired
	private ActPushService actPushService;
	/**
	 * 新建活动
	 * <p>TODO</p>
	 * @param request
	 * @param activity
	 * @return
	 * @author 夏思明
	 */
	@RequestMapping("/createActivity.app")
	@ResponseBody
	public Object createActivity(HttpServletRequest request,ActivityMobile activity,Integer[] participants,Integer publishStatus){
		ResultModel<ActivityMobile> result = new ResultModel<ActivityMobile>();
		result.setData(null);
		result.setInfo(0);
		result.setMessage("");
		try {
		
			HttpSession session = request.getSession();
			Integer userId = (Integer)session.getAttribute("userId");
			activity.setDescribe(StringDeal.removeNonBmpUnicode(activity.getDescribe()));
			if(userId!=null&&publishStatus!=null){
				List<Integer> arrayList = Arrays.asList(participants);
			this.activityService.insertActivity(activity,userId,publishStatus,arrayList);
			result.setStatus(true);
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result.setStatus(false);
		}
		return result;
		
	}
	
	/**
	 * 新建活动
	 * <p>TODO</p>
	 * @param request
	 * @param activity
	 * @return
	 * @author 夏思明
	 */
	@RequestMapping("/createActivity_ios.app")
	@ResponseBody
	public Object createActivity_ios(HttpServletRequest request,ActivityMobile activity,String participants,Integer publishStatus){
		ResultModel<ActivityMobile> result = new ResultModel<ActivityMobile>();
		result.setData(null);
		result.setInfo(0);
		result.setMessage("");
		try {
		
			HttpSession session = request.getSession();
			Integer userId = (Integer)session.getAttribute("userId");
			activity.setDescribe(StringDeal.removeNonBmpUnicode(activity.getDescribe()));
			if(userId!=null&&publishStatus!=null){
				String[] array = participants.split(",");
				List<Integer> arrayList = new ArrayList<Integer>();
				for (String string : array) {
				arrayList.add(Integer.valueOf(string));	
				}
				
			this.activityService.insertActivity(activity,userId,publishStatus,arrayList);
			result.setStatus(true);
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result.setStatus(false);
		}
		return result;
		
	}
	
	
	
	
	/**
	 * 根据状态获取活动详情
	 */
	@RequestMapping("/getActivitysByState.app")
	@ResponseBody
	public Object getActivitysByState(HttpServletRequest request,Integer page,int state){
		ResultModel<List<ActivityMobile>> result = new ResultModel<List<ActivityMobile>>();
		result.setData(null);
		result.setInfo(0);
		result.setMessage("");
		
		try {
			ActivityCondition actCondition = new ActivityCondition();
			
			if(state==ActivityState.UNSTART_ACTIVITY){//未开始
				actCondition.setState(0);
			}else if(state==ActivityState.UNDERWAY_ACTIVITY){//进行中
				actCondition.setState(1);
			}else if(state==(ActivityState.COMPLETED_ACTIVITY|ActivityState.ABANDON_ACTIVITY)){//已完成和放弃
				actCondition.setState(2);
			}
			HttpSession session = request.getSession();
			Integer userId = (Integer)session.getAttribute("userId");
			if(userId!=null){
			if(page!=null&&page>0){
				actCondition.setPage(page);
			}
			List<ActivityMobile> pages = this.activityService.getActivityByState(actCondition,userId);
			result.setData(pages);
			result.setStatus(true);
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result.setStatus(false);
		}
		return result;
		
	}
	/**
	 * 修改活动状态
	 * <p>TODO</p>
	 * @param request
	 * @param id
	 * @param state
	 * @return
	 * @author 夏思明
	 */
	@RequestMapping("/modifyActivityState.app")
	@ResponseBody
	public Object modifyActivityState(HttpServletRequest request,Integer id,Integer state){
		ResultModel<Page<ActivityMobile>> result = new ResultModel<Page<ActivityMobile>>();
		result.setData(null);
		result.setInfo(0);
		result.setMessage("");
		try {
			HttpSession session = request.getSession();
			Integer userId = (Integer)session.getAttribute("userId");
			if(state==ActivityState.UNSTART_ACTIVITY){//未开始
				state=0;
			}else if(state==ActivityState.UNDERWAY_ACTIVITY){//进行中
				state=1;
			}else if(state==ActivityState.COMPLETED_ACTIVITY){//已完成和放弃
				state=2;
			}else if(state==ActivityState.ABANDON_ACTIVITY){
				state = 3;
			}
			if(userId!=null){
			this.activityService.updateActivityState(userId, id, state);
			result.setStatus(true);
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result.setStatus(false);
		}
		return result;
	}
	/**
	 * 提交报告
	 * <p>TODO</p>
	 * @param request
	 * @param cmd
	 * @return
	 * @author 夏思明
	 */
	@RequestMapping("/submitActivityReports.app")
	@ResponseBody
	public Object submitActivityReports(HttpServletRequest request,ActivityCondition cmd){
		ResultModel<ActivityMobile> result = new ResultModel<ActivityMobile>();
		result.setData(null);
		result.setInfo(0);
		result.setMessage("");
		try {
			HttpSession session = request.getSession();
			Integer userId = (Integer)session.getAttribute("userId");
			if(userId!=null){
			if(cmd.getType()==1){
			cmd.setActRepottime(new Date());
			}
			cmd.setActMemberId(userId);
			this.activityService.submitReport(cmd);
			result.setStatus(true);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result.setStatus(false);
		}
		return result;
		
	}
	@RequestMapping("/getActivityReports.app")
	@ResponseBody
	public  Object getActivityReports(HttpServletRequest request,ActivityCondition cmd,Integer userId){
		ResultModel<String> result = new ResultModel<String>();
		result.setData(null);
		result.setInfo(0);
		result.setMessage("");
		try {
			
			if(userId!=null){
			cmd.setActMemberId(userId);
			String rep = this.activityService.getReports(cmd);
			result.setData(rep);
			result.setStatus(true);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result.setStatus(false);
		}
		return result;
		
	}
	@RequestMapping("/submitActivityComment.app")
	@ResponseBody
	public Object submitActivityComment(HttpServletRequest request,ActivityCondition cmd,Integer userId){
		ResultModel<String> result = new ResultModel<String>();
		result.setData(null);
		result.setInfo(0);
		result.setMessage("");
		try {
			HttpSession session = request.getSession();
			Integer userId2 = (Integer)session.getAttribute("userId");
			cmd.setActMemberId(userId);
			if(userId2!=null){
				cmd.setActComId(userId2);
				this.activityService.submitComment(cmd);
				result.setStatus(true);
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result.setStatus(false);
		}
		
		return result;
		
	}
	@RequestMapping("/getActivityComment.app")
	@ResponseBody
	public Object getActivityComment(HttpServletRequest request,Integer userId,Integer id){
		ResultModel<CommentModel> result = new ResultModel<CommentModel>();
		result.setData(null);
		result.setInfo(0);
		result.setMessage("");
		try {

			HttpSession session = request.getSession();
			Integer userId2 = (Integer)session.getAttribute("userId");
			if(userId2!=null){
			CommentModel cmodel = this.activityService.getComments(userId,id);
			result.setData(cmodel);
			result.setStatus(true);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result.setStatus(false);
		}
		return result;
		
	}
	@RequestMapping("/getActivityParticipantsList.app")
	@ResponseBody
	public Object getActivityParticipantsList(HttpServletRequest request,Integer id){
		
		Map<String, Object> map = new HashMap<String, Object>();
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		ResultModel<List<Map<String, Object>>> result = new ResultModel<List<Map<String, Object>>>();
		result.setData(null);
		result.setInfo(0);
		result.setMessage("");
		try {
			list = this.activityService.getPartMemberList(id);
			result.setData(list);
			result.setStatus(true);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result.setStatus(false);
		}
		return result;	
	}
	@RequestMapping("/openPushAct.htm")
	@ResponseBody
	public ModelAndView openPushAct(HttpServletRequest request,ActPushRecModel actPush){
		List<Map<Integer,String>> result = new ArrayList<Map<Integer,String>>();
		ModelAndView modelAndView = new ModelAndView("dialog/ma_add");
		Integer uId = (Integer) request.getSession().getAttribute("userId");
		try {
			if(uId!=null){
			result = activityService.getBranch(uId);
			modelAndView.addObject("result", result);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return modelAndView;
		
	}
	/**
	 * 点击发布按钮发布活动
	 * @param args
	 */
	@RequestMapping("/publishActivities.app")
	@ResponseBody
	public Object publishActivities(HttpServletRequest request,ActivityMobile acMoblie,Integer[] participants){
		ResultModel<Object> result = new ResultModel<Object>();
		result.setData(null);
		result.setInfo(0);
		result.setMessage("");
		result.setStatus(false);
		try {
			Integer uid = (Integer) request.getSession().getAttribute("userId");
			acMoblie.setDescribe(StringDeal.removeNonBmpUnicode(acMoblie.getDescribe()));
			List<Integer> arrayList = Arrays.asList(participants);
			if(uid!=null){
			boolean flag = this.activityService.publishActivities(acMoblie, arrayList,uid);
			result.setStatus(flag);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
		
	}
	
	/**
	 * 点击发布按钮发布活动
	 * @param args
	 */
	@RequestMapping("/publishActivities_ios.app")
	@ResponseBody
	public Object publishActivities_ios(HttpServletRequest request,ActivityMobile acMoblie,String participants){
		ResultModel<Object> result = new ResultModel<Object>();
		result.setData(null);
		result.setInfo(0);
		result.setMessage("");
		result.setStatus(false);
		try {
			Integer uid = (Integer) request.getSession().getAttribute("userId");
			acMoblie.setDescribe(StringDeal.removeNonBmpUnicode(acMoblie.getDescribe()));
			String[] array = participants.split(",");
			List<Integer> arrayList = new ArrayList<Integer>();
			for (String string : array) {
			arrayList.add(Integer.valueOf(string));	
			}
			if(uid!=null){
			boolean flag = this.activityService.publishActivities(acMoblie, arrayList,uid);
			result.setStatus(flag);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
		
	}
	
	
	@RequestMapping("/LookActDetails.htm")
	public ModelAndView LookActDetails(HttpServletRequest request,Integer actId){
		ModelAndView modelAndView = new ModelAndView("ma_detail");
		Integer uid = (Integer) request.getSession().getAttribute("userId");
		Map<String, Object> hashMap = new HashMap<String, Object>();
		if(uid!=null&&actId!=null){
			hashMap = this.activityService.getActDetails(actId, uid);
			modelAndView.addObject("result", hashMap);
		}
		return modelAndView;
		
	}
	@RequestMapping("/deleteActivity.htm")
	@ResponseBody
	public int deleteActivity(HttpServletRequest request,Integer actId){
		int i = 0;
		Integer uid = (Integer) request.getSession().getAttribute("userId");
		if(uid!=null&&actId!=null){
		i = this.actPushService.deleteActivityByRole(uid, actId);
		}
		return i;
		
	}
	@RequestMapping("/deleteActivityBatch.htm")
	@ResponseBody
	public int deleteActivityBatch(HttpServletRequest request,Integer[] Ids){
		
		Integer i = 0;
		Integer uid = (Integer) request.getSession().getAttribute("userId");
		if(uid!=null&&Ids!=null){
		i = this.actPushService.deleteBatch(uid, Ids);
		}
		return i;
		
	}
	
}
