package com.baobao.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;






























import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.baobao.common.cmd.BranchCondition;
import com.baobao.common.cmd.PartyCmd;
import com.baobao.common.mapping.BranchInfoModelMapper;
import com.baobao.common.mapping.MemRoleModelMapper;
import com.baobao.common.mapping.MemberInfoModelMapper;
import com.baobao.common.model.BranchInfoModel;
import com.baobao.common.model.MemRoleModel;
import com.baobao.common.model.MemberInfoModel;
import com.baobao.common.model.PartyBranchModel;
import com.baobao.common.service.BranchService;
import com.baobao.common.service.IMService;
import com.baobao.common.service.MemberService;
import com.baobao.common.service.PartyBranchService;
import com.baobao.util.PageParamCommand;
import com.baobao.util.StrUtil;

@Controller
@RequestMapping("/branch")
public class BranchController {

private  final Logger log = LoggerFactory.getLogger(this.getClass());
@Autowired
private BranchInfoModelMapper branchInfoModelMapper;
@Autowired
private MemberInfoModelMapper memberInfoModelMapper;
@Autowired
private BranchService branchService;
@Autowired
private MemberService memberInfoService;
@Autowired
private MemRoleModelMapper memRoleModelMapper;
@Autowired
private  PartyBranchService partyBranchService;
@Autowired
private IMService imService;
@RequestMapping("showBranchList")
public List<BranchInfoModel> showBranchList(BranchCondition cmd){
	log.info("");
	List<BranchInfoModel> results=new ArrayList<BranchInfoModel>();
	try{
		results=branchService.getBranchBypage(cmd);
	}catch(Exception e){
		e.printStackTrace();
		log.error("",e);
	}
	return results;
	
}

/**
 * 查询支部信息
 * @return
 * @author 夏思明
 */
@RequestMapping("/viewBranch.htm")
public ModelAndView  viewBranch(HttpServletRequest request,BranchCondition cmd){
	
	ModelAndView mdoelAndView = new ModelAndView("branchManager");
	try{
	ArrayList<BranchInfoModel> list = this.branchService.getBranchInfoModels(cmd);
	Integer userId=(Integer) request.getSession().getAttribute("userId");
	MemberInfoModel miModel=memberInfoModelMapper.getUserInfoByUserId(userId);
	Integer roleId= 0;
	if (-1== miModel.getMemberInbranchid()) {
		
	}else {
	   MemRoleModel memRoleModel =memRoleModelMapper.selectByMemberId(miModel.getMemberId());
	   PartyBranchModel partyBranchModel = partyBranchService.getPartyById(memRoleModel.getRelationPartBranchId());
		if (partyBranchModel.getPartyBranchType()==0) {
			
		}else {
			roleId=2;
			mdoelAndView.addObject("branchFatherId",memRoleModel.getRelationPartBranchId());
		}
	}
	 mdoelAndView.addObject("roleId",roleId);
	mdoelAndView.addObject("result",list);
	}catch(Exception e){
		log.error("查询出错");
	}
	return mdoelAndView;
	
}
/**
 * 
 * 显示校，院，支部信息
 * @author 袁子龙（15071746320）
 * @param 
 * @return
 * 2017年9月19日
 */
@RequestMapping("/viewBranchByCondition.htm")
public ModelAndView  viewBranchByCondition(HttpServletRequest request,PartyCmd partyCmd,Integer type){
	
	ModelAndView mdoelAndView = new ModelAndView("branchManager");
	partyCmd.setSearchValue(StrUtil.strTrimSpace(partyCmd.getSearchValue()));
	try{
		Integer userId=(Integer) request.getSession().getAttribute("userId");
		MemberInfoModel miModel=memberInfoModelMapper.getUserInfoByUserId(userId);
		List<PartyBranchModel> list  = new ArrayList<>();
		List<BranchInfoModel> list2 = new ArrayList<>();
		Integer roleId= 0;
		Integer adminGrade = 0;//创建群组设置管理员才能创建
		if (-1== miModel.getMemberInbranchid()) {//显示校级
			adminGrade = 1;
			if (partyCmd.getBranchFatherId()==null) {
				list = partyBranchService.getPartyBranchModels(partyCmd);
			}else {
				PartyBranchModel partyBranchModel = partyBranchService.getPartyById(partyCmd.getBranchFatherId());
				if (partyBranchModel.getPartyBranchType()==0) {
					list = partyBranchService.getPartyFaculty(partyCmd);
				}else {
					list2 = branchInfoModelMapper.getBranchInfoModelsByCondition(partyCmd);
					mdoelAndView.setViewName("cademyManager");
				}
			}
			
		}else {//不显示校级管理
		   MemRoleModel memRoleModel =memRoleModelMapper.selectByMemberId(miModel.getMemberId());
		   PartyBranchModel partyBranchModel = partyBranchService.getPartyById(memRoleModel.getRelationPartBranchId());
			if (partyBranchModel.getPartyBranchType()==0) {
				if (partyCmd.getBranchFatherId()==null) {
					list=partyBranchService.getPartyFaculty(partyCmd);
				}else {
					list2 = branchInfoModelMapper.getBranchInfoModelsByCondition(partyCmd);
					mdoelAndView.setViewName("cademyManager");
				}
			}else {
				roleId=2;
				mdoelAndView.addObject("branchFatherId",memRoleModel.getRelationPartBranchId());
				if (partyCmd.getBranchFatherId()==null) {
					partyCmd.setBranchFatherId(partyBranchModel.getPartyId());
				}
				list2 = branchInfoModelMapper.getBranchInfoModelsByCondition(partyCmd);
				mdoelAndView.setViewName("cademyManager");
			}
		}
		if (list.size()!=0) {
			mdoelAndView.addObject("result", list);
		}else {
			mdoelAndView.addObject("result", list2);
		}
		if(partyCmd.getSearchValue()!=null){
			mdoelAndView.addObject("searchValue", partyCmd.getSearchValue());
		}
		mdoelAndView.addObject("roleId",roleId);
		mdoelAndView.addObject("adminGrade", adminGrade);
		if (partyCmd.getBranchFatherId()!=null) {
			mdoelAndView.addObject("branchFatherId",partyCmd.getBranchFatherId());
		}
		
	}catch(Exception e){
		log.error("查询出错");
	}
	return mdoelAndView;
	
}
/**
 * 插入党员
 * @param request
 * @param branInfo
 * @return
 * @author 夏思明
 */
@RequestMapping("/insertBranch.htm")
@ResponseBody
public int insertBranch(HttpServletRequest request,BranchInfoModel branInfo){
	int i = 0;
	try {
		i = this.branchService.insertBranch(branInfo);
	} catch (Exception e) {
		e.printStackTrace();
		log.error("插入出错");
	}
	return i;
	
}
@RequestMapping("/showEditPage.htm")
public ModelAndView showEditPage(HttpServletRequest request,Integer branchId){
	ModelAndView model = new ModelAndView("dialog/branch_edit");
	BranchInfoModel branch  = this.branchInfoModelMapper.selectByPrimaryKey(branchId);
	model.addObject("branchInfo",branch);
	return model;
}
@RequestMapping("/showEditPartyPage.htm")
public ModelAndView showEditPartyPage(HttpServletRequest request,Integer branchId){
	ModelAndView model = new ModelAndView("dialog/org_edit");
	PartyBranchModel partyBranchModel = partyBranchService.getPartyById(branchId);
	Integer uid = (Integer) request.getSession().getAttribute("userId");
	MemberInfoModel mem = this.memberInfoModelMapper.selectByPrimaryKey(uid);
	model.addObject("branchInfo",partyBranchModel);
	model.addObject("result",mem);
	return model;
}
/**
 * 修改信息
 * @param request
 * @param branch
 * @return
 * @author 夏思明
 */
@RequestMapping("/updateBranch.htm")
@ResponseBody
public int updateBranch(HttpServletRequest request,BranchInfoModel branch){
	int i = 0;
	try {
	i =  branchService.updateBranchJob(branch);
	} catch (Exception e) {
		e.printStackTrace();
	}
	return i;
	
}
@RequestMapping("/deleteById.htm")
@ResponseBody
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public int deleteById(HttpServletRequest request,Integer branchId){
	int i = 0;
	try {
	i = this.branchService.deleteById(branchId);
	} catch (Exception e) {
		e.printStackTrace();
	}
	return i;
	
}
@RequestMapping("/importBraInfo.htm")
@ResponseBody
public int importBraInfo(HttpServletRequest request,@RequestParam(value="myfile",required = false)MultipartFile myfile){
	int i = 0 ;
	try{
	i = this.branchService.readExcelBranch(myfile);
	}catch(Exception e){
		log.error("导入信息失败");
	}
	return i ;
	
}
@RequestMapping("/checkBranchName.htm")
@ResponseBody
public Object checkBranchName(HttpServletRequest request,String brname,Integer brId) throws UnsupportedEncodingException{
	int i = 0;
	JSONObject result=new JSONObject();
	try{
	if(brname!=null){
	String branchName =  URLDecoder.decode(brname, "UTF-8"); 
	 i = this.branchInfoModelMapper.getListByName(branchName);
	 if(i>=1){
		 result.put("valid", false);
	 }else{
		  result.put("valid", true);
	 }
	}else if(brId!=null){
		i = this.branchInfoModelMapper.getListById(brId);
		if(i>=1){
			result.put("valid", false);
		}else{
			 result.put("valid", true);
		}
	}
	 System.out.println(22);
	}catch(Exception e){
		log.error("查询出错");
	}
	return result;
	
}
/**
 * 获取党员名称
 * <p>TODO</p>
 * @param request
 * @return
 * @author 夏思明
 */
@RequestMapping("/getMemberName.htm")
@ResponseBody
public Object getMemberName(HttpServletRequest request,Integer brId,Integer parentBrId){
	PageParamCommand cmd = new PageParamCommand();
	List<MemberInfoModel> result = new ArrayList<MemberInfoModel>();
	try {
		Integer userId=(Integer) request.getSession().getAttribute("userId");
		MemberInfoModel miModel=memberInfoModelMapper.getUserInfoByUserId(userId);
		if(brId!=null){
			cmd.setBrId(brId);
		}
		if(parentBrId!=null){
			List<Integer> Ids = this.branchInfoModelMapper.getBranchId(parentBrId);
			if(Ids!=null&&Ids.size()>0){
			cmd.setBrIds(Ids);
			}
		}
		if (-1== miModel.getMemberInbranchid()) {
			result = this.memberInfoModelMapper.getMemberName(cmd);
		}else {
		   MemRoleModel memRoleModel =memRoleModelMapper.selectByMemberId(miModel.getMemberId());
		   PartyBranchModel partyBranchModel = partyBranchService.getPartyById(memRoleModel.getRelationPartBranchId());
			if (partyBranchModel.getPartyBranchType()==0) {
				result = this.memberInfoModelMapper.getMemberName(cmd);
			}else {
				/*List<Integer> list = branchInfoModelMapper.getBranchId(memRoleModel.getRelationPartBranchId());
				cmd.setBrIds(list);
				result = this.memberInfoModelMapper.selectMembersByBrIdPage(cmd);*/
				result = this.memberInfoModelMapper.getMemberName(cmd);
			}
		}
	} catch (Exception e) {
		log.error("查询出错");
		e.printStackTrace();
	}
	return result;
	
}
@RequestMapping("/openAddOrg.htm")
public ModelAndView openAddOrg(HttpServletRequest request){
	ModelAndView modelAndView = new ModelAndView("/dialog/org_add");
	Integer uid = (Integer) request.getSession().getAttribute("userId");
	if(uid!=null){
	MemberInfoModel model = this.memberInfoModelMapper.selectByPrimaryKey(uid);
	modelAndView.addObject("result", model);
	}
	return modelAndView;
	
}
@RequestMapping("/testCreateGroup.htm")
@ResponseBody
public int testCreateGroup(HttpServletRequest request,Integer id){
	imService.createGroup(id);
	return 1;
}
//对组织支部进行批量删除
@RequestMapping("/deleteBatch.htm")
@ResponseBody
public Object deleteBatch(HttpServletRequest request,Integer[] Ids){
	Map<String, Object>  result =new HashMap<String, Object>();
	try {
		result = this.branchService.deleteBatch(Ids);
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return result;
}
}
