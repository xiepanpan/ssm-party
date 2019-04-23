package com.baobao.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.baobao.common.model.ResultModel;
import com.baobao.common.model.Task;
import com.baobao.common.model.andshow.TaskFileFormat;
import com.baobao.common.model.andshow.TaskList;
import com.baobao.common.service.TaskService;


@Controller
@RequestMapping("/task")
public class TaskController {
	
	private Logger logger = LoggerFactory.getLogger(TaskController.class);
	
	@Autowired
	private TaskService taskService;
	
	
	/*
	 * 获取任务的组织
	 */
	@RequestMapping("/getTaskBranch")
	@ResponseBody
	public Object getTaskBranch(){
		ResultModel resultModel = new ResultModel(); 
		try {
			resultModel.setData(taskService.getTaskBranchs());
			resultModel.setMessage("获取任务组织成功");
			resultModel.setStatus(true);
		} catch (Exception e) {
			logger.info("获取任务组织发生异常");
			e.printStackTrace();
			resultModel.setStatus(false);
			resultModel.setMessage("获取任务组织异常");
		}
		return resultModel;
	}
	
	
	/*
	 * 获取任务的联系人
	 */
	@RequestMapping("/getTaskContacts")
	@ResponseBody
	public Object getTaskContacts(Integer[] ids){
		ResultModel resultModel = new ResultModel(); 
		try {
			resultModel.setData(taskService.getTaskContacts(ids));
			resultModel.setMessage("获取任务组织联系人成功");
			resultModel.setStatus(true);
		} catch (Exception e) {
			logger.info("获取任务组织联系人发生异常");
			e.printStackTrace();
			resultModel.setStatus(false);
			resultModel.setMessage("获取任务组织联系人异常");
		}
		return resultModel;
	}
	
	
	
	/*
	 * 用户创建任务
	 */
	@RequestMapping("/create")
	@ResponseBody
	public Object ctreate(Task task,Integer[] userIds,@RequestParam(required=false)Integer[] fileIds){
		ResultModel<Task> resultModel = new ResultModel<Task>(); 
 		try {
			if (taskService.createTask(task, userIds, fileIds)) {
				resultModel.setStatus(true);
				resultModel.setMessage("创建任务成功");
			}else {
				resultModel.setStatus(false);
				resultModel.setMessage("创建任务失败");
			}
		} catch (Exception e) {
			logger.info("创建任务发生异常");
			e.printStackTrace();
			resultModel.setStatus(false);
			resultModel.setMessage("创建任务异常");
		}
		
		return resultModel;
	}
	
	
	/*
	 * 用户创建任务
	 */
	@RequestMapping("/create_ios")
	@ResponseBody
	public Object ctreate(Task task,Integer[] userIds,@RequestParam(required=false)MultipartFile[] files){
		ResultModel<Task> resultModel = new ResultModel<Task>(); 
 		try {
 			List<Integer> list = taskService.upload(files);
			if (taskService.createTask(task, userIds, list)) {
				resultModel.setStatus(true);
				resultModel.setMessage("创建任务成功");
			}else {
				resultModel.setStatus(false);
				resultModel.setMessage("创建任务失败");
			}
		} catch (Exception e) {
			logger.info("创建任务发生异常");
			e.printStackTrace();
			resultModel.setStatus(false);
			resultModel.setMessage("创建任务异常");
		}
		
		return resultModel;
	}
	
	/*
	 * 获取任务列表   type   1 获取要处理的任务 2 获取自己发布的任务 
	 */
	@RequestMapping("/getTasks")
	@ResponseBody
	public Object getTasks(Integer type){
		ResultModel<List<TaskList>> resultModel = new ResultModel<List<TaskList>>(); 
		try {
			resultModel.setStatus(true);
			switch (type) {
			case 1:
				resultModel.setData(taskService.getWillList());
				resultModel.setMessage("获取数据成功");
				break;
			case 2:
				resultModel.setData(taskService.getMyList());
				resultModel.setMessage("获取数据成功");
				break;
			default:
				resultModel.setStatus(false);
				resultModel.setMessage("没有这个获取类型");
				break;
			}
		} catch (Exception e) {
			logger.info("获取任务列表发生异常");
			e.printStackTrace();
			resultModel.setStatus(false);
			resultModel.setMessage("获取任务异常");
		}
		System.out.println(resultModel.getData().size());
		return resultModel;
	}
	
	/*
	 * 获取任务的详情
	 */
	@RequestMapping("/getTaskDetail")
	@ResponseBody
	public Object getTaskDetail(Integer taskId){
		ResultModel resultModel = new ResultModel<>(); 
		try {
			resultModel.setStatus(true);
			resultModel.setData(taskService.getTaskDetail(taskId));
			resultModel.setMessage("获取任务详情");
		} catch (Exception e) {
			logger.info("获取任务详情发生异常");
			e.printStackTrace();
			resultModel.setStatus(false);
			resultModel.setMessage("获取任务详情异常");
		}
		return resultModel;
	}
	
	
	
	/*
	 * 接收者接收任务
	 */
	@RequestMapping("/acceptTask")
	@ResponseBody
	public Object acceptTask(Integer taskId){
		ResultModel resultModel = new ResultModel<>(); 
		try {
			if (taskService.getTaskByDeadline(taskId)) {
				resultModel.setStatus(false);
				resultModel.setMessage("任务已截至，无法完成");
				return resultModel;
			}
			if (taskService.accept(taskId)) {
				resultModel.setStatus(true);
				resultModel.setMessage("接收任务成功");
			}else {
				resultModel.setStatus(false);
				resultModel.setMessage("接收任务失败");
			}
		} catch (Exception e) {
			logger.info("接收任务发生异常");
			e.printStackTrace();
			resultModel.setStatus(false);
			resultModel.setMessage("接收任务异常");
		}
		return resultModel;
	}
	
	/*
	 * 接收者完成任务
	 */
	@RequestMapping("/finshTask")
	@ResponseBody
	public Object finshTask(Integer taskId,String des,Integer[] fileIds){
		ResultModel resultModel = new ResultModel<>(); 
		try {
			if (taskService.getTaskByDeadline(taskId)) {
				resultModel.setStatus(false);
				resultModel.setMessage("任务已截至，无法完成");
				return resultModel;
			}
			if (taskService.finsh(taskId, des, fileIds)) {
				resultModel.setStatus(true);
				resultModel.setMessage("完成任务成功");
			}else {
				resultModel.setStatus(false);
				resultModel.setMessage("完成任务失败");
			}
		} catch (Exception e) {
			logger.info("完成任务发生异常");
			e.printStackTrace();
			resultModel.setStatus(false);
			resultModel.setMessage("完成任务异常");
		}
		return resultModel;
	}
	
	
	
	@RequestMapping("/finshTask_ios")
	@ResponseBody
	public Object finshTask_ios(Integer taskId,String des,@RequestParam(required=false,value="files")MultipartFile[] files){
		ResultModel resultModel = new ResultModel<>(); 
		try {
			if (taskService.getTaskByDeadline(taskId)) {
				resultModel.setStatus(false);
				resultModel.setMessage("任务已截至，无法完成");
				return resultModel;
			}
		    List<Integer> ids = taskService.upload(files);
			if (taskService.finsh(taskId, des, ids)) {
				resultModel.setStatus(true);
				resultModel.setMessage("完成任务成功");
			}else {
				resultModel.setStatus(false);
				resultModel.setMessage("完成任务失败");
			}
		} catch (Exception e) {
			logger.info("完成任务发生异常");
			e.printStackTrace();
			resultModel.setStatus(false);
			resultModel.setMessage("完成任务异常");
		}
		return resultModel;
	}
	
	/*
	 * 发布人完结任务
	 */
	@RequestMapping("/overTask")
	@ResponseBody
	public Object overTask(Integer taskId){
		ResultModel resultModel = new ResultModel<>(); 
		try {
			if (!taskService.getCondition(taskId)) {
				resultModel.setStatus(false);
				resultModel.setMessage("完结任务条件未达到");
				return resultModel;
			}
			if (taskService.overTask(taskId)) {
				resultModel.setStatus(true);
				resultModel.setMessage("完结任务成功");
			}else {
				resultModel.setStatus(false);
				resultModel.setMessage("完结任务失败");
			}
		} catch (Exception e) {
			logger.info("完结任务发生异常");
			e.printStackTrace();
			resultModel.setStatus(false);
			resultModel.setMessage("完结任务异常");
		}
		return resultModel;
	}
	
}
