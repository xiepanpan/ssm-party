/*******************************************************************************
 * Project : hbjiandang
 * Create on 2017年8月22日 下午2:28:02
 * Copyright (c) 2014 - 2016 粮达网
 * 注意：本内容仅限于粮达网内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
package com.baobao.controller;

import io.netty.handler.codec.http.HttpRequest;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.ws.RequestWrapper;

import jxl.read.biff.Record;
import net.sf.ezmorph.object.DateMorpher;


import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.util.JSONUtils;

import org.apache.commons.io.IOUtils;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.joda.time.Minutes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import cn.jmessage.api.group.CreateGroupResult;
import cn.jmessage.api.group.GroupClient;
import cn.jmessage.api.group.GroupInfoResult;
import cn.jmessage.api.group.MemberListResult;

import com.baobao.common.cmd.PartyCmd;
import com.baobao.common.cmd.StudyRecord;
import com.baobao.common.cmd.TimeRecordModel;
import com.baobao.common.mapping.BranchInfoModelMapper;
import com.baobao.common.mapping.GroupInfoModelMapper;
import com.baobao.common.mapping.MemRoleModelMapper;
import com.baobao.common.mapping.MemberInfoModelMapper;
import com.baobao.common.mapping.StudyRecordModelMapper;
import com.baobao.common.model.BranchInfoModel;
import com.baobao.common.model.ContributionRecordModel;
import com.baobao.common.model.ContributionRecordPageModel;
import com.baobao.common.model.ActivityCountModel;
import com.baobao.common.model.GroupInfoModel;
import com.baobao.common.model.MemRoleModel;
import com.baobao.common.model.MemberInfoModel;
import com.baobao.common.model.PartyBranchModel;
import com.baobao.common.model.ResultModel;
import com.baobao.common.model.StudyRecordModel;
import com.baobao.common.model.StudyRecordPageModel;
import com.baobao.common.model.TimeRecordIosModel;
import com.baobao.common.model.UserBriefModel;
import com.baobao.common.model.UserModel;
import com.baobao.common.service.MemberService;
import com.baobao.common.service.PartyBranchService;
import com.baobao.common.service.PayService;
import com.baobao.common.service.StudyService;
import com.baobao.util.CommUtils;
import com.baobao.util.DateFormat;
import com.baobao.util.DateMorpherEx;
import com.baobao.util.ExportExcel;
import com.baobao.util.GroupInfo;
import com.baobao.util.PageParamCommand;
import com.baobao.util.StrUtil;
import com.baobao.util.StringDeal;
import com.baobao.util.createGroup;
import com.baobao.util.managerMember;

/**
 * <P>党员管理控制类</P>
 * @author 夏思明
 * @date 2017年8月22日 下午2:28:02
 */
@Controller
@RequestMapping("/memeber")
public class MemeberController {
	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private PayService payService;
	@Autowired
	private MemberService memberService;
	@Autowired
	private StudyRecordModelMapper studyMapper;
	@Autowired
	private StudyService studyService;
	@Autowired
	private MemberInfoModelMapper memberInfoModelMapper;
	@Autowired
	private MemRoleModelMapper memRoleModelMapper;
	@Autowired
	private BranchInfoModelMapper branchInfoModelMapper;
	@Autowired
	private  PartyBranchService partyBranchService;
	@Autowired
	private GroupInfoModelMapper groupMapper;
	/**
	 * 
	 * <p>查询党员列表</p>
	 * @param request
	 * @param 
	 * @return
	 * @author 夏思明
	 */
	@RequestMapping("/showMembers.htm")
	public ModelAndView showMembers(HttpServletRequest request,PageParamCommand cmd){
		ModelAndView modelAndView = new ModelAndView("memberManager");
		ArrayList<MemberInfoModel> result = new ArrayList<MemberInfoModel>();
		cmd.setSearchValue(StrUtil.strTrimSpace(cmd.getSearchValue()));   
		try{
			Integer userId=(Integer) request.getSession().getAttribute("userId");
			MemberInfoModel miModel=memberInfoModelMapper.getUserInfoByUserId(userId);
			if (-1== miModel.getMemberInbranchid()) {
				result = this.memberService.showMembers(cmd);
			}else {
			   MemRoleModel memRoleModel =memRoleModelMapper.selectByMemberId(miModel.getMemberId());
			   PartyBranchModel partyBranchModel = partyBranchService.getPartyById(memRoleModel.getRelationPartBranchId());
				if (partyBranchModel.getPartyBranchType()==0) {
					result = this.memberService.showMembers(cmd);
				}else {
					List<Integer> list = branchInfoModelMapper.getBranchId(memRoleModel.getRelationPartBranchId());
					cmd.setBrIds(list);
					result = this.memberService.showMembersByBId(cmd);
				}
			}
			modelAndView.addObject("result",result);
			modelAndView.addObject("cmd",cmd);
		}catch (Exception e){
			log.error("查询党员列表出错{}",e);
		}
		return modelAndView;	
	}
	/**
	 * <p>重置密码</p>
	 * @param request
	 * @param password
	 * @return
	 */
	@RequestMapping("/resetPassword.app")
	@ResponseBody
	public ResultModel<UserBriefModel> restPassWord(HttpServletRequest request,String password){
		log.info("重置密码方法参数",password);
		ResultModel<UserBriefModel> model = new ResultModel<UserBriefModel>(); // 返回的结果集
		try{
			// 查询
			model=memberService.restPassWord(request, password);
		}catch(Exception e){
			model.setStatus(false);
			e.printStackTrace();
			log.error("重置密码方法报错!",e);
		}
		return model;
	}
	
	/**
	 * <p>获得当前登录用户所属支部下所有人员信息</p>
	 * @param request
	 * @return
	 */
	@RequestMapping("getBranchList.app")
	@ResponseBody
	public ResultModel<List<UserBriefModel>> getBranchList(HttpServletRequest request){
		log.info("MemeberController,获得当前登录用户所属支部下所有人员信息getBranchList方法参数");
		ResultModel<List<UserBriefModel>> model = new ResultModel<List<UserBriefModel>>();
		try{
			// 获得session
			HttpSession session=request.getSession();
			// 获得当前登录用户Id
			Integer userId=(Integer)session.getAttribute("userId");
			// 查询
			model=memberService.getBranchUsersInfoByUserId(userId);
		}catch(Exception e){
			model.setStatus(false);
			e.printStackTrace();
			log.error("MemeberController,获得当前登录用户所属支部下所有人员信息getBranchList方法报错!",e);
		}
		return model;
	}
	
	/**
	 * <p>获取用户详情</p>
	 * @param userId
	 * @return
	 */
	@RequestMapping("getUserDetails.app")
	@ResponseBody
	public ResultModel<UserModel> getUserDetails(HttpServletRequest request,Integer userId){
		log.info("获取用户详情getUserDetails方法参数",userId);
		ResultModel<UserModel> model = new ResultModel<UserModel>();
		try{
			// 获得session
			HttpSession session=request.getSession();
			// 获得当前登录用户Id
			if(userId==null){
				userId=(Integer)session.getAttribute("userId");
			}
			// 查询
			model=memberService.getUserDetails(userId);
		}catch(Exception e){
			model.setStatus(false);
			e.printStackTrace();
			log.error("",e);
		}
		return model;
	}
	
	/**
	 * <p>点击添加党员按钮跳转</p>
	 * @param request
	 * @param 
	 * @return
	 * @author 袁杨柳 (17607177525)
	 */
	@RequestMapping("/jumpAddMember.htm")
	public Object jumpAddMember(){
		ModelAndView mv =new ModelAndView();
		mv.setViewName("dialog/member_add");
		return mv;
	}
	
	/**
	 * <p>添加党员</p>
	 * @param request
	 * @param 
	 * @return
	 * @author 袁杨柳 (17607177525)
	 */
	@RequestMapping("/addMember.htm")
	@ResponseBody
	public int addMember(HttpServletRequest request,MemberInfoModel miModel){
		try{
			/*String idCard=request.getParameter("memberIdcard").trim();  //获取身份证号
			if(null != idCard && !("").equals(idCard)){
				int lenth=6; //截取字符长度
				String memberIdCard=idCard.substring(idCard.length()-lenth,idCard.length()); //身份证后6位
				String md5Pass=CommUtils.MD5("123456");
				miModel.setMemberPass(md5Pass); 
			}*/
			miModel.setMemberPass(CommUtils.MD5("123456"));
			memberService.addMember(miModel);
		}catch (Exception e){
			log.error("添加党员出错{}", e);
			return 0;
		}
		return 1;
	}

	/**
	 *
	 * <p>
	 * 获取支部名称
	 * </p>
	 *
	 * @param request
	 * @param response
	 * @return
	 * @author 袁杨柳(17607177525)
	 */
	@RequestMapping("/getbranchName.htm")
	@ResponseBody
	public Object getbranchName(HttpServletRequest request,
			HttpServletResponse response) {
		List<MemberInfoModel> result = new ArrayList<MemberInfoModel>();
		Integer userId=(Integer) request.getSession().getAttribute("userId");
		MemberInfoModel miModel=memberInfoModelMapper.getUserInfoByUserId(userId);
		if (-1== miModel.getMemberInbranchid()) {
			result = memberService.getbranchName();
		}else {
		   MemRoleModel memRoleModel =memRoleModelMapper.selectByMemberId(miModel.getMemberId());
		   PartyBranchModel partyBranchModel = partyBranchService.getPartyById(memRoleModel.getRelationPartBranchId());
			if (partyBranchModel.getPartyBranchType()==0) {
				result = memberService.getbranchName();
			}else {
				List<Integer> list = branchInfoModelMapper.getBranchId(memRoleModel.getRelationPartBranchId());
				result = this.memberService.getbranchNameByIds(list);
			}
		}
		if (result != null && result.size() != 0) {
			return result;
		} else {
			return null;
		}
	}
	
	/**
	 *
	 * <p>
	 * 查询手机号是否已经被注册
	 * </p>
	 *
	 * @param request
	 * @param response
	 * @return
	 * @author 袁杨柳(17607177525)
	 */
	@RequestMapping("/checkUserPhone.htm")
    @ResponseBody
    public Object checkUserPhone(String memberTel,HttpServletRequest request){
		JSONObject result= memberService.checkUserPhone(memberTel);
		return result;
    }
	
	/**
	 * <p>APP上传用户头像</p>
	 * @param file
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/updateUserIcon.app")
	@ResponseBody
	public ResultModel<String> updateUserIcon(MultipartFile file, HttpServletRequest request) {
		log.info("");
		ResultModel<String> model = new ResultModel<String>();
		try{
			// 获得session
			HttpSession session=request.getSession();
			// 获得当前登陆用户Id
			Integer userId = (Integer)session.getAttribute("userId");
			// 上传头像
			model=memberService.updateUserIcon(file,userId,request);
		}catch(Exception e){
			model.setStatus(false);
			e.printStackTrace();
			log.error("",e);
		}
		return model;
	}
	
	/**
	 * <p>点击修改按钮跳转</p>
	 * @param request
	 * @param 
	 * @return
	 * @author 袁杨柳 (17607177525)
	 */
	@RequestMapping("/jumpEditPage.htm")
	public ModelAndView showEditPage(HttpServletRequest request,Integer memberId){
		ModelAndView mv = new ModelAndView();
		mv.setViewName("dialog/member_edit");
		MemberInfoModel miResult = memberService.selectBranchBymemberId(memberId);
		mv.addObject("result",miResult);
		return mv;
	}
	
	/**
	 * <p>
	 * 修改党员信息
	 * </p>
	 * @param record 入参 
	 * @author 袁杨柳(17607177525)
	 */
	@RequestMapping("/updateMember.htm")
	@ResponseBody
	public int updateMember(HttpServletRequest request,MemberInfoModel record){
		try{
			memberService.updateMember(record);
		}catch(Exception e){
			log.error("修改党员信息出错", e);
			return 0;
		}
		return 1;
	}
	
	/**
	 * 
	 * <p>根据id删除党员信息</p>
	 * @param request
	 * @param memberId
	 * @return
	 * @author 袁杨柳(17607177525)
	 */
	@RequestMapping("/deleteMemberById.htm")
	@ResponseBody
	public int deleteMemberById(HttpServletRequest request,MemberInfoModel record){
		try{
			memberService.updateMember(record);
		}catch(Exception e){
			log.error("删除党员信息出错", e);
			return  0;
		}
		return 1;
	}
	
	/**
	 * 
	 * <p>Excel导入党员信息</p>
	 * @param request
	 * @param memberId
	 * @return
	 * @author 袁杨柳(17607177525)
	 * @throws ParseException 
	 */
	@RequestMapping("/importMemberExcel.htm")
	@ResponseBody
	public Object importMemberExcel(HttpServletRequest request,@RequestParam(value="myfile",required = false)MultipartFile myfile) throws ParseException{
		ModelAndView mv=new ModelAndView();
		JSONObject result = memberService.readExcel(myfile);
		Iterator<String> sIterator = result.keys(); 
		Iterator<Entry<String, String>> iter = result.entrySet().iterator();
		Map.Entry<String,String> entry;
		Object val = null;
		Object key=null;
		int valueint=0;
		while (iter.hasNext()) {
			entry = (Map.Entry<String,String>) iter.next();
			key=entry.getKey(); //得到key值
			if("resultType".equals(key)){
				val= entry.getValue();
				//object转int
				valueint=Integer.parseInt(val.toString());
				break;
			}
		}
		if(valueint != 0){ //判断成功或失败 失败回显
			mv.addObject("result", result);
			mv.setViewName("dialog/importMemberExcel");
		}
		return result ;
	}
	
	/**
	 * <p>当前用户获取学习记录页数据(每天的学习时间统计数据)</p>
	 * @param request
	 * @return
	 */
	/*@RequestMapping("/getStudyRecordPage.app")
	@ResponseBody
	public ResultModel<Object> getStudyRecordPage(HttpServletRequest request){
		log.info("当前用户获取学习记录页数据getStudyRecordPage方法");
		ResultModel<Object> model= new ResultModel<Object>();
		List<StudyRecordModel> stuList = new ArrayList<StudyRecordModel>();
		Map<String,Object> map =new HashMap<String, Object>();
		
		model.setData(null);
		model.setInfo(0); 
		model.setMessage("");
		try{
			HttpSession session= request.getSession();
			Integer userId= (Integer) session.getAttribute("userId");
			if(userId!=null){
			stuList = 	this.memberService.getStuReByUserId(userId);
			int size = stuList.size();
			String[] dateList = new String[size];
			Integer [] timeList = new Integer[size];
			for(int i = size-1;i>=0;i--){
				if(stuList.get(i)!=null){
					if(stuList.get(i).getStuDate()!=null){
					String dateF = DateFormat.getformatDate(stuList.get(i).getStuDate());
					dateList[size-i-1] = dateF;
					}
					if(stuList.get(i).getTodayTime()!=null){
						Integer timeSTu = stuList.get(i).getTodayTime()%60>0?stuList.get(i).getTodayTime()/60+1:stuList.get(i).getTodayTime()/60;
						timeList[size-i-1] = timeSTu;
					}
				}
			}
			Integer tod = stuList.get(0).getTodayTime()%60>0?stuList.get(0).getTodayTime()/60+1:stuList.get(0).getTodayTime()/60;
			map.put("continuityStudyDays", stuList.get(0).getContiniuityStudydays());
			map.put("datesList", dateList);
			map.put("timesList", timeList);
			map.put("todayTime",tod);
			}else{
				map.put("continuityStudyDays", 0);
				map.put("datesList", null);
				map.put("timesList", null);
				map.put("todayTime", 0);
			}
			model.setData(map);
			model.setStatus(true);
		}catch(Exception e){
			model.setStatus(false);
			e.printStackTrace();
			log.error("当前用户获取学习记录页数据getStudyRecordPage方法出错", e);
		}
		return model;
	}*/
	@RequestMapping("/getStudyRecordPage.app")
	@ResponseBody
	public ResultModel<Object> getStudyRecordPage(HttpServletRequest request){
		log.info("当前用户获取学习记录页数据getStudyRecordPage方法");
		ResultModel<Object> model= new ResultModel<Object>();
		StudyRecordPageModel result = new StudyRecordPageModel();
		Map<String,Object> map =new HashMap<String, Object>();
		
		model.setData(null);
		model.setInfo(0); 
		model.setMessage("");
		try{
			HttpSession session= request.getSession();
			Integer userId= (Integer) session.getAttribute("userId");
			if(userId!=null){
			result = this.studyService.getStuPage(userId);
			model.setData(result);
			model.setStatus(true);
			}
		}catch(Exception e){
			model.setStatus(false);
			e.printStackTrace();
			log.error("当前用户获取学习记录页数据getStudyRecordPage方法出错", e);
		}
		return model;
	}
	/**
	 * <p>当前用户获取缴费记录</p>
	 */
	
	@RequestMapping("ContributionRecord.app")
	@ResponseBody
	public ResultModel<Object> contributionRecord(HttpServletRequest request,Integer year){
		log.info("当前用户获取缴费记录ContributionRecord方法");
		ResultModel<Object> model= new ResultModel<Object>();
		List<Map<String,Object>> list= new ArrayList<Map<String,Object>>();
		List<ContributionRecordModel> conList = new ArrayList<ContributionRecordModel>();
		model.setInfo(0); 
		model.setMessage("");
		try{
			HttpSession session= request.getSession();
			Integer userId= (Integer) session.getAttribute("userId");
			if(userId!=null){
			conList = payService.getPayList(userId,year);
			model.setData(conList);
			model.setStatus(true);
			}
		}catch(Exception e){
			model.setStatus(false);
			e.printStackTrace();
			log.error("当前用户获取缴费记录ContributionRecord方法出错", e);
		}
		return model;
	}
	
	@RequestMapping("/ContributionRecordPage.app")
	@ResponseBody
	public ResultModel<ContributionRecordPageModel> ContributionRecordPage(HttpServletRequest request){
		ResultModel<ContributionRecordPageModel> model = new ResultModel<ContributionRecordPageModel>();
		ContributionRecordPageModel neModel = new ContributionRecordPageModel();
		model.setData(null);
		model.setInfo(0); 
		model.setMessage("");
		try {
			HttpSession session= request.getSession();
			Integer userId= (Integer) session.getAttribute("userId");
			if(userId!=null){
				neModel = this.payService.getPayPage(userId);
				model.setData(neModel);
				model.setStatus(true);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			model.setStatus(false);
		}
		return model;
		
	}
	@RequestMapping("/UserDataPage.app")
	@ResponseBody
	public ResultModel<Object> userDataPage(HttpServletRequest request){
		log.info("当前用户获取学习记录页数据getStudyRecordPage方法");
		ResultModel<Object> model= new ResultModel<Object>();
		Map<String,Object> maps = new HashMap<String,Object>();
		Map<String,Object[]> activityStatistics = new HashMap<String, Object[]>();
		List<Map<String,Object>> typeStatistics= new ArrayList<Map<String,Object>>();
		Integer [] newCounts= new Integer []{10,20,30};
		String [] typeName= new String []{"讲座活动","植树活动","志愿者活动"};
		model.setData(null);
		model.setInfo(0); 
		model.setMessage("");
		try{
			HttpSession session= request.getSession();
			Integer userId= (Integer) session.getAttribute("userId");
			List<ActivityCountModel> list = studyService.getStudyRecordById(userId);
			Integer [] counts= new Integer [list.size()];
			String [] months= new String [list.size()];
			for (int i = 0; i < list.size(); i++) {
				counts[i]=list.get(i).getNumber();
				months[i]=list.get(i).getDate();
			}
			activityStatistics.put("counts", counts);
			activityStatistics.put("months", months);
			for(int i=0;i<3;i++){
				Map<String,Object> map = new HashMap<String, Object>();
				map.put("count", newCounts[i]);
				map.put("typeName", typeName[i]);
				typeStatistics.add(map);
			}
			maps.put("activityStatistics", activityStatistics);
			maps.put("typeStatistics", typeStatistics);
			model.setData(maps);
			model.setStatus(true);
		}catch(Exception e){
			model.setStatus(false);
			e.printStackTrace();
			log.error("当前用户获取学习记录页数据getStudyRecordPage方法出错", e);
		}
		return model;
	}
	@RequestMapping("/editPass.app")
	@ResponseBody
	public ResultModel<Object> editPass(HttpServletRequest request,String pass,String newPass){
		ResultModel<Object> model= new ResultModel<Object>();
		model.setData(null);
		model.setInfo(0); 
		model.setMessage("");
		model.setStatus(false);
		Integer userId = (Integer) request.getSession().getAttribute("userId");
		try {
			int i = this.memberService.editPass(userId, pass, newPass);
			if(i>0){
			model.setStatus(true);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			model.setStatus(false);
		}
		return model;
	}
	@RequestMapping("/submitStudyRecord.app")
	@ResponseBody
	public ResultModel<Object> submitStudyRecord(HttpServletRequest request,HttpServletResponse response,StudyRecord stuRecord,Integer informationId) throws IOException{
		ResultModel<Object> model = new ResultModel<Object>();
		model.setData(null);
		model.setInfo(0);
		model.setMessage("");
		try {
		
		  StudyRecord stu = new StudyRecord();
		  Map<String, Object> map = new HashMap<String, Object>();
          int size = request.getContentLength();
          InputStream is = request.getInputStream();
          byte[] reqBodyBytes = readBytes(is, size);
          String res = new String(reqBodyBytes);
          System.out.println(res);
        
          map.put("mTimeRecords", TimeRecordModel.class);
          JSONUtils.getMorpherRegistry().registerMorpher(
                  new DateMorpherEx(new String[] { "yyyy-MM-dd HH:mm:ss",
                          "yyyy-MM-dd", "yyyy-MM-dd't'HH:mm:ss" }, (Date) null));
          JSONObject jsonBean = JSONObject.fromObject(res);
          stu = (StudyRecord) JSONObject.toBean(jsonBean,StudyRecord.class,map);
          response.setContentType("text/html;charset=UTF-8");
          response.setCharacterEncoding("UTF-8");
          response.getOutputStream().write(res.getBytes("utf-8"));
          response.flushBuffer();
		  Integer userId = (Integer) request.getSession().getAttribute("userId");
		  if(userId!=null){
			this.memberService.insertRecordData(stu, userId);
			model.setStatus(true);
		  }
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			model.setStatus(false);
		}
		return model;
	}
	@RequestMapping("/submitStudyRecord_ios.app")
	@ResponseBody
	public ResultModel<Object> submitStudyRecordIos(HttpServletRequest request,HttpServletResponse response,Integer informationId,String json) throws IOException{
		ResultModel<Object> model = new ResultModel<Object>();
		model.setData(null);
		model.setInfo(0);
		model.setMessage("");
		System.out.println(json);
		List<TimeRecordModel> recordModels = new ArrayList<TimeRecordModel>();
		//json.replaceAll("\\", "");
		  List<TimeRecordIosModel> jsonArray = JSONArray.toList(JSONArray.fromObject(json), TimeRecordIosModel.class);
	
		try {
			/*StudyRecord stu = new StudyRecord();
			Map<String, Object> map = new HashMap<String, Object>();
			int size = request.getContentLength();
			InputStream is = request.getInputStream();
			byte[] reqBodyBytes = readBytes(is, size);
			String res = new String(reqBodyBytes);
			System.out.println(res);
			
			map.put("mTimeRecords", TimeRecordModel.class);
			JSONUtils.getMorpherRegistry().registerMorpher(
					new DateMorpherEx(new String[] { "yyyy-MM-dd HH:mm:ss",
							"yyyy-MM-dd", "yyyy-MM-dd't'HH:mm:ss" }, (Date) null));
			JSONObject jsonBean = JSONObject.fromObject(res);
			stu = (StudyRecord) JSONObject.toBean(jsonBean,StudyRecord.class,map);
			response.setContentType("text/html;charset=UTF-8");
			response.setCharacterEncoding("UTF-8");
			res = JSONUtils.valueToString(stuRecord);
			response.getOutputStream().write(res.getBytes("utf-8"));
			response.flushBuffer();*/
			Integer userId = (Integer) request.getSession().getAttribute("userId");
			if(userId!=null){
				this.memberService.insertRecordData(jsonArray, userId);
				model.setStatus(true);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			model.setStatus(false);
		}
		return model;
	}
	  public static final byte[] readBytes(InputStream is, int contentLen) {
          if (contentLen > 0) {
                  int readLen = 0;

                  int readLengthThisTime = 0;

                  byte[] message = new byte[contentLen];

                  try {

                          while (readLen != contentLen) {

                                  readLengthThisTime = is.read(message, readLen, contentLen
                                                  - readLen);

                                  if (readLengthThisTime == -1) {// Should not happen.
                                          break;
                                  }

                                  readLen += readLengthThisTime;
                          }
                          return message;
                  } catch (IOException e) {
                          // Ignore
                          // e.printStackTrace();
                  }
          }

          return new byte[] {};
	  }
	  //测试注册所有人
	  @RequestMapping("/testRegisterAllMembers.htm")
	  @ResponseBody
	  public int testRegisterAllMembers(HttpServletRequest request){
		List<MemberInfoModel> list = new ArrayList<MemberInfoModel>();
		list = this.memberInfoModelMapper.getAllMembers();
		managerMember.registerMembers(list);
		return 0;
	  }
	  //测试创建群组
	  @RequestMapping("/testcreateGroup.htm")
	  @ResponseBody
	  public int testcreateGroup(HttpServletRequest request){
		int branchId = 1;
		List<Integer> brList = new ArrayList<Integer>();
		brList.add(branchId);
		PageParamCommand cmd = new PageParamCommand();
		cmd.setBrIds(brList);
		List<MemberInfoModel> list = new ArrayList<MemberInfoModel>();
		//根据支部ID获取所有人
		list = this.memberInfoModelMapper.selectMembersByBrIdPage(cmd);
		String[] JoinList = new String[list.size()];
		//根据支部ID获取支部的Model
		BranchInfoModel branch = this.branchInfoModelMapper.selectByPrimaryKey(branchId);
		//党支部书记的ID
		Integer brSJId = branch.getBrSeId(); 
		for(int i =0;i<list.size();i++){
			JoinList[i] = StringDeal.getUserIdAboveFour(list.get(i).getMemberId());
		}
		CreateGroupResult result = createGroup.createGroup(String.valueOf("0"+brSJId),"信工第一支部群","备注",JoinList);
		for(int i =0;i< result.getMembers_username().size()+1;i++){
		GroupInfoModel gInfo = new GroupInfoModel();
		if(i==result.getMembers_username().size()){
			gInfo.setGroupId(result.getGid());
			gInfo.setMemUsername(String.valueOf(result.getOwner_username()).replace("\"",""));
		}else{
		gInfo.setGroupId(result.getGid());
		gInfo.setMemUsername(String.valueOf(result.getMembers_username().get(i)).replace("\"",""));
		}
		this.groupMapper.insertSelective(gInfo);
		}
		return 0;
	  }
	  @RequestMapping("/resetPass.htm")
	  @ResponseBody
	  public int resetPass(HttpServletRequest  request,Integer memberId){
		  int i = 0;
		MemberInfoModel model = new MemberInfoModel();
		model.setMemberId(memberId);
		model.setMemberPass(CommUtils.MD5("123456"));
		try {
			 i = this.memberInfoModelMapper.updateByPrimaryKeySelective(model);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return i;
	  }
	  @RequestMapping("/test.htm")
	  @ResponseBody
	  public int test(HttpServletRequest request){
		 List<MemberInfoModel>  list = this.memberInfoModelMapper.selectMembersByPage(null);
		 managerMember.registerMembers(list);
		return 0;
		  
	  }
	  @RequestMapping("/testInsertGroup.htm")
	  @ResponseBody
	  public void testInsertGroup(HttpServletRequest request,Long Id){
		  List<String> list = new ArrayList<String>();
		  list = createGroup.getGroupMembers(Id);
		  for(int i = 0;i<list.size();i++){
			  GroupInfoModel g = new GroupInfoModel();
			  g.setGroupId(Id);
			  g.setMemUsername(list.get(i));
		  this.groupMapper.insertSelective(g);
		  }
	  }
	  @RequestMapping("/deleteMemberByIds.htm")
	  @ResponseBody
	  public int deleteMemberByIds(HttpServletRequest request,Integer[] memberId){
		Integer i = 0;
		try {
			i = this.memberService.deleteByBatch(memberId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return i;
		  
	  }
	  @RequestMapping("/exportExcel.htm")
	  @ResponseBody
	  public void exportExcel(HttpServletRequest request,HttpServletResponse response,Integer[] Ids){
		List<MemberInfoModel> list = new ArrayList<MemberInfoModel>();
		list = this.memberInfoModelMapper.getMembersByIds(Arrays.asList(Ids));
		List<MemberInfoModel> result = new ArrayList<MemberInfoModel>();
		for(MemberInfoModel m:list){
			if(m.getMemberInbranchid()!=null){
			BranchInfoModel mem = this.branchInfoModelMapper.selectByPrimaryKey(m.getMemberInbranchid());
				if(mem!=null&&mem.getBranchName()!=null){
				m.setBranchName(mem.getBranchName());
				}
			}
			result.add(m);
		}
		XSSFWorkbook wb = ExportExcel.buildXSLXExcel(result);
		String contentType = "application/x-msdownload";
  		OutputStream out = null ;  
		  try{
	    		response.setContentType(contentType);
	    		response.setHeader("Content-disposition", String.format("attachment; filename=\"%s\"", 
	    					URLEncoder.encode("党员信息.xlsx", "UTF-8").replaceAll("\\+", "%20")));
	    		out = response.getOutputStream();// 输出流
	    	    if(wb!=null) wb.write(out);
	    	} catch (Exception e) {
	    		log.error("打开输出流出错:{}",e);
			} finally {
				IOUtils.closeQuietly(out);
			}
	  }
	  @RequestMapping("/exportExcelAll.htm")
	  public void exportExcelAll(HttpServletRequest request,HttpServletResponse response,PageParamCommand cmd){
		  Integer id = (Integer) request.getSession().getAttribute("userId");
		  MemberInfoModel mModel = memberInfoModelMapper.getUserInfoByUserId(id);
		  List<MemberInfoModel> result = new ArrayList<MemberInfoModel>();
		  List<MemberInfoModel> list = new ArrayList<MemberInfoModel>();
		  if(-1==mModel.getMemberInbranchid()){
			  list = this.memberService.showMembers(cmd);
		  }else{
			  MemRoleModel mRoleModel = memRoleModelMapper.selectByMemberId(mModel.getMemberId());
			  PartyBranchModel partyBrModel = partyBranchService.getPartyById(mRoleModel.getRelationPartBranchId());
			  if(partyBrModel.getPartyBranchType()==0){
				  list = this.memberService.showMembers(cmd);
			  }else{
				  List<Integer> list2 = branchInfoModelMapper.getBranchId(mRoleModel.getRelationPartBranchId());
				  cmd.setBrIds(list2);
				  list = this.memberService.showMembersByBId(cmd);
			  }
		  }
		  for(MemberInfoModel m:list){
				if(m.getMemberInbranchid()!=null){
				BranchInfoModel mem = this.branchInfoModelMapper.selectByPrimaryKey(m.getMemberInbranchid());
					if(mem!=null&&mem.getBranchName()!=null){
					m.setBranchName(mem.getBranchName());
					}
				}
				result.add(m);
			}
		  XSSFWorkbook wb = ExportExcel.buildXSLXExcel(result);
			String contentType = "application/x-msdownload";
	  		OutputStream out = null ;  
			  try{
		    		response.setContentType(contentType);
		    		response.setHeader("Content-disposition", String.format("attachment; filename=\"%s\"", 
		    					URLEncoder.encode("党员信息.xlsx", "UTF-8").replaceAll("\\+", "%20")));
		    		out = response.getOutputStream();// 输出流
		    	    if(wb!=null) wb.write(out);
		    	} catch (Exception e) {
		    		log.error("打开输出流出错:{}",e);
				} finally {
					IOUtils.closeQuietly(out);
				}
	  }
	  public static void main(String[] args) {
		  
		  System.out.println(System.currentTimeMillis());
		  
		  String json = "[{\"endTime\":\"1527822670652\",\"startTime\":\"1527821773161\"},{\"endTime\":\"1527822670653\",\"startTime\":\"1527822501956\"}]";
		   List<TimeRecordIosModel> jsonArray = JSONArray.toList(JSONArray.fromObject(json), TimeRecordIosModel.class);
		   int countTime = 0;
			  int studyTime = 0;
		   for (TimeRecordIosModel timeRecordIosModel : jsonArray) {
			  SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			
			  try {
				  
					countTime = (int) ((timeRecordIosModel.getEndTime()-timeRecordIosModel.getStartTime())/(60*1000));
					System.out.println(countTime);
					studyTime+=countTime;
				Date date1 = sdf.parse(sdf.format(timeRecordIosModel.getStartTime()));
				Date date2 = sdf.parse(sdf.format(timeRecordIosModel.getEndTime()));
				System.out.println(date1);
				System.out.println(date2);
				
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			  
			System.out.println(timeRecordIosModel);
		}
		   
		   System.out.println(studyTime);
		   //System.out.println(jsonArray);
		  
		 /* GroupClient groupClient = new GroupClient(GroupInfo.getAppKey(),GroupInfo.getMasterSecret());
		   try {
			   
			MemberListResult res = groupClient.getGroupMembers(23308003);
			ArrayList<String> list = new ArrayList<String>();
			for(int i = 0;i<res.getMembers().length;i++){
				list.add(res.getMembers()[i].getUsername());
				System.out.println(res.getMembers()[i].getUsername());
			}
			System.out.println(list.size());
		} catch (APIConnectionException | APIRequestException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	}
}
