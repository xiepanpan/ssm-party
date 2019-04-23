package com.baobao.common.model.andshow;

import java.util.List;


public class TaskDetail extends TaskList{
	
	private String taskDes;
	
	private List<TaskUserState> userStates;
	
	private List<TaskFeedback> taskFeedbacks;
	
	private List<AppTaskFile> files;

	public String getTaskDes() {
		return taskDes;
	}

	public void setTaskDes(String taskDes) {
		this.taskDes = taskDes;
	}

	public List<TaskUserState> getUserStates() {
		return userStates;
	}

	public void setUserStates(List<TaskUserState> userStates) {
		this.userStates = userStates;
	}


	public List<TaskFeedback> getTaskFeedbacks() {
		return taskFeedbacks;
	}

	public void setTaskFeedbacks(List<TaskFeedback> taskFeedbacks) {
		this.taskFeedbacks = taskFeedbacks;
	}

	public List<AppTaskFile> getFiles() {
		return files;
	}

	public void setFiles(List<AppTaskFile> files) {
		this.files = files;
	}
	
	
	
}
