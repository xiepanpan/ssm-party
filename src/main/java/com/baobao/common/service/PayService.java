package com.baobao.common.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.baobao.common.cmd.BranchCondition;
import com.baobao.common.mapping.BranchInfoModelMapper;
import com.baobao.common.mapping.MemRoleModelMapper;
import com.baobao.common.mapping.MemberInfoModelMapper;
import com.baobao.common.mapping.PayInfoModelMapper;
import com.baobao.common.model.BranchInfoModel;
import com.baobao.common.model.ContributionRecordModel;
import com.baobao.common.model.ContributionRecordPageModel;
import com.baobao.common.model.MemRoleModel;
import com.baobao.common.model.MemberInfoModel;
import com.baobao.common.model.PayInfoModel;
import com.baobao.util.PageParamCommand;

@Service
public class PayService {
	@Autowired
	private PayInfoModelMapper payInfoMapper;
	@Autowired
	private BranchInfoModelMapper brMapper;
	@Autowired
	private MemberInfoModelMapper memberInfoMapper;
	@Autowired
	private MemRoleModelMapper memRoleMapper;
	public List<PayInfoModel> getPayList(){
		List<PayInfoModel> list = new ArrayList<PayInfoModel>();
		list = this.payInfoMapper.getPayList();
		return list;
		
	}
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public int addPayInfo(PayInfoModel record){
		int i = 0 ;
		BranchInfoModel bModel = this.brMapper.selectByPrimaryKey(Integer.parseInt(record.getPayBrName()));
		record.setPayBrName(bModel.getBranchName());
		i = this.payInfoMapper.insertSelective(record);
		return i;
		
	}
	public List<String> getTelList(Integer uid,String uname){
		List<String> list = new ArrayList<String>();
		if(uname!=null){
			list = this.memberInfoMapper.getTelByName(uname);
		}else{
		MemberInfoModel mModel = this.memberInfoMapper.selectByPrimaryKey(uid);
		if(mModel.getMemberInbranchid()==-1){
			list = this.memberInfoMapper.getMemberPhone();
		}else{
			MemRoleModel roleModel = this.memRoleMapper.selectByMemberId(uid);
			if(roleModel.getRelationRole()==3){
				list = this.memberInfoMapper.getMemberPhone();
			}else{
				List<Integer> ids = this.brMapper.getBranchId(roleModel.getRelationPartBranchId());
				list = this.memberInfoMapper.getTelByIds(ids);
			}
		}
		}
		return list;
	}
	public List<String> getNameList(Integer uid,Integer brId){
		PageParamCommand cmd = new PageParamCommand();
		List<String> list = new ArrayList<String>();
		if(brId!=null){
			List<Integer> ids = new ArrayList<Integer>();
			ids.add(brId);
			list = this.memberInfoMapper.getPayName(ids);
		}else{
			MemberInfoModel mModel = this.memberInfoMapper.selectByPrimaryKey(uid);
			if(mModel.getMemberInbranchid()==-1){
				list = this.memberInfoMapper.getPayName(null);
			}else{
				MemRoleModel roleModel = this.memRoleMapper.selectByMemberId(uid);
				if(roleModel.getRelationRole()==3){
					list = this.memberInfoMapper.getPayName(null);
				}else{
					List<Integer> ids = this.brMapper.getBranchId(roleModel.getRelationPartBranchId());
					list = this.memberInfoMapper.getPayName(ids);
				}
			}
		}
		return list;
	}
	public List<BranchInfoModel> getBrList(Integer uid){
		List<BranchInfoModel> list = new ArrayList<BranchInfoModel>();
		
		BranchCondition cmd = new BranchCondition();
		//如果为管理员查询所有支部信息
		//如果为校级管理员查询所在院级支部的所有信息
		//如果为院级管理员查询院下所有支部的信息
		MemberInfoModel mModel = this.memberInfoMapper.selectByPrimaryKey(uid);
		if(mModel.getMemberInbranchid()==-1){
			//则为管理员
			list = this.brMapper.getBranchList(cmd);
		}else{
			MemRoleModel roleModel = this.memRoleMapper.selectByMemberId(uid);
			if(roleModel.getRelationRole()==3){//校级
				list = this.brMapper.getBranchList(cmd);
			}else{//院级
				 list = this.brMapper.getBranchInfoModels(roleModel.getRelationPartBranchId());
			}
		}
		
		return list;
	}
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public int editPay(PayInfoModel payModel){
		int i = 0;
		BranchInfoModel bModel = this.brMapper.selectByPrimaryKey(Integer.parseInt(payModel.getPayBrName()));
		payModel.setPayBrName(bModel.getBranchName());
		i = this.payInfoMapper.updateByPrimaryKeySelective(payModel);
		return i;
		
	}
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public int deletePay(Integer id){
		int i = 0;
		i = this.payInfoMapper.deleteByPrimaryKey(id);
		return i;
	}
	/**
	 * 根据用户id获取所有党费费用的页面
	 * @param id
	 * @return
	 */
	public ContributionRecordPageModel getPayPage(Integer id){
		
		ContributionRecordPageModel cpage = new ContributionRecordPageModel();
		List<ContributionRecordModel> conList = new ArrayList<ContributionRecordModel>();
		MemberInfoModel model = new MemberInfoModel();
		model = this.memberInfoMapper.selectByPrimaryKey(id);
		String tel = model.getMemberTel();
		
		conList =this.payInfoMapper.getPayModel(tel);
		Float monMoney  = this.payInfoMapper.getMonMoney(tel);
		if(monMoney==null){
			monMoney = 0.0f;
		}
		Float yearMoney = this.payInfoMapper.getYearMoney(tel);
		Float allMoney = this.payInfoMapper.getAllMoney(tel);
		cpage.setTotalHistorical(allMoney);
		cpage.setTotalYear(yearMoney);
		cpage.setCurrentMembershipDues(monMoney);
		cpage.setYearRecordList(conList);
		return cpage;
		
	}
	/**
	 * 根据年份查询用户的记录
	 */
	public List<ContributionRecordModel> getPayList(Integer id,Integer year){
		 List<ContributionRecordModel> list = new ArrayList<ContributionRecordModel>();
		MemberInfoModel model = new MemberInfoModel();
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		model = this.memberInfoMapper.selectByPrimaryKey(id);
		String tel = model.getMemberTel();
		hashMap.put("year", year);
		 hashMap.put("tel", tel);
		list = this.payInfoMapper.getPayModelByYear(hashMap);
		return list;
		
	}
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public int deleteBatch(Integer[] Ids){
		Integer i = 0;
		i = this.payInfoMapper.deleteBatch(Arrays.asList(Ids));
		return i;
		
	}
	
	//查询所对应的号码和姓名
	public List<Map<String, Object>> getTelNameList(Integer uId,Integer brId){
		PageParamCommand cmd = new PageParamCommand();
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		if(brId!=null){
			List<Integer> ids = new ArrayList<Integer>();
			ids.add(brId);
			list = this.memberInfoMapper.getPayTelName(ids);
		}else{
			MemberInfoModel mModel = this.memberInfoMapper.selectByPrimaryKey(uId);
			if(mModel.getMemberInbranchid()==-1){
				list = this.memberInfoMapper.getPayTelName(null);
			}else{
				MemRoleModel roleModel = this.memRoleMapper.selectByMemberId(uId);
				if(roleModel.getRelationRole()==3){
					list = this.memberInfoMapper.getPayTelName(null);
				}else{
					List<Integer> ids = this.brMapper.getBranchId(roleModel.getRelationPartBranchId());
					list = this.memberInfoMapper.getPayTelName(ids);
				}
			}
		}
		return list;
	}
	//根据所给的人员的电话查出所在的支部
	public Integer getBrId(String tel){
		return 	this.brMapper.getBrIdByTel(tel);
		
	}
	//根据不同的角色来查看缴费信息
	public List<PayInfoModel> getPayListByRole(Integer uid){
		List<PayInfoModel> result = new ArrayList<PayInfoModel>();
		MemRoleModel roleModel = this.memRoleMapper.selectByMemberId(uid);
		if(roleModel!=null&&roleModel.getRelationRole()==2){
		result = this.payInfoMapper.getPayInfoBypartId(roleModel.getRelationPartBranchId());
		}else{
			result = this.payInfoMapper.getPayList();
		}
		return result;
	}
}
