package com.baobao.common.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.baobao.common.mapping.BranchInfoModelMapper;
import com.baobao.common.mapping.MemberInfoModelMapper;
import com.baobao.common.mapping.SequenceModelMapper;
import com.baobao.common.mapping.TaskContactsMapper;
import com.baobao.common.mapping.TaskFileMapper;
import com.baobao.common.mapping.TaskFinshFileMapper;
import com.baobao.common.mapping.TaskJoinMapper;
import com.baobao.common.mapping.TaskMapper;
import com.baobao.common.model.MemberInfoModel;
import com.baobao.common.model.Task;
import com.baobao.common.model.TaskFile;
import com.baobao.common.model.TaskJoin;
import com.baobao.common.model.andshow.Contacts;
import com.baobao.common.model.andshow.TaskBranch;
import com.baobao.common.model.andshow.TaskDetail;
import com.baobao.common.model.andshow.TaskFileFormat;
import com.baobao.common.model.andshow.TaskList;
import com.baobao.util.FileDeal;
import com.baobao.util.GetLocalIp;

@Service
public class TaskService {

	private Logger logger = LoggerFactory.getLogger(TaskService.class);
	
	@Autowired
	private TaskMapper taskMapper;
	
	@Autowired
	private TaskJoinMapper taskJoinMapper;
	
	@Autowired
	private TaskFileMapper taskFileMapper;
	
	@Autowired
	private HttpServletRequest request;
	
	@Autowired
	private SequenceModelMapper sequenceModelMapper;
	
	@Autowired
	private MemberInfoModelMapper memberInfoModelMapper;
	
	@Autowired
	private BranchInfoModelMapper branchInfoModelMapper;
	
	@Autowired
	private TaskFinshFileMapper taskFinshFileMapper;
	
	@Autowired
	private TaskContactsMapper taskContactsMapper;
	
	/**
	 * 
	 * 获取任务的组织
	 * @author 袁子龙（15071746320）
	 * @param 
	 * @return
	 * @date 2018年5月29日
	 */
	@Transactional(readOnly=true)
	public List<TaskBranch> getTaskBranchs(){
		return branchInfoModelMapper.getTaskBranch();
	}
	
	public List<Contacts> getTaskContacts(Integer[] ids){
		return taskContactsMapper.getTaskContacts(ids);
	}
	
	/**
	 * 
	 * 上传文件
	 * @author 袁子龙（15071746320）
	 * @param 
	 * @return
	 * @date 2018年5月29日
	 */
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
	public TaskFile uploadTaskFile(MultipartFile file){
		Integer userId = (Integer) request.getSession().getAttribute("userId");
		MemberInfoModel user = memberInfoModelMapper.getUserInfoByUserId(userId);
		TaskFile taskFile = FileDeal.getIntence().uploadFile(request, file);
		if (null!=taskFile) {
			taskFile.setTaskFileName("("+user.getMemberName()+")"+taskFile.getTaskFileName());
		}
		taskFile.setTaskFileId(Integer.parseInt(sequenceModelMapper.getId()));
		taskFileMapper.insert(taskFile);
		return taskFile;
		
	}
	
	
	/**
	 * 
	 * 新建任务 
	 * @author 袁子龙（15071746320）
	 * @param task 任务model
	 * @param userIds 任务参与者id集合
	 * @param files 任务文件集合
	 * @return Boolean
	 * @date 2018年5月28日
	 */
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
	public Boolean createTask(Task task,Integer[] userIds,List<Integer> fileIds){
		Integer userId = (Integer) request.getSession().getAttribute("userId");
		MemberInfoModel user = memberInfoModelMapper.getUserInfoByUserId(userId);
		logger.info(user.getMemberName()+"创建任务");
		Integer taskId =Integer.parseInt(sequenceModelMapper.getId()) ;
		task.setTaskCreateUserId(userId);
		task.setTaskCreateTime(new Date());
		task.setTaskId(taskId);
		
		if (null!=fileIds&&fileIds.size()>0) {
			if (taskMapper.insertSelective(task)==1&&taskJoinMapper.insertList(taskId, userIds)==userIds.length&&taskFileMapper.insertTask(fileIds,taskId)==fileIds.size()) {
				return true;
			}
		}else {
			if (taskMapper.insertSelective(task)==1&&taskJoinMapper.insertList(taskId, userIds)==userIds.length) {
				return true;
			}
		}
		
		return false;
	}
	
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
	public Boolean createTask(Task task,Integer[] userIds,String[] files){
		Integer userId = (Integer) request.getSession().getAttribute("userId");
		MemberInfoModel user = memberInfoModelMapper.getUserInfoByUserId(userId);
		logger.info(user.getMemberName()+"创建任务");
		Integer taskId =Integer.parseInt(sequenceModelMapper.getId()) ;
		task.setTaskCreateUserId(userId);
		task.setTaskCreateTime(new Date());
		task.setTaskId(taskId);
		List<TaskFile> fileList = new ArrayList<TaskFile>();
		for (String file : files) {
			
			String fileName = "("+user.getMemberName()+")"+file;
			TaskFile taskFile=new TaskFile();
			taskFile.setTaskFileBelong(taskId);
			taskFile.setTaskFileName(fileName);
			taskFile.setTaskFileUrl(file);
			fileList.add(taskFile);

		}
		if (taskMapper.insertSelective(task)==1&&taskJoinMapper.insertList(taskId, userIds)==userIds.length&&taskFileMapper.insertList(fileList)==fileList.size()) {
			return true;
		}
		return false;
	}
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
	public Boolean createTask(Task task,Integer[] userIds,TaskFileFormat taskFileFormat){
		Integer userId = (Integer) request.getSession().getAttribute("userId");
		MemberInfoModel user = memberInfoModelMapper.getUserInfoByUserId(userId);
		logger.info(user.getMemberName()+"创建任务");
		Integer taskId =Integer.parseInt(sequenceModelMapper.getId()) ;
		task.setTaskCreateUserId(userId);
		task.setTaskCreateTime(new Date());
		task.setTaskId(taskId);
		List<TaskFile> fileList = new ArrayList<TaskFile>();
		if (null!=taskFileFormat.getTaskFiles()) {
			for (TaskFile taskFile : taskFileFormat.getTaskFiles()) {
				taskFile.setTaskFileBelong(taskId);
				fileList.add(taskFile);
			}
		}
		if (fileList.size()>0) {
			if (taskMapper.insertSelective(task)==1&&taskJoinMapper.insertList(taskId, userIds)==userIds.length&&taskFileMapper.insertList(fileList)==fileList.size()) {
				return true;
			}
		}else {
			if (taskMapper.insertSelective(task)==1&&taskJoinMapper.insertList(taskId, userIds)==userIds.length) {
				return true;
			}
		}
		
		return false;
	}
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
	public Boolean createTask(Task task,Integer[] userIds,Integer[] fileIds){
		Integer userId = (Integer) request.getSession().getAttribute("userId");
		MemberInfoModel user = memberInfoModelMapper.getUserInfoByUserId(userId);
		logger.info(user.getMemberName()+"创建任务");
		Integer taskId =Integer.parseInt(sequenceModelMapper.getId()) ;
		task.setTaskCreateUserId(userId);
		task.setTaskCreateTime(new Date());
		task.setTaskId(taskId);
		if (null!=fileIds&&fileIds.length>0) {
			if (taskMapper.insertSelective(task)==1&&taskJoinMapper.insertList(taskId, userIds)==userIds.length&&taskFileMapper.insertTask(fileIds, taskId)==fileIds.length) {
				return true;
			}
		}else {
			if (taskMapper.insertSelective(task)==1&&taskJoinMapper.insertList(taskId, userIds)==userIds.length) {
				return true;
			}
		}
		
		return false;
	}
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
	public Boolean createTask_ios(Task task,Integer[] userIds,Integer[] fileIds){
		Integer userId = (Integer) request.getSession().getAttribute("userId");
		MemberInfoModel user = memberInfoModelMapper.getUserInfoByUserId(userId);
		logger.info(user.getMemberName()+"创建任务");
		Integer taskId =Integer.parseInt(sequenceModelMapper.getId()) ;
		task.setTaskCreateUserId(userId);
		task.setTaskCreateTime(new Date());
		task.setTaskId(taskId);
		if (null!=fileIds&&fileIds.length>0) {
			if (taskMapper.insertSelective(task)==1&&taskJoinMapper.insertList(taskId, userIds)==userIds.length&&taskFileMapper.insertTask(fileIds, taskId)==fileIds.length) {
				return true;
			}
		}else {
			if (taskMapper.insertSelective(task)==1&&taskJoinMapper.insertList(taskId, userIds)==userIds.length) {
				return true;
			}
		}
		
		return false;
	}
	/**
	 * 
	 * 获取待做的任务
	 * @author 袁子龙（15071746320）
	 * @param 
	 * @return
	 * @date 2018年5月28日
	 */
	@Transactional(readOnly=true)
	public List<TaskList> getWillList(){
		Integer userId = (Integer) request.getSession().getAttribute("userId");
		return taskMapper.getWillTaskLit(userId);
	}
	
	/**
	 * 
	 * 获取我发起的任务
	 * @author 袁子龙（15071746320）
	 * @param 
	 * @return
	 * @date 2018年5月28日
	 */
	@Transactional(readOnly=true)
	public List<TaskList> getMyList(){
		Integer userId = (Integer) request.getSession().getAttribute("userId");
		return taskMapper.getMyTaskLit(userId);
	}
	
	
	/**
	 * 
	 * 根据id获取任务详情
	 * @author 袁子龙（15071746320）
	 * @param taskId 任务id
	 * @return
	 * @date 2018年5月28日
	 */
	@Transactional(readOnly=true)
	public TaskDetail getTaskDetail(Integer taskId){
		Integer userId = (Integer) request.getSession().getAttribute("userId");
		TaskDetail taskDetail = taskMapper.getTaskDetail(taskId);
		TaskJoin taskJoin = taskJoinMapper.selectByTaskAndUser(taskId, userId);
		if (taskJoin!=null) {
			taskDetail.setTaskState(taskJoin.getTaskJoinFinshState());
		}
		
		return taskDetail;
	}
	
	
	/**
	 * 
	 * 发布人完结任务
	 * @author 袁子龙（15071746320）
	 * @param taskId 任务的id 
	 * @return
	 * @date 2018年5月28日
	 */
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
	public Boolean overTask(Integer taskId){
		int i = taskMapper.overTask(taskId);
		List<Integer> list = taskMapper.selectNoSuccess();
		taskMapper.updateState(list);
		return i>0?true:false;
	}
	
	
	public boolean getCondition(Integer taskId){
		Integer i = taskJoinMapper.selectCount(taskId);
		Task task = taskMapper.selectByPrimaryKey(taskId);
		if (i==0||task.getTaskDeadline().getTime()<new Date().getTime()) {
			return true;
		}else {
			return false;
		}
	}
	
	/**
	 * 
	 * 接收任务 
	 * @author 袁子龙（15071746320）
	 * @param taskId 任务id
	 * @return Boolean
	 * @date 2018年5月28日
	 */
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
	public Boolean accept(Integer taskId){
		Integer userId = (Integer) request.getSession().getAttribute("userId");
		
		return  taskJoinMapper.accept(taskId, userId)>0?true:false;
		
	}
	
	
	/**
	 * 
	 * 获取任务是否在截至日期之前
	 * @author 袁子龙（15071746320）
	 * @param taskId 任务id
	 * @return Boolean true 不在  true 在
	 * @date 2018年6月8日
	 */
	public Boolean getTaskByDeadline(Integer taskId){
		return taskMapper.selectByDeadline(taskId)!=null?true:false;
	}
	
	/**
	 * 
	 * 任务完成
	 * @author 袁子龙（15071746320）
	 * @param  taskId 任务id
	 * @param  des 任务描述
	 * @param  files 任务完成上传的文件
	 * @return Boolean
	 * @date 2018年5月28日
	 */
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
	public Boolean finsh(Integer taskId,String des,List<Integer> list){
		Integer userId = (Integer) request.getSession().getAttribute("userId");
		TaskJoin taskJoin = taskJoinMapper.selectByTaskAndUser(taskId, userId);
		taskJoin.setTaskJoinFinshState(1);
		taskJoin.setTaskJoinFinshDes(des);
		taskJoin.setTaskJoinFinshTime(new Date());
		taskFinshFileMapper.addTaskFinishFileList(taskJoin.getTaskJoinId(), list);
		taskJoinMapper.updateByPrimaryKeySelective(taskJoin);
		return true;
	}
	
	public List<Integer> upload(MultipartFile[] files){
		List<Integer> list = new ArrayList<Integer>();
		Integer userId = (Integer) request.getSession().getAttribute("userId");
		if (null!=files&&files.length>0) {
			for (MultipartFile multipartFile : files) {
				MemberInfoModel user = memberInfoModelMapper.getUserInfoByUserId(userId);
				TaskFile taskFile = FileDeal.getIntence().uploadFile(request, multipartFile);
				if (null!=taskFile) {
					taskFile.setTaskFileName("("+user.getMemberName()+")"+taskFile.getTaskFileName());
				}
				Integer id = Integer.parseInt(sequenceModelMapper.getId());
				taskFile.setTaskFileId(id);
				taskFileMapper.insert(taskFile);
				list.add(id);
			}
			
		}
		
		return list;
	}
	

	/**
	 * 
	 * 任务完成
	 * @author 袁子龙（15071746320）
	 * @param  taskId 任务id
	 * @param  des 任务描述
	 * @param  files 任务完成上传的文件
	 * @return Boolean
	 * @date 2018年5月28日
	 */
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
	public Boolean finsh(Integer taskId,String des,Integer[] fileIds){
		Integer userId = (Integer) request.getSession().getAttribute("userId");
		TaskJoin taskJoin = taskJoinMapper.selectByTaskAndUser(taskId, userId);
		taskJoin.setTaskJoinFinshState(1);
		taskJoin.setTaskJoinFinshDes(des);
		taskJoin.setTaskJoinFinshTime(new Date());
		if (null!=fileIds&&fileIds.length>0) {
			taskFinshFileMapper.addTaskFinishFile(taskJoin.getTaskJoinId(), fileIds);
		}
		
		taskJoinMapper.updateByPrimaryKeySelective(taskJoin);
		return true;
	}
	
}
