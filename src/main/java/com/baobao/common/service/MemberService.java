/*******************************************************************************
 * Project : hbjiandang
 * Create on 2017年8月22日 下午2:34:33
 * Copyright (c) 2014 - 2016 粮达网
 * 注意：本内容仅限于粮达网内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
package com.baobao.common.service;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import com.baobao.common.cmd.StudyRecord;
import com.baobao.common.cmd.TimeRecordModel;
import com.baobao.common.mapping.BranchInfoModelMapper;
import com.baobao.common.mapping.GroupInfoModelMapper;
import com.baobao.common.mapping.MemRoleModelMapper;
import com.baobao.common.mapping.MemberInfoModelMapper;
import com.baobao.common.mapping.SequenceModelMapper;
import com.baobao.common.mapping.StudyRecordModelMapper;
import com.baobao.common.model.BranchInfoModel;
import com.baobao.common.model.MemRoleModel;
import com.baobao.common.model.MemberInfoModel;
import com.baobao.common.model.ResultModel;
import com.baobao.common.model.StudyRecordModel;
import com.baobao.common.model.TimeRecordIosModel;
import com.baobao.common.model.UserBriefModel;
import com.baobao.common.model.UserInfoAll;
import com.baobao.common.model.UserModel;
import com.baobao.util.CommUtils;
import com.baobao.util.GetLocalIp;
import com.baobao.util.MathUtil;
import com.baobao.util.MemberJobType;
import com.baobao.util.MemberType;
import com.baobao.util.PageParamCommand;
import com.baobao.util.ReadExcel;
import com.baobao.util.StrUtil;
import com.baobao.util.StringDeal;
import com.baobao.util.UploadImage;
import com.baobao.util.managerMember;

/**
 * @author 夏思明
 * @date 2017年8月22日 下午2:34:33
 */
@Service
public class MemberService {
	private Logger log = LoggerFactory.getLogger(this.getClass());
	//private static Integer studyTime =0 ;
	private static Map<Integer, Object> userTime = new HashMap<Integer, Object>();
	@Autowired
	private MemberInfoModelMapper  memberInfoModelMapper;
	@Autowired
	private BranchInfoModelMapper branchInfoModelMapper;
	@Autowired
	private StudyRecordModelMapper studyRecordMapper;
	@Autowired
	private MemRoleModelMapper memRoleModelMapper;
	@Autowired
	private GroupInfoModelMapper groupMapper;
	@Autowired
	private SequenceModelMapper sequenceModelMapper;
	/*public Page<MemberInfoModel> showMembers(PageParamCommand cmd){
		PageHelper.startPage(cmd.getPage(), cmd.getRows());
		Page<MemberInfoModel> page = this.memberInfoModelMapper.selectMembersByPage(cmd);
		page.setPageNum(cmd.getPage());
		page.setPageSize(cmd.getRows());
		return page;
	}*/
	public ArrayList<MemberInfoModel> showMembers(PageParamCommand cmd){
		//PageHelper.startPage(cmd.getPage(), cmd.getRows());
		ArrayList<MemberInfoModel> list = new ArrayList<MemberInfoModel>();
		list= this.memberInfoModelMapper.selectMembersByPage(cmd);
		return list;
	}
	public ArrayList<MemberInfoModel> showMembersByBId(PageParamCommand cmd){
		//PageHelper.startPage(cmd.getPage(), cmd.getRows());
		 return this.memberInfoModelMapper.selectMembersByBrIdPage(cmd);
	}
	/**
	 * 用户登录
	 * @param name
	 * @param password
	 * @return
	 */
	/*@Transactional(readOnly=true)
	public ResultModel<UserBriefModel> login (String tel,String password) throws Exception {
		log.info("memberService 登陆login方法参数:",tel,password);
		ResultModel<UserBriefModel> model = new ResultModel<UserBriefModel>(); // 返回的结果集
		UserBriefModel userBriefModel = new UserBriefModel();
		MemberInfoModel miModel=memberInfoModelMapper.login(tel);
		if(miModel != null){
			if(miModel.getMemberPass().equals(password)){
				
				// 封装信息到用户基本信息model中
				userBriefModel.setGender(miModel.getMemberSex());
				userBriefModel.setIconUrl(miModel.getMemberPhotourl());
				userBriefModel.setId(miModel.getMemberId());
				userBriefModel.setName(miModel.getMemberName());
				userBriefModel.setRolesType(MemberJobType.getType(miModel.getMemberJob()));
				userBriefModel.setPartyOrganizationName(miModel.getBranchName());
				userBriefModel.setPartyClassification(MemberType.getType(miModel.getMemberType()));
				// 封装结果集model
				model.setData(userBriefModel);
				model.setInfo(0); // 登陆成功
				model.setMessage("Success"); 
				model.setStatus(true); //
			}else{
				// 封装结果集model
				model.setInfo(05); // 密码错误
				model.setMessage("密码错误!");
				model.setStatus(false);
			}
		}else{
			model.setInfo(01); // 账号不正确
			model.setMessage("账号错误!");
			model.setStatus(false);
		}
		return model;
	}*/
	@Transactional(readOnly=true)
	public ResultModel<UserInfoAll> login (String tel,String password) throws Exception {
		log.info("memberService 登陆login方法参数:",tel,password);
		ResultModel<UserInfoAll> model = new ResultModel<UserInfoAll>(); // 返回的结果集
		UserInfoAll userAllModel= new UserInfoAll();
		userAllModel=memberInfoModelMapper.LoginGetInfo(tel);
		List<Long> groupId = new ArrayList<Long>();
		
		if(userAllModel != null&&userAllModel.getInstitutions()!=null&&userAllModel.getUserBrief()!=null){
			if(userAllModel.getUserBrief().getUserPass().equals(password)){
				userAllModel.getUserBrief().setUserPass(null);
				userAllModel.getUserBrief().setRolesType(MemberJobType.getType(userAllModel.getUserBrief().getRolesName()));
				userAllModel.getInstitutions().setBrRolesType(MemberJobType.getType(userAllModel.getInstitutions().getBrRolesName()));
				userAllModel.getUserBrief().setPartyClassification(MemberType.getType(userAllModel.getUserBrief().getPartyClassificationName()));
				String userName = StringDeal.getUserIdAboveFour(userAllModel.getUserBrief().getId());
				System.out.println(userName);
				groupId = this.groupMapper.getGroupId(userName);
				userAllModel.getUserBrief().setGroupId(groupId);
				model.setData(userAllModel);
				model.setInfo(0); // 登陆成功
				model.setMessage("Success"); 
				model.setStatus(true); //
			}else{
				// 封装结果集model
				model.setInfo(05); // 密码错误
				model.setMessage("密码错误!");
				model.setStatus(false);
			}
		}else{
			model.setInfo(01); // 账号不正确
			model.setMessage("账号错误!");
			model.setStatus(false);
		}
		return model;
	}
	
	/**
	 * 用户登录
	 * @param name
	 * @param password
	 * @return
	 */
	@Transactional(readOnly=true)
	public ResultModel<UserBriefModel> webLogin (String tel,String password) throws Exception {
		log.info("memberService 登陆login方法参数:",tel,password);
		String 	adminTel = null;
		String adminPass =null;
		ResultModel<UserBriefModel> model = new ResultModel<UserBriefModel>(); // 返回的结果集
		UserBriefModel userBriefModel = new UserBriefModel();
		MemberInfoModel miModel=memberInfoModelMapper.login(tel);
		MemberInfoModel adminModel = memberInfoModelMapper.selectByAdminTel(tel);
		if(adminModel!=null){
		 adminTel = adminModel.getMemberTel();
		 adminPass = adminModel.getMemberPass();
		}
		if(miModel != null){
			MemRoleModel rr= this.memRoleModelMapper.selectByMemberId(miModel.getMemberId());
			if((miModel.getMemberPass().equals(password)&&memRoleModelMapper.selectByMemberId(miModel.getMemberId())!=null&&memRoleModelMapper.selectByMemberId(miModel.getMemberId()).getRelationRole()!=1)||(StringUtils.equals(adminTel, tel)&&adminPass.equals(password))){
				// 封装信息到用户基本信息model中
				userBriefModel.setGender(miModel.getMemberSex());
				userBriefModel.setIconUrl(miModel.getMemberPhotourl());
				userBriefModel.setId(miModel.getMemberId());
				userBriefModel.setName(miModel.getMemberName());
				userBriefModel.setRolesType(MemberJobType.getType(miModel.getMemberJob()));
				userBriefModel.setPartyOrganizationName(miModel.getBranchName());
				userBriefModel.setPartyClassification(MemberType.getType(miModel.getMemberType()));
				// 封装结果集model
				model.setData(userBriefModel);
				model.setInfo(0); // 登陆成功
				model.setMessage("Success"); 
				model.setStatus(true); //
			}else{
				// 封装结果集model
				model.setInfo(05); // 密码错误
				model.setMessage("帐号不存在或密码错误!");
				model.setStatus(false);
			}
		}else{
			model.setInfo(01); // 账号不正确
			model.setMessage("帐号不存在或账号错误!");
			model.setStatus(false);
		}
		return model;
	}
	/**
	 * <p>重置密码</p>
	 * @param request
	 * @param password
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public ResultModel<UserBriefModel> restPassWord(HttpServletRequest request,String password){
		log.info("memberService 重置密码restPassWord方法参数",password);
		ResultModel<UserBriefModel> model = new ResultModel<UserBriefModel>(); // 返回的结果集
		MemberInfoModel record=new MemberInfoModel();
		// 通用参数(数据对象,操作返回代码,操作附加信息)
		model.setData(null);
		model.setInfo(0); 
		model.setMessage("");
		try{
			// 获取session
			HttpSession session=request.getSession();
			// 获取当前登录用户Id
			Integer userId=(Integer)session.getAttribute("userId");
			// 封装用户信息model
			record.setMemberId(userId); 	// 用户Id
			record.setMemberPass(password); // 新密码
			memberInfoModelMapper.updateByPrimaryKeySelective(record);
			model.setStatus(true);  // 操作成功
		}catch(Exception e){
			model.setStatus(false); // 操作失败
			e.printStackTrace();
			log.error("memberService 重置密码失败!",e);
			throw e;
		}
		return model;
	}
	
	/**
	 * <p>获得当前登录用户所属支部下所有人员信息</p>
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	@Transactional(readOnly=true)
	public ResultModel<List<UserBriefModel>> getBranchUsersInfoByUserId(Integer userId) throws Exception{
		log.info("memberService 获得当前登录用户所属支部下所有人员信息getBranchUsersInfoByUserId方法参数",userId);
		ResultModel<List<UserBriefModel>> model = new ResultModel<List<UserBriefModel>>(); // 返回的结果集
		List<MemberInfoModel> memberInfoModels = null;
		List<UserBriefModel> userBriefModels = new ArrayList<UserBriefModel>();
		model.setData(userBriefModels);
		model.setInfo(0); 
		model.setMessage("");
		memberInfoModels=memberInfoModelMapper.getBranchUsersInfoByUserId(userId);
		if(!CollectionUtils.isEmpty(memberInfoModels)){
			for(MemberInfoModel miModel:memberInfoModels){
				UserBriefModel userBriefModel=new UserBriefModel();
				userBriefModel.setGender(miModel.getMemberSex());
				userBriefModel.setIconUrl(miModel.getMemberPhotourl());
				userBriefModel.setId(miModel.getMemberId());
				userBriefModel.setName(miModel.getMemberName());
				userBriefModel.setRolesType(MemberJobType.getType(miModel.getMemberJob()));
				userBriefModel.setPartyClassification(MemberType.getType(miModel.getMemberType()));
				userBriefModels.add(userBriefModel);
			}
			model.setData(userBriefModels);
			model.setStatus(true);
		}
		return model;
	}
	
	/**
	 * 添加党员
	 * @param miModel
	 */
	public int addMember(MemberInfoModel miModel) {
		/*String id = sequenceModelMapper.getId();
		miModel.setMemberId(Integer.parseInt(id));*/
		List<MemberInfoModel> memList = new ArrayList<MemberInfoModel>();
		memList.add(miModel);
		managerMember.registerMembers(memList);
		return memberInfoModelMapper.insertSelective(miModel);
	}

	/**
	 * 
	 * <p>获取支部名称</p>
	 * @return
	 * @author 袁杨柳(17607177525)
	 * @date 2017年5月11日 下午4:06:22
	 */
	@Transactional(readOnly = true)
	public List<MemberInfoModel> getbranchName() {
		List<MemberInfoModel> rs;
		log.info("获取支部名称");
		try{
			rs=memberInfoModelMapper.getbranchName();
			return rs;
		}catch(Exception e){
			log.error("获取支部名称出错{}", e);
			e.printStackTrace();
			throw e;
		}	
	}
	/**
	 * 
	 * <p>获取支部名称</p>
	 * @return
	 * @author 袁杨柳(17607177525)
	 * @date 2017年5月11日 下午4:06:22
	 */
	@Transactional(readOnly = true)
	public List<MemberInfoModel> getbranchNameByIds(List<Integer> ids) {
		List<MemberInfoModel> rs;
		log.info("获取支部名称");
		try{
			rs=memberInfoModelMapper.getbranchById(ids);
			return rs;
		}catch(Exception e){
			log.error("获取支部名称出错{}", e);
			e.printStackTrace();
			throw e;
		}	
	}
	/**
	 * @param memberTel
	 * @return
	 */
	public JSONObject checkUserPhone(String memberTel) {
		JSONObject result=new JSONObject();
		String info= memberInfoModelMapper.findMemberTel(memberTel);
	    if(info == null){
	    	result.put("valid", true);
	        return result;
	    }else{
	    	result.put("valid", false);
	        return result;
	    }
	}
	
	/**
	 * <p>app根据用户Id查询用户详情</p>
	 * @param userId
	 * @return
	 */
	@Transactional(readOnly=true)
	public ResultModel<UserModel> getUserDetails(Integer userId) throws Exception{
		log.info("memberService 根据用户Id查询用户详情方法getUserDetails参数:",userId);
		ResultModel<UserModel> model = new ResultModel<UserModel>(); // 返回的结果集
		UserModel userModel = new UserModel(); 						 // 用户详细信息
		UserBriefModel userBriefModel = new UserBriefModel(); 		 // 用户基本信息
		MemberInfoModel miModel = new MemberInfoModel(); 			 // 党员信息
		model.setInfo(0); 		  									 // 操作异常代码(0无异常)
		model.setMessage("");
		List<Long> groupId = new ArrayList<Long>();
		// 附加信息
		// 查询
		miModel=memberInfoModelMapper.getUserInfoByUserId(userId);
		if(miModel!=null){
			// 封装用户基本信息
		
			groupId = this.groupMapper.getGroupId(StringDeal.getUserIdAboveFour(userBriefModel.getId()));
			userBriefModel.setGroupId(groupId);
			userBriefModel.setGender(miModel.getMemberSex());			 // 用户性别
			userBriefModel.setPartyClassification(MemberType.getType(miModel.getMemberType())); // 用户类型
			userBriefModel.setIconUrl(miModel.getMemberPhotourl());		 //	用户头像URL
			userBriefModel.setId(miModel.getMemberId());				 //	用户Id
			userBriefModel.setName(miModel.getMemberName());			 // 用户名
			userBriefModel.setRolesType(MemberJobType.getType(miModel.getMemberJob()));		 // 用户职务
			userBriefModel.setPartyOrganizationName(miModel.getBranchName()); // 用户所属组织名称
			// 封装用户详细信息
			userModel.setBriefInfo(userBriefModel);						 // 用户基本信息
			userModel.setBirthDay(miModel.getMemberBirth()==null?0:miModel.getMemberBirth().getTime());	 // 用户生日
			userModel.setEmail(miModel.getMemberEmail());				 // 用户Email
			userModel.setMobileNumber(miModel.getMemberTel());			 // 用户电话
			userModel.setUserIdcard(miModel.getMemberIdcard());			 // 用户身份证
			userModel.setWorkUnitName(miModel.getMemberWorkunit());		 // 用户工作地址
			// 封装结果集
			model.setData(userModel); // 返回的数据主体
			model.setStatus(true);    // 操作成功
		}
		return model;
	}
	
	/**
	 * <p>APP上传用户头像</p>
	 * @param file
	 * @param request
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
	public ResultModel<String> updateUserIcon(MultipartFile file,Integer userId,HttpServletRequest request) throws Exception{
		log.info("memberService 上传保存用户头像方法updateUserIcon参数:",file,userId);
		ResultModel<String> model = new ResultModel<String>();  // 返回的结果集
		model.setInfo(0); 						// 操作异常代码(0无异常)
		model.setMessage("");					// 附加信息
		MemberInfoModel mimodel = new MemberInfoModel(); 
		/*String fileName = file.getOriginalFilename(); 		// file.getOriginalFilename()是得到上传时的文件名
		String uploadFileName=UUID.randomUUID()+fileName.substring(fileName.length()-4); 	// 修饰后的文件名
		System.out.println(uploadFileName);
		File myfile=new File(request.getSession().getServletContext().getRealPath("/")+"Images"+System.getProperty("file.separator", "\\")+uploadFileName);
		File parent = myfile.getParentFile();
		if (!parent.exists()) {  // 如果新建文件不存在,则重新创建
			parent.mkdirs();
		}
		if(!myfile.exists()){
			myfile.createNewFile();
		}
		file.transferTo(myfile);
		// 封装用户model
		mimodel.setMemberId(userId);  // 用户Id
	mimodel.setMemberPhotourl(GetLocalIp.sHOST_URL+"/hbjiandang/Images/"+uploadFileName);	// 上传头像的存放路径*/	
		String uploadFileName = UploadImage.uploadImg(request, file);
		mimodel.setMemberId(userId);  // 用户Id
		mimodel.setMemberPhotourl(GetLocalIp.imgURL+uploadFileName);	// 上传头像的存放路径*/	
		// 保存
			//转存文件	
		// 修改用户信息
		memberInfoModelMapper.updateByPrimaryKeySelective(mimodel);
		model.setData(GetLocalIp.imgURL+uploadFileName);
		model.setStatus(true); // 操作成功
		return model;
	}
	
	/**
	 * 根据id查询党员信息
	 */
	public MemberInfoModel selectBranchBymemberId(Integer memberId) {
		return memberInfoModelMapper.selectBranchBymemberId(memberId);
	}
	
	/**
	 * 根据id删除或修改党员信息
	 */
	public int updateMember(MemberInfoModel record) {
		return memberInfoModelMapper.updateByPrimaryKeySelective(record);
	}
	/**
	 * 导入excel
	 * @param myfile
	 * @return
	 * @throws ParseException 
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public JSONObject readExcel(MultipartFile file) throws ParseException{
		JSONObject result=new JSONObject();
		try {
			//返回结果
			result.put("resultType", 0);  //resultType 0 代表全部成功 1代表程序没报错 2代表报错
			ReadExcel readExcel = new ReadExcel();
			List<List<Object>> lists = readExcel.read2007Excel(file, 0);
			 List<MemberInfoModel> miModelList = new ArrayList<MemberInfoModel>();
			 int i = 1;
			 if(lists.size()==0 || lists == null){
				//没有主体数据就不导入了
				result.put("resultType", 1);
				result.put("message","请导入正确的excel模板");
				return result;
			 }
			 for(List<Object> list:lists){
					 if(i>2){
						  String memberName=list.get(0).toString();
						 if(!("").equals(memberName)){
							MemberInfoModel miModel = new MemberInfoModel();
						   miModel.setMemberName(list.get(0).toString());
						    miModel.setMemberEdu(list.get(1).toString());
						    miModel.setMemberSex(list.get(2).toString());
						 String phone= list.get(3).toString();
						    List<String> memberPhones=memberInfoModelMapper.getMemberPhone();
						    if(phone.length() >= 11){
						    	for(String memberPhone : memberPhones){
							    	if(phone.equals(memberPhone)){
							    		result.put("resultType", 1);
								    	result.put("message", "手机号:"+phone+"已被注册");
								    	return result;
							    	}
						    	}
						    	miModel.setMemberTel(phone);
						    }else{
						    	result.put("resultType", 1);
						    	result.put("message", "手机号:"+phone+"必须为11位数字");
						    	return result;
						    }
						    
						    miModel.setMemberNational(list.get(4).toString());

						    miModel.setMemberNative(list.get(5).toString());
						    SimpleDateFormat sdf  =   new  SimpleDateFormat( "yyyy-MM-dd" );
						    if(!list.get(6).toString().isEmpty()){
						    	 Date rdDate = sdf.parse(list.get(6).toString());
								 miModel.setMemberRddate(rdDate);
						    }else{
						    	 miModel.setMemberRddate(null);
						    }
						    if(list.get(7) !=null && list.get(7) != "" ){
						    	if(list.get(7).toString() !=null && list.get(7).toString() != ""){
							    Date zzDate = sdf.parse(list.get(7).toString());
							    miModel.setMemberZzdate(zzDate);
						    	}
						    }else{
						    	miModel.setMemberZzdate(null);
						    }
						    if(!list.get(8).toString().isEmpty()){
						    Date birthday = sdf.parse(list.get(8).toString());
						    miModel.setMemberBirth(birthday);
						    }else{
						    	miModel.setMemberBirth(null);
						    }
						    miModel.setMemberWorkunit(CommUtils.isEmpty(list.get(9).toString()));
						    miModel.setMemberJob("普通成员");
						    if(!list.get(10).toString().isEmpty()){
							    if(MathUtil.isIdcard(list.get(10).toString())){
							    miModel.setMemberIdcard(list.get(10).toString());
							    }else{
							    	result.put("resultType", 1);
							    	result.put("message", "身份证号码不合法");
							    	return result;
							    }
						    }
						    miModel.setMemberPass(CommUtils.MD5("123456"));
						    String branchName=list.get(11).toString();
						    if(branchName.isEmpty()){
						    	result.put("resultType", 1);
						    	result.put("message", "所在支部信息不能为空");
						    	return result;
						    }
						    Integer branchId = this.branchInfoModelMapper.getBranchIdsByName(branchName);
						    if(branchId!=null){
						    miModel.setMemberInbranchid(branchId);
						    }else{
						    	 //miModel.setMemberInbranchid(0);
						    	result.put("resultType", 1);
						    	result.put("message", branchName+"不存在");
						    	return result;
						    }
						    miModel.setMemberType(list.get(12).toString());
						    miModelList.add(miModel);
			        	}
				 }
				  i++;
		        }
			memberInfoModelMapper.importMemberExcel(miModelList);
			
			managerMember.registerMembers(miModelList);//极光注册用户
			//sql有问题：j = this.branchInfoModelMapper.importExcelByBranchName(miModelList);
		} catch (IOException e) {
			result.put("resultType", 2);
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return result;
	}
	@Transactional(readOnly=true)
	public List<MemberInfoModel> getNameByMember(PageParamCommand cmd){
		List<MemberInfoModel> result = this.memberInfoModelMapper.getMemberName(cmd);
		return result;
		
	}
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
	public int editPass(Integer userId,String pass,String newPass){
		int i = 0;
		if(userId!=null){
		MemberInfoModel member = this.memberInfoModelMapper.selectByPrimaryKey(userId);
		if(pass.equals(member.getMemberPass())){
			MemberInfoModel model2 = new MemberInfoModel();
			model2.setMemberId(userId);
			model2.setMemberPass(newPass);
			i = this.memberInfoModelMapper.updateByPrimaryKeySelective(model2);
		}
		}
		return i;
		
	}
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
	public void insertRecordData(StudyRecord stuRecord,Integer userId){
		Map<String, Object> map = new HashMap<String, Object>();
		map = isCurrentDay(userId);
		boolean isflag = (boolean) map.get("isToday");
		Integer studyTime = 0;
		if(isflag){
		 studyTime = currentTime(userId);
		}
		StudyRecordModel stuReModel = new StudyRecordModel();
		List<TimeRecordModel> tmList = stuRecord.getmTimeRecords();
		int countTime = 0;
		for(TimeRecordModel tmModel :tmList){
			/*if(tmModel.getEndTime().getDate()==tmModel.getStartTime().getDate()){
			int secords = tmModel.getEndTime().getSeconds()-tmModel.getStartTime().getSeconds();
			int minutes = tmModel.getEndTime().getMinutes()-tmModel.getStartTime().getMinutes();
			int hour = tmModel.getEndTime().getHours()- tmModel.getStartTime().getHours();
			if(hour>=0&&minutes>=0&&secords>=0){
				countTime += hour*60+minutes*60+secords;
			}
			}*/
			countTime = (int) ((tmModel.getEndTime().getTime()-tmModel.getStartTime().getTime())/1000);
			studyTime+=countTime;
		}
		stuReModel.setTodayTime(studyTime);
		stuReModel.setStuMemberId(userId);
		if(isflag){
		//如果为今天直接更新数据
			stuReModel.setStuDate(new Date());
			stuReModel.setSturecordId((Integer) map.get("id"));
			stuReModel.setContiniuityStudydays(continueDays(userId));
			this.studyRecordMapper.updateByUserId(stuReModel);
		}else{
		//如果是新的一天直接插入数据
			stuReModel.setStuDate(new Date());
			stuReModel.setContiniuityStudydays(continueDays(userId));
			this.studyRecordMapper.insertSelective(stuReModel);
		}
	}
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
	public void insertRecordData(List<TimeRecordIosModel> timeRecordIosModels,Integer userId){
		Map<String, Object> map = new HashMap<String, Object>();
		map = isCurrentDay(userId);
		boolean isflag = (boolean) map.get("isToday");
		Integer studyTime = 0;
		if(isflag){
			studyTime = currentTime(userId);
		}
		StudyRecordModel stuReModel = new StudyRecordModel();
		int countTime = 0;
		for(TimeRecordIosModel tmModel :timeRecordIosModels){
			/*if(tmModel.getEndTime().getDate()==tmModel.getStartTime().getDate()){
			int secords = tmModel.getEndTime().getSeconds()-tmModel.getStartTime().getSeconds();
			int minutes = tmModel.getEndTime().getMinutes()-tmModel.getStartTime().getMinutes();
			int hour = tmModel.getEndTime().getHours()- tmModel.getStartTime().getHours();
			if(hour>=0&&minutes>=0&&secords>=0){
				countTime += hour*60+minutes*60+secords;
			}
			}*/
			countTime = (int) ((tmModel.getEndTime()-tmModel.getStartTime())/1000);
			studyTime+=countTime;
		}
		stuReModel.setTodayTime(studyTime);
		stuReModel.setStuMemberId(userId);
		if(isflag){
			//如果为今天直接更新数据
			stuReModel.setStuDate(new Date());
			stuReModel.setSturecordId((Integer) map.get("id"));
			stuReModel.setContiniuityStudydays(continueDays(userId));
			this.studyRecordMapper.updateByUserId(stuReModel);
		}else{
			//如果是新的一天直接插入数据
			stuReModel.setStuDate(new Date());
			stuReModel.setContiniuityStudydays(continueDays(userId));
			this.studyRecordMapper.insertSelective(stuReModel);
		}
	}
	//判断是否为今天的数据
	public Map isCurrentDay(Integer userId){
		Map<String, Object> map = new HashMap<String, Object>();
		Date date = new Date();
		Integer currentDay = 0;
		//this.studyRecordMapper.getMaxDay(userId);
		StudyRecordModel record = this.studyRecordMapper.getMaxDay(userId);
		System.out.println(date.getDate());
		if(record!=null){
		 currentDay = record.getStuDate().getDate();
			if(currentDay!=null&&currentDay==date.getDate()){
				map.put("isToday",true);
				map.put("id",record.getSturecordId());
			}else{
				map.put("isToday",false);
				map.put("id",record.getSturecordId());
			}
		}else{
			map.put("isToday",false);
			map.put("id",null);
		}
		return map;
	}
	
	//计算连续天数
	public  int continueDays(Integer userId){
		List<StudyRecordModel> days = new ArrayList<StudyRecordModel>();
		days = 	this.studyRecordMapper.allDays(userId);
		int count = 1;
		if(days.size()>1){
		for(int i =1;i<days.size();i++){
			long da =  ((days.get(i-1).getStuDate().getDate()-days.get(i).getStuDate().getDate()));
			if(da==1){
				count++;
			}else{
				break;
			}
		}
		}
		return count;
	}
	//查询当前用户的学习今天的学习时长
	public int currentTime(Integer userId){
		
		StudyRecordModel studyModel = this.studyRecordMapper.selectTodayTime(userId);
		if(studyModel!=null){
		Integer time = studyModel.getTodayTime();
		if(time!=null&&time>0){
			return time;
		}
		}
		return 0;
	}
	/**
	 * 根据用户id查询所有的学习记录
	 * @param request
	 * @return
	 */
	public List<StudyRecordModel> getStuReByUserId(Integer id){
		List<StudyRecordModel> result = new ArrayList<StudyRecordModel>();
	     result =this.studyRecordMapper.selectStuReList(id);
		return result;
		
	}
	public int deleteByBatch(Integer[] memberIds){
		List<Integer> list = Arrays.asList(memberIds);
		Integer i = 0;
		i = this.memberInfoModelMapper.deletedByBatch(list);
		return i;
	}
	public static void main(String[] args) {
		System.out.println("党支部书记".contains("书记"));
	}
}
