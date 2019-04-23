/*******************************************************************************
 * Project : hbjiandang
 * Create on 2017年9月4日 上午8:55:14
 * Copyright (c) 2014 - 2016 粮达网
 * 注意：本内容仅限于粮达网内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
package com.baobao.common.service;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.baobao.common.cmd.ActivityCondition;
import com.baobao.common.mapping.ActPushRecModelMapper;
import com.baobao.common.mapping.ActRecordModelMapper;
import com.baobao.common.mapping.ActivityModelMapper;
import com.baobao.common.mapping.BranchInfoModelMapper;
import com.baobao.common.mapping.MemRoleModelMapper;
import com.baobao.common.mapping.MemberInfoModelMapper;
import com.baobao.common.mapping.PartyBranchModelMapper;
import com.baobao.common.mapping.SequenceModelMapper;
import com.baobao.common.mapping.TempActInfoModelMapper;
import com.baobao.common.model.ActPushRecModel;
import com.baobao.common.model.ActRecordModel;
import com.baobao.common.model.ActivityMobile;
import com.baobao.common.model.ActivityModel;
import com.baobao.common.model.BranchInfoModel;
import com.baobao.common.model.ChildModel;
import com.baobao.common.model.CommentModel;
import com.baobao.common.model.MemRoleModel;
import com.baobao.common.model.MemberInfoModel;
import com.baobao.common.model.PartyUserModel;
import com.baobao.common.model.TempActInfoModel;
import com.baobao.common.model.UserResultModel;
import com.baobao.util.MemberJobType;
import com.baobao.util.MemberType;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

/**
 * <P>TODO</P>
 * @author 夏思明
 * @date 2017年9月4日 上午8:55:14
 */
@Service
public class ActivityService {
	@Autowired
	private ActivityModelMapper activityMapper;
	@Autowired
	private ActRecordModelMapper actRecordMapper;
	@Autowired
	private SequenceModelMapper sequenceModelMapper;
	@Autowired
	private MemberInfoModelMapper memberMapper;
	@Autowired
	private MemRoleModelMapper memRoleModelMapper;
	@Autowired
	private BranchInfoModelMapper  branchInfoMapper;
	@Autowired
	private PartyBranchModelMapper partyBranchMapper;
	@Autowired
	private TempActInfoModelMapper tempActInfoMapper;
	@Autowired
	private ActPushRecModelMapper actPushRecModelMapper;
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public int insertActivity(ActivityMobile activity,Integer userId,Integer publishStatus,List<Integer> participantsList){
		int i = 0;
		try {
			ActivityModel acModel = new ActivityModel();
			
			MemRoleModel mRole = new MemRoleModel();
			mRole = memRoleModelMapper.selectByMemberId(userId);
			if(mRole!=null&&mRole.getRelationRole()!=null){
				if(mRole.getRelationRole()==1){
					acModel.setActivityGrade(1);
					
				}else if(mRole.getRelationRole()==2){
					acModel.setActivityGrade(2);
					
				}else if(mRole.getRelationRole()==3){
					acModel.setActivityGrade(3);
					
				}
			}
			String Id=sequenceModelMapper.getId();
			Integer acId = Integer.parseInt(Id);
			List<ActRecordModel> acRecord = new ArrayList<ActRecordModel>();
			List<ActPushRecModel> actPushList = new ArrayList<ActPushRecModel>();
			acModel.setActivityId(acId);
			if(null!=activity.getTitle()){
			acModel.setActivityTitle(activity.getTitle());
			}
			if(null!=activity.getDescribe()){
				acModel.setActivityContext(activity.getDescribe());
				}
			if(null!=activity.getAddress()){
				acModel.setActivityPlace(activity.getAddress());
			}
			if(null!=activity.getStartTime()){
				acModel.setActivityStarttime(activity.getStartTime());
				}
			if(null!=activity.getEndTime()){
				acModel.setActivityEndtime(activity.getEndTime());
				}
			if(null!=userId){
				acModel.setActivityLeaderid(userId);
			}
			if(null!=activity.getLatitude()){
				acModel.setLatitude(activity.getLatitude());
			}
			if(null!=activity.getLongitude()){
				acModel.setLongitude(activity.getLongitude());
			}
			MemberInfoModel model = memberMapper.selectByPrimaryKey(userId);
			if(model.getMemberName()!=null){
				acModel.setActivityLeadername(model.getMemberName());
			}
			acModel.setActivityCreatetime(new Date());
			acModel.setActivityStatus(publishStatus);
			if(publishStatus==0){//保存不发布状态
				this.activityMapper.insertSelective(acModel);
				if(participantsList.size()>0){
					for(Integer mId:participantsList){
					TempActInfoModel tempModel =  new TempActInfoModel();
					tempModel.setTempactActid(acId);
					tempModel.setTempactJoinid(mId);
					tempActInfoMapper.insertSelective(tempModel);
				}
				}
			}else if(publishStatus==1){//发布状态
				this.activityMapper.insertSelective(acModel);
				if(participantsList.size()>0&&mRole.getRelationRole()==1){
					for(Integer mId:participantsList){
						ActRecordModel record = new ActRecordModel();
						record.setActRecActid(acId);
						record.setActMemberId(mId);
						acRecord.add(record);
					}
					this.actRecordMapper.batchInsertRecord(acRecord);
				}else if(participantsList.size()>0&&mRole.getRelationRole()==2||mRole.getRelationRole()==3){
					for(Integer mId:participantsList){
						ActPushRecModel record = new ActPushRecModel();
						record.setActPushActid(acId);
						record.setActPushBranchid(mId);
						record.setActPushTime(new Date());
						record.setActPushType(mRole.getRelationRole());
						actPushList.add(record);
					}
					this.actPushRecModelMapper.batchInsertRecord(actPushList);
				}
			}
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		return i;
		
	}
	@Transactional(readOnly=true)
	public List<ActivityMobile> getActivityByState(ActivityCondition cmd,Integer userId){
		
		
		if(cmd.getPage()>=1){
		cmd.setStartIndex((cmd.getPage()-1)*cmd.getRows());
		cmd.setEndIndex(10);
		}else{
			cmd.setPage(1);
			cmd.setStartIndex((cmd.getPage()-1)*cmd.getRows());
			cmd.setStartIndex(cmd.getPage()*cmd.getRows());
		}
		if(userId!=null){
		cmd.setActMemberId(userId);
		}
		List<ActivityMobile> list = this.actRecordMapper.getActivtyModelByState(cmd);
		System.out.println();
		return list;
	}
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public int updateActivityState(Integer userId,Integer id,Integer state){
		int i = 0;
		ActivityCondition cmd = new ActivityCondition();
		cmd.setId(id);
		cmd.setState(state);
		cmd.setActMemberId(userId);
		if(state == 2){
			cmd.setActRepottime(new Date());
			cmd.setType(1);
			this.actRecordMapper.insertReport(cmd);
		}
		i = this.actRecordMapper.updateStateById(cmd);
		return i;
		
	}
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public int submitReport(ActivityCondition cmd){
		int i = this.actRecordMapper.insertReport(cmd);
		if(i>=1&&cmd.getType()==1){
			cmd.setState(2);
			this.actRecordMapper.updateStateById(cmd);
		}
		return i;
		
	}
	@Transactional(readOnly=true)
	public String getReports(ActivityCondition cmd){
		String report = this.actRecordMapper.getReports(cmd);
		return report;
		
	}
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public int submitComment(ActivityCondition cmd){
		
		int i = this.actRecordMapper.insertReport(cmd);
		
		return i;
	}
	public CommentModel getComments(Integer userId,Integer id){
		ActivityCondition cmd = new ActivityCondition();
		//cmd.setActMemberId(userId2);
		cmd.setActMemberId(userId);
		cmd.setId(id);
		CommentModel cmodel = this.actRecordMapper.getComments(cmd);
		return cmodel;
		
	}
	public List<Map<String, Object>> getPartMemberList(Integer id){
		List<Map<String, Object>> datalist = new ArrayList<Map<String,Object>>();
	
		List<UserResultModel> list = this.actRecordMapper.getPartMembers(id);
		try {
		
			for(UserResultModel uModel:list){
				Map<String,Object> map = new HashMap<String, Object>();
				PartyUserModel pModel = new PartyUserModel();
				BeanUtils.copyProperties(pModel,uModel);
				if(uModel.getDangyuanType()!=null){
				pModel.setPartyClassification(MemberType.getType(uModel.getDangyuanType()));
				}
				if(uModel.getJobType()!=null){
					pModel.setRolesType(MemberJobType.getType(uModel.getJobType()));
				}
				map.put("user", pModel);
				map.put("activityStatus", 1<<uModel.getActivityStatus());
				datalist.add(map);
			 }
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return datalist;
	}
	public List<Map<Integer,String>> getBranch(Integer id){
		List<Map<Integer,String>> result =new ArrayList<Map<Integer,String>>();
		MemRoleModel mRole = new MemRoleModel();
		//先查出党员角色
		mRole =	this.memRoleModelMapper.selectByMemberId(id);
		if(mRole!=null&&mRole.getRelationRole()!=null){
			if(mRole.getRelationPartBranchId()!=null&&mRole.getRelationRole()==2){//如果是分院党支部级别，则查询下面的分支部
			result	= this.branchInfoMapper.getBranchChildId(mRole.getRelationPartBranchId());
			}else if(mRole.getRelationPartBranchId()!=null&&mRole.getRelationRole()==3){//如果是校支部级别，则查询院支部
			result = this.partyBranchMapper.getChildList(1);
			}
		}
		return result;
		
	}
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public boolean publishActivities(ActivityMobile acMoblie,List<Integer> participants,Integer uid){
		boolean flag = false;
		if(acMoblie.getId()!=null&&acMoblie.getGrade()!=null){
			MemRoleModel roleModel = new MemRoleModel();
			roleModel = this.memRoleModelMapper.selectByMemberId(uid);
			ActivityModel activity = new ActivityModel();
			Integer brRole = roleModel.getRelationRole();
			//1.先改变活动的发布的状态
			if(acMoblie.getGrade()==brRole){
			//改变活动活动的信息
			activity.setActivityId(acMoblie.getId());
			activity.setActivityTitle(acMoblie.getTitle());
			activity.setActivityContext(acMoblie.getDescribe());
			activity.setActivityPlace(acMoblie.getAddress());
			activity.setActivityStarttime(acMoblie.getStartTime());
			activity.setActivityEndtime(acMoblie.getEndTime());
			activity.setLatitude(acMoblie.getLatitude());
			activity.setLongitude(acMoblie.getLongitude());
			activity.setActivityCreatetime(new Date());
			activity.setActivityStatus(1);
			//改变创建的活动状态
			this.activityMapper.updateByPrimaryKeySelective(activity);
			}else{//改变推送互动状态
			ActPushRecModel actPush =new ActPushRecModel();
			actPush.setActPushBranchid(roleModel.getRelationPartBranchId());
			actPush.setActPushActid(acMoblie.getId());
			actPush.setActPushStatus(1);
			//改变发布的互动状态
			this.actPushRecModelMapper.updatePushStatus(actPush);
			}
			//2.再删除临时保存的记录信息
			this.tempActInfoMapper.deleteByActId(acMoblie.getId());
			//3.再讲活动记录插入(对应的支部或者是人员)
			if(brRole== 1){
				List<ActRecordModel> acList =  new ArrayList<ActRecordModel>();
				for(Integer mId:participants){
					ActRecordModel record = new ActRecordModel();
					record.setActRecActid(acMoblie.getId());
					record.setActMemberId(mId);
					acList.add(record);
				}
				this.actRecordMapper.batchInsertRecord(acList);
				flag = true;
			}else if(brRole> 1){
				List<ActPushRecModel> actPushList = new ArrayList<ActPushRecModel>();
				for(Integer mId:participants){
					ActPushRecModel record = new ActPushRecModel();
					record.setActPushActid(acMoblie.getId());
					record.setActPushBranchid(mId);
					record.setActPushTime(new Date());
					record.setActPushType(acMoblie.getGrade());
					actPushList.add(record);
				}
				this.actPushRecModelMapper.batchInsertRecord(actPushList);
				flag = true;
			}
		}
		return flag;
	}
	public Map<String, Object> getActDetails(Integer actId,Integer uid){
		Map<String, Object> hashMap = new HashMap<String, Object>();
		List<Map<Integer, String>> partList = new ArrayList<Map<Integer,String>>();
		ActivityModel actModel = new ActivityModel();
		actModel = this.activityMapper.selectByPrimaryKey(actId);
		MemRoleModel roleModel = new MemRoleModel();
		roleModel = this.memRoleModelMapper.selectByMemberId(uid);
		partList = this.actPushRecModelMapper.getSelectedBranches(roleModel.getRelationRole(),actId);
		hashMap.put("actModel",actModel);
		hashMap.put("partList", partList);
		return hashMap;
		
	}
	//后台编辑活动信息
	public int updateActByPage(HttpServletRequest request,ActivityModel actModel,Integer[] brList){
		this.activityMapper.updateByPrimaryKeySelective(actModel);
		
		for(Integer brId:brList){
		
		}
		return 0;
		
	}
}
