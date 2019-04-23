package com.baobao.common.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.baobao.common.cmd.ActCmd;
import com.baobao.common.mapping.ActTypeRelationMapper;
import com.baobao.common.mapping.ActivityBranchMapper;
import com.baobao.common.mapping.ActivityJoinMapper;
import com.baobao.common.mapping.ActivityMapper;
import com.baobao.common.mapping.ActivityTypeMapper;
import com.baobao.common.mapping.GoodMapper;
import com.baobao.common.mapping.SequenceModelMapper;
import com.baobao.common.mapping.TaskFileMapper;
import com.baobao.common.model.Activity;
import com.baobao.common.model.ActivityType;
import com.baobao.common.model.andshow.ActComment;
import com.baobao.common.model.andshow.ActCount;
import com.baobao.common.model.andshow.ActDetail;
import com.baobao.common.model.andshow.ActList;
import com.baobao.common.model.andshow.FinishedActList;
import com.baobao.common.model.andshow.NewActList;

@Service
public class ActService {

	@Autowired
	private ActivityMapper activityMapper;

	@Autowired
	private ActivityJoinMapper activityJoinMapper;

	@Autowired
	private ActivityTypeMapper activityTypeMapper;

	@Autowired
	private ActTypeRelationMapper actTypeRelationMapper;

	@Autowired
	private SequenceModelMapper sequenceModelMapper;

	@Autowired
	private ActivityBranchMapper activityBranchMapper;
	
	@Autowired
	private TaskFileMapper taskFileMapper;
	
	@Autowired
	private GoodMapper goodMapper;

	@Autowired
	private HttpServletRequest request;
	
	/**
	 * 
	 * 保存活动分类
	 * 
	 * @author 袁子龙（15071746320）
	 * @param activityType
	 *            活动
	 * @return boolean
	 * @date 2018年6月4日
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public Boolean saveActType(ActivityType activityType) {
		if (null != activityType.getActivityTypeId()) {
			if (activityTypeMapper.updateByPrimaryKeySelective(activityType) > 0) {
				return true;
			} else {
				return false;
			}
		} else {
			if (activityTypeMapper.insertSelective(activityType) > 0) {
				return true;
			} else {
				return false;
			}
		}
	}
	
	/**
	 * 
	 * 删除活动分类
	 * @author 袁子龙（15071746320）
	 * @param id 活动分类的id
	 * @return
	 * @date 2018年6月5日
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public Boolean deleteById(Integer id) {
		if (activityTypeMapper.deleteById(id)>0) {
			return true;
		}else {
			return  false;
		}
	}
	
	

	/**
	 * 
	 * 获取所有的活动分类
	 * 
	 * @author 袁子龙（15071746320）
	 * @param
	 * @return
	 * @date 2018年6月4日
	 */
	@Transactional(readOnly = true)
	public List<ActivityType> getType() {
		return activityTypeMapper.getAllType();
	}
	
	
	/**
	 * 
	 * 根据id获取活动分类
	 * @author 袁子龙（15071746320）
	 * @param
	 * @return
	 * @date 2018年6月4日
	 */
	@Transactional(readOnly = true)
	public ActivityType getTypeById(Integer id) {
		return activityTypeMapper.selectByPrimaryKey(id);
	}
	
	/**
	 * 
	 * 创建活动
	 * @author 袁子龙（15071746320）
	 * @param activity 活动实体类
	 * @param typeIds 活动分类的id集合
	 * @param userIds 活动参与者的id集合
	 * @param fileIds 活动附件的id集合
	 * @param branchIds 活动所属支部的id集合
	 * @return Boolean 布尔值
	 * @date 2018年6月4日
	 */
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
	public Boolean createAct(Activity activity, Integer[] typeIds,
			Integer[] userIds, Integer[] fileIds,Integer[] branchIds) {
		Integer userId = (Integer) request.getSession().getAttribute("userId");
		Integer actId = Integer.parseInt(sequenceModelMapper.getId());
		activity.setActivityCreateUserId(userId);
		activity.setActivityId(actId);
		if (activityMapper.insertSelective(activity) == 1
				&& activityJoinMapper.addJoin(actId, userIds) == userIds.length
				&& actTypeRelationMapper.addRelation(actId, typeIds)==typeIds.length
				&& activityBranchMapper.addBranch(actId, branchIds)==branchIds.length) {
			if (null!=fileIds&&fileIds.length>0) {
				return taskFileMapper.insertTask(fileIds, actId)==fileIds.length?true:false;
			}
			return true;
		}else {
			return false;
		}
	}

	/**
	 * 
	 * 获取进行中的活动
	 * @author 袁子龙（15071746320）
	 * @param 
	 * @return
	 * @date 2018年6月5日
	 */
	public List<NewActList> getDoingActLists(){
		Integer userId = (Integer) request.getSession().getAttribute("userId");
		return activityMapper.getDoingActList(userId);
	}
	
	
	public List<ActComment> getActComments(Integer actId){
		Integer userId = (Integer) request.getSession().getAttribute("userId");
		return activityMapper.getComment(actId,userId);
	}
	
	/**
	 * 
	 * 获取已完成的活动
	 * @author 袁子龙（15071746320）
	 * @param typeId 活动分类id
	 * @return
	 * @date 2018年6月5日
	 */
	public List<NewActList> getFinshedActLists(Integer typeId){
		Integer userId = (Integer) request.getSession().getAttribute("userId");
		ActCmd cmd = new ActCmd();
		cmd.setUserId(userId);
		cmd.setTypeId(typeId);
		return activityMapper.getFinishedActList(cmd);
	}
	
	/**
	 * 
	 * 获取活动详情 
	 * @author 袁子龙（15071746320）
	 * @param id 活动id
	 * @return ActDetail 活动详情model
	 * @date 2018年6月6日
	 */
	public ActDetail getActDetail(Integer id){
		Integer userId = (Integer) request.getSession().getAttribute("userId");
		return activityMapper.getActDetail(id, userId);
	}
	
	
	/**
	 * 
	 * 活动统计
	 * @author 袁子龙（15071746320）
	 * @param 
	 * @return
	 * @date 2018年6月6日
	 */
	public List<ActCount> getActCounts(){
		return activityMapper.getActCount();
	}
}
