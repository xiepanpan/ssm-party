package com.baobao.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baobao.common.model.ActivityMobile;
import com.baobao.common.model.AppraisalModel;
import com.baobao.common.model.ResultModel;
import com.baobao.common.service.AppraisalServcie;

@Controller
@RequestMapping("/appraisal")
public class AppraisalController {
	@Autowired
	private AppraisalServcie appraisalServcie;
	
	@RequestMapping("/getAppraisals.app")
	@ResponseBody
	public Object getAppraisals(HttpServletRequest request){
		ResultModel<List<AppraisalModel>> result = new ResultModel<List<AppraisalModel>>();
		result.setData(null);
		result.setInfo(0);
		result.setMessage("");
		try {
			// 获得session
			HttpSession session=request.getSession();
			// 获得当前登陆用户Id
			Integer userId = (Integer)session.getAttribute("userId");
			if(userId!=null){
			List<AppraisalModel> re= this.appraisalServcie.getAppraisalList(userId);
			result.setStatus(true);
			result.setData(re);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			result.setStatus(false);
			e.printStackTrace();
		}
		return result;
		
	}
	@RequestMapping("/createAppraisal.app")
	@ResponseBody
	public Object createAppraisal(HttpServletRequest request,AppraisalModel appModel,Integer userId){
		ResultModel<String> result = new ResultModel<String>();
		result.setData(null);
		result.setInfo(0);
		result.setMessage("");
		try {
			HttpSession session = request.getSession();
			if(userId!=null){
			appModel.setUid(userId);
			}
			this.appraisalServcie.insertAppra(appModel);
			result.setStatus(true);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result.setStatus(false);
		}
		return result;
		
	}
}
