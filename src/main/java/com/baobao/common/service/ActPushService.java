package com.baobao.common.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

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
import com.baobao.common.model.ActivityMobile;
import com.baobao.common.model.ActivityModel;
import com.baobao.common.model.MemRoleModel;
import com.baobao.common.model.MemberInfoModel;

@Service
public class ActPushService {
	@Autowired
	private MemRoleModelMapper memRoleMapper;
	@Autowired
	private MemberInfoModelMapper memInfoMapper;
	@Autowired
	private BranchInfoModelMapper branchMapper;
	@Autowired
	private PartyBranchModelMapper partyBranchMapper;
	@Autowired
	private SequenceModelMapper sequenceModelMapper;
	@Autowired
	private ActPushRecModelMapper actPushRecModelMapper;
	@Autowired
	private ActRecordModelMapper actRecordMapper;
	@Autowired
	private ActivityModelMapper  activityMapper;
	@Autowired
	private TempActInfoModelMapper tempActMapper;
	/**
	 * 获取子支部ID或者子支部的成员ID的Map集合
	 * @return
	 */
	public List<Map<Integer,String>> getChildInfo(Integer uid){
		List<Map<Integer,String>> result = new ArrayList<Map<Integer,String>>();
		MemRoleModel roleModel = new MemRoleModel();
		roleModel = memRoleMapper.selectByMemberId(uid);
		if(roleModel!=null&&roleModel.getRelationRole()==1){
			result = this.memInfoMapper.getChildInfo(roleModel.getRelationPartBranchId());
		}else if(roleModel!=null&&roleModel.getRelationRole()==2){
			result	= this.branchMapper.getBranchChildId(roleModel.getRelationPartBranchId());
		}else if(roleModel!=null&&roleModel.getRelationRole()==3){
			result = this.partyBranchMapper.getChildList(1);
		}
		return result;
		
	}
	/**
	 * 后台发布活动仅限分党书记及校党委书记
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public int publishActivity(ActivityModel acModel,List<Integer> brList,Integer id){
		int i = 0;
		int j = 0;
		MemRoleModel mRole = new MemRoleModel();
		MemberInfoModel memberInfoModel = memInfoMapper.selectByPrimaryKey(id);
		mRole = memRoleMapper.selectByMemberId(id);
		String Id=sequenceModelMapper.getId();
		Integer acId = Integer.parseInt(Id);
		acModel.setActivityStatus(1);
		acModel.setActivityGrade(mRole.getRelationRole());
		System.out.println(acModel.getActivityGrade());
		acModel.setActivityId(acId);
		acModel.setActivityLeaderid(id);
		acModel.setActivityLeadername(memberInfoModel.getMemberName());
		acModel.setActivityCreatetime(new Date());
		 j = activityMapper.insertSelective(acModel);
		 List<ActPushRecModel> actPushList = new ArrayList<ActPushRecModel>();
		if(brList!=null&&brList.size()>0){
			for(Integer childId:brList){
				ActPushRecModel actPushModel = new ActPushRecModel();
				actPushModel.setActPushActid(acId);
				actPushModel.setActPushBranchid(childId);
				actPushModel.setActPushTime(new Date());
				actPushModel.setActPushType(mRole.getRelationRole());
				actPushList.add(actPushModel);
			}
		i =	this.actPushRecModelMapper.batchInsertRecord(actPushList);
		}
		return i*j;
		
	}
	/**
	 * 获取当前支部的活动信息
	 */
	public List<ActivityMobile> getActByManager(Integer uid,Integer brId,Integer page){
		List<ActivityMobile> acModel = new ArrayList<ActivityMobile>();
		List<Map<Integer,String>>  list = new ArrayList<Map<Integer,String>>();
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		map.put("uid", uid);
		map.put("brId",brId);
		Integer start=((page-1)*10);
		Integer end =10;
		map.put("start", start);
		map.put("end", end);
		MemRoleModel roleModel = new MemRoleModel();
		roleModel = this.memRoleMapper.selectByMemberId(uid);
		if(roleModel.getRelationRole()==3){
		map.put("status", 3);
		}else if(roleModel.getRelationRole()==2){
		map.put("status", 2);
		}else{
		map.put("status", 1);
		}
		acModel = this.activityMapper.getActByManager(map);
		return acModel;
		
	}
	public List<ActivityModel> getActivities(Integer uid){
		List<ActivityModel> ActivityList = new ArrayList<ActivityModel>();
		ActivityList = this.activityMapper.getActivitiesByUserId(uid);
		return ActivityList;
	}
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public int deleteActivityByRole(Integer uid,Integer actId){
		int i = 0;
		MemRoleModel roleModel = new MemRoleModel();
		roleModel = this.memRoleMapper.selectByMemberId(uid);
		ActivityModel actModel = new ActivityModel();
		if(roleModel!=null&&roleModel.getRelationRole()!=null){
			Integer roleGrade = roleModel.getRelationRole();
			//判断活动状态为保存或是发布
			actModel = this.activityMapper.selectByPrimaryKey(actId);
			if(actModel!=null&&actModel.getActivityStatus()!=null){
			Integer publishStatus = actModel.getActivityStatus();
			this.activityMapper.deleteByPrimaryKey(actId);//第二步 活动表中删除记录
			//第二步 根据角色删除判断需要删除的活动记录信息
			if(roleGrade == 1){//最低支部
				if(publishStatus==0){//保存状态，删除保存的记录信息
					i = this.tempActMapper.deleteByActId(actId);
				}else if(publishStatus==1){//已发布，删除记录表中的信息
					i = this.actRecordMapper.deleteRecordByActId(actId);
				}
			}else if(roleGrade >1){//可能为分院党支部或者是校党支部
				if(publishStatus==0){//保存状态，删除保存的记录信息
					i = this.tempActMapper.deleteByActId(actId);
				}else if(publishStatus==1){//已发布，删除记录表中的信息
					i = this.actPushRecModelMapper.deleteByActId(actId);
					this.actRecordMapper.deleteRecordByActId(actId);
				}
			}
			}
		}
		return i;
		
	}
	//批量删除活动
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public int deleteBatch(Integer uid,Integer[] ids){
		int i = 0;
		MemRoleModel roleModel = new MemRoleModel();
		roleModel = this.memRoleMapper.selectByMemberId(uid);
		ActivityModel actModel = new ActivityModel();
		if(roleModel!=null&&roleModel.getRelationRole()!=null){
			Integer roleGrade = roleModel.getRelationRole();
			//判断活动状态为保存或是发布
			for(int j = 0;j<ids.length;j++){
				actModel = this.activityMapper.selectByPrimaryKey(ids[j]);
				if(actModel!=null&&actModel.getActivityStatus()!=null){
				Integer publishStatus = actModel.getActivityStatus();
				try {
					this.activityMapper.deleteByPrimaryKey(ids[j]);//第二步 活动表中删除记录
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return 0;
				}
				//第二步 根据角色删除判断需要删除的活动记录信息
				if(roleGrade == 1){//最低支部
					if(publishStatus==0){//保存状态，删除保存的记录信息
						try {
							i = this.tempActMapper.deleteByActId(ids[j]);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							return 0;
						}
					}else if(publishStatus==1){//已发布，删除记录表中的信息
						try {
							i = this.actRecordMapper.deleteRecordByActId(ids[j]);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							return 0;
						}
					}
				}else if(roleGrade >1){//可能为分院党支部或者是校党支部
					if(publishStatus==0){//保存状态，删除保存的记录信息
						try {
							i = this.tempActMapper.deleteByActId(ids[j]);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							return 0;
						}
					}else if(publishStatus==1){//已发布，删除记录表中的信息
						try {
							i = this.actPushRecModelMapper.deleteByActId(ids[j]);
							this.actRecordMapper.deleteRecordByActId(ids[j]);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							return 0;
						}
					}
				}
			}
			}
		}
		return ids.length;
	}
	public List<Map<Integer,String>> getSelectedBranches(Integer actId,Integer uid){
		List<Map<Integer, String>> result = new ArrayList<Map<Integer,String>>();
		MemRoleModel roleModel = new MemRoleModel();
		roleModel = this.memRoleMapper.selectByMemberId(uid);
		result = this.actPushRecModelMapper.getSelectedBranches(roleModel.getRelationRole(),actId);
		return result;
	}
	//后台修改活动
	public int updateActivity(ActivityModel actModel){
		int i = 0;
		int j = 0;
		boolean flag = false;
		i = this.activityMapper.updateByPrimaryKeySelective(actModel);
		/*List<Map<Integer, String>> result = new ArrayList<Map<Integer,String>>();
		MemRoleModel roleModel = new MemRoleModel();
		roleModel = this.memRoleMapper.selectByMemberId(uid);
		result = this.actPushRecModelMapper.getSelectedBranches(roleModel.getRelationRole(),actModel.getActivityId());
		for(Integer joinId:joinList){
			for(int k =0;k<result.size();k++){
			 flag = result.get(k).containsKey(joinId);
			 if(flag==true){
				 break;
			 }
			}
			if(flag==false){
				
			}
		}*/
		return i;
		
	}
}
