package com.baobao.common.model.andshow;

import java.util.Date;

public class TaskList {
	
	private Integer taskId;
	
	private String taskTitle;
	
	private String taskCreaterName;
	
	private Integer taskState;
	
	private Date taskDeadLine;

	
	public Integer getTaskId() {
		return taskId;
	}

	public void setTaskId(Integer taskId) {
		this.taskId = taskId;
	}

	public String getTaskTitle() {
		return taskTitle;
	}

	public void setTaskTitle(String taskTitle) {
		this.taskTitle = taskTitle;
	}

	public String getTaskCreaterName() {
		return taskCreaterName;
	}

	public void setTaskCreaterName(String taskCreaterName) {
		this.taskCreaterName = taskCreaterName;
	}

	public Integer getTaskState() {
		return taskState;
	}

	public void setTaskState(Integer taskState) {
		this.taskState = taskState;
	}

	public Date getTaskDeadLine() {
		return taskDeadLine;
	}

	public void setTaskDeadLine(Date taskDeadLine) {
		this.taskDeadLine = taskDeadLine;
	}

	
	
	
	
	
}
