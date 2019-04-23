package com.baobao.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.baobao.common.mapping.ActPushRecModelMapper;
import com.baobao.common.mapping.ActivityModelMapper;
import com.baobao.common.model.ActivityMobile;
import com.baobao.common.model.ActivityModel;
import com.baobao.common.model.ResultModel;
import com.baobao.common.service.ActPushService;
import com.baobao.common.service.ActivityService;
import com.baobao.util.GetLatAndLngByBaidu;

@Controller
@RequestMapping("/actPush")
public class ActPushController {
	@Autowired
	private ActivityService activityService;
	@Autowired
	private ActPushService actPushService;
	@Autowired
	private ActivityModelMapper actModelMapper;
	@RequestMapping("/publishActivity.htm")
	@ResponseBody
	public int PublishActivity(HttpServletRequest request,ActivityModel actModel,Integer[] brList){
		int  i = 0;
		Integer uId = (Integer) request.getSession().getAttribute("userId");
		List<Integer> list = new ArrayList<>();
		if(brList!=null&&brList.length>0){
		for (int j = 0; j < brList.length; j++) {
			list.add(brList[j]);
		}
		}
		if(actModel.getLatitude()==null){
			   Map<String, Double> map = GetLatAndLngByBaidu.getLatAndLngByAddress(actModel.getActivityPlace());
			   if(map==null){
				   return -1;
			   }
			   actModel.setLatitude(map.get("lat"));
			   actModel.setLongitude(map.get("lng"));
		}
		try {
			if(uId!=null){
				
		   List<Integer> arrayList = Arrays.asList(brList);
			i = actPushService.publishActivity(actModel, arrayList, uId);
			}
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return i;
	}
	@RequestMapping("/showActivities.htm")
	public ModelAndView showActivities(HttpServletRequest request){
		ModelAndView modelAndView = new ModelAndView("myactive");
		List<ActivityModel> list = new ArrayList<ActivityModel>();
		List<String> actStatus = new ArrayList<String>();
		String status = "未开始";
		Integer uid = (Integer) request.getSession().getAttribute("userId");
		if(uid!=null){
			list = this.actPushService.getActivities(uid);
			for(ActivityModel actModel:list){
				Date startTime = actModel.getActivityStarttime();
				Date endTime = actModel.getActivityEndtime();
				Date currentTime = new Date();
				if(startTime.before(currentTime)){
					status = "未开始";
				}else if(startTime.before(currentTime)&&endTime.after(currentTime)){
					status = "进行中";
				}else if(endTime.before(currentTime)){
					status = "已结束";
				}
				actStatus.add(status);
			}
			modelAndView.addObject("result", list);
			modelAndView.addObject("status",actStatus);
		}
		return modelAndView;
		
	}
	/**
	 * 通过用户ID获取下面的子支部或者成员
	 */
	@RequestMapping("/getChildInfoByRoleId.app")
	@ResponseBody
	public Object getChildInfoByRoleId(HttpServletRequest request){
		ResultModel<List<Map<Integer,String>>> result = new ResultModel<List<Map<Integer,String>>>();
		List<Map<Integer,String>> list = new ArrayList<Map<Integer,String>>();
		Integer uId = (Integer) request.getSession().getAttribute("userId");
		result.setStatus(false);
		try {
			if(uId!=null){
				list =  actPushService.getChildInfo(uId);
				result.setData(list);
				result.setStatus(true);
			}
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return result;
	}
	/**
	 * 查任职的支部活动
	 */
	@RequestMapping("/getActivityByBranch.app")
	@ResponseBody
	public Object getActivityByBranch(HttpServletRequest request,Integer roleBrId,Integer page){
		ResultModel<List<ActivityMobile>> result = new ResultModel<List<ActivityMobile>>();
		result.setData(null);
		result.setStatus(false);
		try {
			Integer uid =(Integer) request.getSession().getAttribute("userId");
			List<ActivityMobile> acModel = new ArrayList<ActivityMobile>();
			if(uid!=null&&page!=null&&page>0){
				acModel = actPushService.getActByManager(uid,roleBrId,page);
				result.setData(acModel);
				result.setStatus(true);
			}
		} catch (Exception e) {
		
			e.printStackTrace();
		}
		return result;
	}
	/**
	 * 根据不同的角色删除
	 * 
	 * @return
	 */
	@RequestMapping("/deleteActivitiesByRole.app")
	@ResponseBody
	public Object deleteActivitiesByRole(HttpServletRequest request,Integer actId){
		ResultModel<String> result = new ResultModel<String>();
		result.setStatus(false);
		Integer uid = (Integer) request.getSession().getAttribute("userId");
		try {
			if(uid!=null&&actId!=null){
				int i = this.actPushService.deleteActivityByRole(uid, actId);
				if(i>0){
					result.setStatus(true);
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
		
	}
	@RequestMapping("/openEditPage.htm")
	public ModelAndView openEditPage(HttpServletRequest request,Integer actId){
		ModelAndView modelAndView = new ModelAndView("dialog/ma_edit");
		List<Map<Integer, String>> result = new ArrayList<Map<Integer,String>>();
	
		ActivityModel actModel = new ActivityModel();
		Integer uid = (Integer) request.getSession().getAttribute("userId");
		if(uid!=null){
		result = activityService.getBranch(uid);
		actModel = actModelMapper.selectByPrimaryKey(actId);
	
		
		if(actModel!=null){
		modelAndView.addObject("result", actModel);
		}
		if(result.size()>0){
			modelAndView.addObject("br",result);
		}
		
		}
		return modelAndView;
		
	}
	@RequestMapping("/getSelectedBranches.htm")
	@ResponseBody
	public Object getSelectedBranches(HttpServletRequest request,Integer actId){
		List<Map<Integer, String>> selectedResult = new ArrayList<Map<Integer,String>>();
		Integer uid = (Integer) request.getSession().getAttribute("userId");
		if(uid!=null){
			selectedResult = actPushService.getSelectedBranches(actId, uid);
		}
		return selectedResult;
	}
	//后台修改活动的信息
	@RequestMapping("/editActivities.htm")
	@ResponseBody
	public int editActivities(HttpServletRequest request,ActivityModel actModel){
		//List<Integer> arrayList = Arrays.asList(brList);
		int i = 0;
		Integer uid = (Integer) request.getSession().getAttribute("userId");
		try {
			Map<String, Double> map = GetLatAndLngByBaidu.getLatAndLngByAddress(actModel.getActivityPlace());
			if(map!=null){
			actModel.setLatitude(map.get("lat"));
		    actModel.setLongitude(map.get("lng"));
			i =	actPushService.updateActivity(actModel);
			}else{
				return -1;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return i;
	}
	//校验地址信息是否有效
	@RequestMapping("/isValidPalce.htm")
	@ResponseBody
	public Object isValidPalce(HttpServletRequest request,String activityPlace){
		boolean flag = false;
		//Map<String, Object> data = new HashMap<String, Object>();
		
		if(activityPlace!=null){
			Map<String, Double> result = GetLatAndLngByBaidu.getLatAndLngByAddress(activityPlace);
			if(result!=null&&result.get("lat")!=null){
				flag = true;
				
			}
		}
		//data.put("valid", flag);
		return flag;
	}
}
