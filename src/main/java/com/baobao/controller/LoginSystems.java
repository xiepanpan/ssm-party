package com.baobao.controller;

import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.baobao.common.cmd.PartyCmd;
import com.baobao.common.mapping.BranchInfoModelMapper;
import com.baobao.common.mapping.MemRoleModelMapper;
import com.baobao.common.mapping.MemberInfoModelMapper;
import com.baobao.common.model.BranchInfoModel;
import com.baobao.common.model.MemRoleModel;
import com.baobao.common.model.MemberInfoModel;
import com.baobao.common.model.PartyBranchModel;
import com.baobao.common.model.ResultModel;
import com.baobao.common.model.UserBriefModel;
import com.baobao.common.model.UserInfoAll;
import com.baobao.common.service.BranchService;
import com.baobao.common.service.MemberService;
import com.baobao.common.service.PartyBranchService;
import com.baobao.common.service.PartyService;
import com.baobao.listener.SysListener;
import com.baobao.util.CommUtils;


/**
 *	用户登录
 */
@Controller
@RequestMapping("/login")
public class LoginSystems {
	@Autowired
	private MemberService memberService;
	private static final Logger log =LoggerFactory.getLogger(LoginSystems.class);
	@Autowired
	private PartyService partService;
	@Autowired
	private  PartyBranchService partyBranchService;
	
	@Autowired
	private HttpServletRequest request;
	
	@Autowired
	private MemberInfoModelMapper memberInfoModelMapper;
	@Autowired
	private MemRoleModelMapper memRoleModelMapper;
	@Autowired
	private BranchInfoModelMapper branchInfoModelMapper;
	
	/**
	 * 用户登录
	 * @param session
	 * @param request
	 * @param mv
	 * @return
	 */
	@RequestMapping("/Login.app")
	@ResponseBody
	public ResultModel<UserInfoAll> Login(HttpServletRequest request) {
		log.info("用户登录");
		HttpSession session=request.getSession();
		ResultModel<UserInfoAll> model = new ResultModel<UserInfoAll>();  // 返回的结果集
		try{
			//获取前台输入用户的值并去掉空格
			String tel = request.getParameter("phone").trim(); 					// 用户电话(账号)
			String password = request.getParameter("password").trim(); 			// 用户密码
			if(tel.isEmpty() && password.isEmpty()){
				//判断用户是否在线
				boolean flag=SysListener.isOnline(session);
				//为true表示用户在线   
				if(flag){
					//拿到在线用户的sessionID
					Integer key=(Integer)session.getAttribute("userId");
					//通过sessionID获取用户的信息
					UserInfoAll data=(UserInfoAll)session.getAttribute(String.valueOf(key));
					model.setData(data);
					model.setInfo(0);
					model.setMessage("");
					model.setStatus(true);
					return model;
				}
			}
			
			// 查询
			model = memberService.login(tel, password);
			// 帐号密码正确时就判断当前账号是否在线，如在线则不允许登陆，不在线将用户信息封装进session，并保存session
			if ("Success".equals(model.getMessage())) {
				//model.setStatus(true);
				session.setAttribute(String.valueOf(model.getData().getUserBrief().getId()),model.getData()); // key:用户Id value:用户信息
				session.setAttribute("userId", model.getData().getUserBrief().getId()); // value:用户Id
				SysListener.isAlreadyEnter(session,tel);
				/*// 存放当前用户登录状态
				SysListener.isAlreadyEnter(session,tel);
				boolean flag=SysListener.isOnline(tel);
				if(flag){
					model.setInfo(0);
					model.setMessage("当前用户已在线");
					model.setStatus(false);
				}else{
					session.setAttribute(String.valueOf(model.getData().getId()),model.getData()); // key:用户Id value:用户信息
					session.setAttribute("userId", model.getData().getId()); // value:用户Id
					// 存放当前用户登录状态
					SysListener.isAlreadyEnter(session,tel);
				}*/
				
			}
		}catch (Exception e){
			e.printStackTrace();
			log.error("用户登录出错", e);
		}
		return model;
	}
	
	/**
	 * <p>退出登录</p>
	 * @param request
	 * @return
	 */
	@RequestMapping("signOut.app")
	@ResponseBody
	public ResultModel<UserBriefModel> signOut(HttpServletRequest request){
		log.info("退出登录");
		ResultModel<UserBriefModel> model = new ResultModel<UserBriefModel>(); // 返回的结果集
		model.setData(null);
		model.setInfo(0);
		model.setMessage("");
		HttpSession session=null;
		try{
			// 获得session
			session=request.getSession();
			// 移除当前用户Id
			session.removeAttribute("userId");
			SysListener.signOut(session);
			// 使session失效
			session.invalidate();
			model.setStatus(true);  // true  退出成功
		}catch(Exception e){
			e.printStackTrace();
			log.error("退出登录报错!");
			model.setStatus(false); // false 退出异常
		}
		return model;
	}
	
	@RequestMapping("goIndex.htm")
	@ResponseBody
	public Object goIndex(HttpServletRequest request){
		log.info("跳转后台主界面");
		ModelAndView mv=new ModelAndView();
		try{
			Integer userId=(Integer) request.getSession().getAttribute("userId");
			if(userId!=null){
			MemberInfoModel miModel=memberInfoModelMapper.getUserInfoByUserId(userId);
			List<PartyBranchModel> list  = new ArrayList<>();
			List<BranchInfoModel> list2 = new ArrayList<>();
			PartyCmd partyCmd = new PartyCmd();
			Integer roleId= 0;
			if (-1== miModel.getMemberInbranchid()) {
				list = partyBranchService.getPartyBranchModels(partyCmd);
			}else {
			   MemRoleModel memRoleModel =memRoleModelMapper.selectByMemberId(miModel.getMemberId());
			   PartyBranchModel partyBranchModel = partyBranchService.getPartyById(memRoleModel.getRelationPartBranchId());
				if (partyBranchModel.getPartyBranchType()==0) {
					roleId = 3;
					list=partyBranchService.getPartyFaculty(partyCmd);
				}else {
					roleId=2;
					list2 = branchInfoModelMapper.getBranchInfoModels(memRoleModel.getRelationPartBranchId());
				}
			}
			if (list.size()!=0) {
				mv.addObject("result", list);
			}else {
				mv.addObject("result", list2);
			}
			mv.addObject("roleId",roleId);
			mv.addObject("name",miModel.getMemberName());
			mv.setViewName("index");
			}
		}catch(Exception e){
			e.printStackTrace();
			log.error("跳转后台主界面报错!");
		}
		return mv;
	}
	
	/**
	 * <p>获取当前session中的</p>
	 * @param request
	 * @return
	 */
	@RequestMapping("getUserIdFromSession.htm")
	@ResponseBody
	public Object getUserIdFromSession(HttpServletRequest request){
		log.info("获得当前session中的userId方法参数",request);
		JSONObject json = new JSONObject();
		json.put("status", true);
		try{
			// 获得session
			HttpSession session=request.getSession();
			// 获得userId
			Integer userId = (Integer)session.getAttribute("userId");
			json.put("userId", userId);
		}catch(Exception e){
			json.put("status", false);
			e.printStackTrace();
			log.error("getUserIdFromSession方法出错!",e);
		}
		return json;
	}
	
	/**
	 * 用户登录
	 * @param session
	 * @param request
	 * @param mv
	 * @return
	 */
	@RequestMapping("/Login.htm")
	@ResponseBody
	public ResultModel<UserBriefModel> WebLogin(HttpServletRequest request) {
		log.info("用户登录");
		HttpSession session=request.getSession();
		ResultModel<UserBriefModel> model = new ResultModel<UserBriefModel>();  // 返回的结果集
		try{
			//获取前台输入用户的值并去掉空格
			String tel = request.getParameter("phone").trim(); 					// 用户电话(账号)
			String password = request.getParameter("password").trim(); 			// 用户密码
			password=CommUtils.MD5(password);
			if(tel.isEmpty() && password.isEmpty()){
				boolean flag=SysListener.isOnline(session);
				if(flag){
					Integer key=(Integer)session.getAttribute("userId");
					UserBriefModel data=(UserBriefModel)session.getAttribute(String.valueOf(key));
					model.setData(data);
					model.setInfo(0);
					model.setMessage("");
					model.setStatus(true);
					return model;
				}
			}
			// 查询
			model = memberService.webLogin(tel, password);
			// 帐号密码正确时就判断当前账号是否在线，如在线则不允许登陆，不在线将用户信息封装进session，并保存session
			if ("Success".equals(model.getMessage())) {
				session.setAttribute(String.valueOf(model.getData().getId()),model.getData()); // key:用户Id value:用户信息
				session.setAttribute("userId", model.getData().getId()); // value:用户Id
				// 存放当前用户登录状态
				SysListener.isAlreadyEnter(session,tel);
				/*boolean flag=SysListener.isOnline(tel);
				if(flag){
					model.setInfo(0);
					model.setMessage("当前用户已在线");
					model.setStatus(false);
				}else{
					session.setAttribute(String.valueOf(model.getData().getId()),model.getData()); // key:用户Id value:用户信息
					session.setAttribute("userId", model.getData().getId()); // value:用户Id
					// 存放当前用户登录状态
					SysListener.isAlreadyEnter(session,tel);
				}*/
			}
		}catch (Exception e){
			e.printStackTrace();
			log.error("用户登录出错", e);
		}
		return model;
	}
	
}  
