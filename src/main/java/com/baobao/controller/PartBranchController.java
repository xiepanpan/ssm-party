package com.baobao.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baobao.common.mapping.MemberInfoModelMapper;
import com.baobao.common.model.PartyBranchModel;
import com.baobao.common.service.PartyBranchService;


@Controller
@RequestMapping("/partyBranch")
public class PartBranchController {
	/*@Autowired
	private PartyService partyService;
	
	@RequestMapping("/partyShow.htm")
	public ModelAndView partyShow(HttpServletRequest request,String searchValue){
		ModelAndView modelAndView = new ModelAndView("departBranch");
		List<PartyBranchModel> bModel = new ArrayList<PartyBranchModel>();
		try {
			bModel = this.partyService.selectListBySearch(searchValue);
			if(bModel!=null&&bModel.size()>0){
				modelAndView.addObject("result", bModel);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return modelAndView;
		
	}
	@RequestMapping("/addPartBranch.htm")
	@ResponseBody
	public int addPartBranch(HttpServletRequest request,PartyBranchModel pModel){
		int i = 0;
		try {
			i = this.partyService.insertPartBranch(pModel);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return i;
		
	}
	@RequestMapping("/updatePartBranch")
	@ResponseBody
	public int updatePartBranch(HttpServletRequest request,PartyBranchModel pModel){
		int i = 0;
		try {
			i = this.partyService.updatePartBranch(pModel);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return i;
		
	}*/
	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private  PartyBranchService partyBranchService;
	
	@Autowired
	private HttpServletRequest request;
	
	@Autowired
	private MemberInfoModelMapper memberInfoModelMapper;
	
	@RequestMapping("/editPartyBranch.htm")
	@ResponseBody
	public Object editPartyBranch(PartyBranchModel partyBranchModel){
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			map = partyBranchService.editPartyBranch(partyBranchModel);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	
	/*@RequestMapping("/getPartyBranch")
	public ModelAndView getPartyBranch(){
		ModelAndView mav = new ModelAndView();
		try {
			Integer userId=(Integer) request.getSession().getAttribute("userId");
			MemberInfoModel miModel=memberInfoModelMapper.getUserInfoByUserId(userId);
			if (StringUtils.equals("1", miModel.getBranchId())) {
				List<PartyBranchModel> list = partyBranchService.getPartyBranchModels();
				mav.addObject("list",list);
			}else {
				List<PartyBranchModel> list = partyBranchService.getPartyFaculty();
				mav.addObject("list",list);
			}
			mav.setViewName("branchManager");
		} catch (Exception e) {
			e.printStackTrace();
			log.error("获取党分支失败");
		}
		return mav;
	}*/
	@RequestMapping("/getPartyBranchById.htm")
	@ResponseBody
	public PartyBranchModel getPartyBranchById(Integer partyId){
		return partyBranchService.getPartyById(partyId);
	}
	
	@RequestMapping("/deletePartyBranchById.htm")
	@ResponseBody
	public Integer deletePartyBranchById(Integer partyId){
		return partyBranchService.deleteById(partyId);
	}
	@RequestMapping("/deleteBatch.htm")
	@ResponseBody
	public Object deleteBatch(HttpServletRequest request,Integer[] Ids){
		
		return this.partyBranchService.deleteBranchBatch(Ids);
	}
	
}
