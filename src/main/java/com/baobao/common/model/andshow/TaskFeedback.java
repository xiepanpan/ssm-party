package com.baobao.common.model.andshow;

import java.util.Date;
import java.util.List;

import com.baobao.common.model.TaskFile;

public class TaskFeedback extends AppUser{
	
	private Date time;
	
	private String taskDes;
	
	List<AppTaskFile> files;

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public String getTaskDes() {
		return taskDes;
	}

	public void setTaskDes(String taskDes) {
		this.taskDes = taskDes;
	}

	public List<AppTaskFile> getFiles() {
		return files;
	}

	public void setFiles(List<AppTaskFile> files) {
		this.files = files;
	}
	
	
	
}
