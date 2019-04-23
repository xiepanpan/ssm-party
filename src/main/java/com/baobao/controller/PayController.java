package com.baobao.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.baobao.common.mapping.BranchInfoModelMapper;
import com.baobao.common.mapping.PayInfoModelMapper;
import com.baobao.common.model.BranchInfoModel;
import com.baobao.common.model.MemberInfoModel;
import com.baobao.common.model.PayInfoModel;
import com.baobao.common.service.PayService;

@Controller
@RequestMapping("/pay")
public class PayController {
	@Autowired
	private PayService payService;
	@Autowired
	private PayInfoModelMapper payMapper;
	@Autowired
	private BranchInfoModelMapper brInfoMapper;
	@RequestMapping("/showPay.htm")
	public ModelAndView showPay(HttpServletRequest request){
		ModelAndView modelAndView = new ModelAndView("payInfo");
		try {
			List<PayInfoModel> payList = new ArrayList<PayInfoModel>();
			//payList = payService.getPayList();
			Integer uid = (Integer) request.getSession().getAttribute("userId");
			if(uid!=null){
			payList = this.payService.getPayListByRole(uid);
			}
			if(payList!=null&&payList.size()>0){
				modelAndView.addObject("payList",payList);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return modelAndView;
		
	}
	@RequestMapping("addPayInfo.htm")
	@ResponseBody
	public int addPayInfo(HttpServletRequest request,PayInfoModel payInfo){
		int i = 0;
		try {
			i = payService.addPayInfo(payInfo);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return i;
	}
	@RequestMapping("/getMemberTel.htm")
	@ResponseBody
	public List<String> getMemberTel(HttpServletRequest request,String uname){
		List<String> list = new ArrayList<String>();
		String name = null;
		try {
			
			Integer userId = (Integer) request.getSession().getAttribute("userId");
			if(userId!=null){
			if(uname!=null){
			name = java.net.URLDecoder.decode(uname,"UTF-8");
			}
			list = payService.getTelList(userId,name);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
		
	}
	@RequestMapping("/getMemberName.htm")
	@ResponseBody
	public List<String> getMemberName(HttpServletRequest request,Integer brId){
		List<String> list = new ArrayList<String>();
		try {
			Integer userId = (Integer) request.getSession().getAttribute("userId");
			if(userId!=null){
			list = payService.getNameList(userId,brId);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	@RequestMapping("/getBrName.htm")
	@ResponseBody
	public List<BranchInfoModel> getBrName(HttpServletRequest request){
		List<BranchInfoModel> list = new ArrayList<BranchInfoModel>();
		try {
			Integer userId = (Integer) request.getSession().getAttribute("userId");
			if(userId!=null){
			list = payService.getBrList(userId);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	@RequestMapping("openPayPage.htm")
	public ModelAndView openPayPage(HttpServletRequest request,Integer payId){
		ModelAndView modelAndView = new ModelAndView("dialog/pay_edit");
		PayInfoModel pay = new PayInfoModel();
		try {
			pay = payMapper.selectByPrimaryKey(payId);
			Integer pId = this.brInfoMapper.getBranchIdsByName(pay.getPayBrName());
			pay.setPayBrId(pId);
			modelAndView.addObject("payInfo",pay);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return modelAndView;
		
	}
	@RequestMapping("/editPayInfo.htm")
	@ResponseBody
	public int editPayInfo(HttpServletRequest request,PayInfoModel payInfo){
		int i = 0;
		try {
			i = this.payService.editPay(payInfo);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return i;
		
	}
	@RequestMapping("/deleteById.htm")
	@ResponseBody
	public int deleteById(HttpServletRequest request,Integer payId){
		int i = 0;
		try {
			i = this.payService.deletePay(payId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return i;
		
	}
	@RequestMapping("/deleteByBatch.htm")
	@ResponseBody
	public int deleteBatch(HttpServletRequest request,Integer[] Ids){
		Integer i = 0;
		i = this.payService.deleteBatch(Ids);
		return i;
		
	}
	//获取对应的支部的姓名和手机号
	@RequestMapping("/getMemberTelName.htm")
	@ResponseBody
	public Object getMemberTelName(HttpServletRequest request,Integer Ids){
		List<Map<String, Object>> result = new ArrayList<Map<String,Object>>();
		try {
			Integer userId = (Integer) request.getSession().getAttribute("userId");
			if(userId!=null){
			//list = payService.getNameList(userId,brId);
				result = payService.getTelNameList(userId, Ids);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	//根据所给的手机号带出支部信息
	@RequestMapping("/getBranchByTel.htm")
	@ResponseBody
	public Object getBranchByTel(HttpServletRequest request,String tel){
		return 	this.payService.getBrId(tel);
	}
	
}
