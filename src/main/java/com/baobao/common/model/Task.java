package com.baobao.common.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class Task {
    private Integer taskId;

    private String taskTitle;

    private String taskDes;

    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date taskDeadline;

    private Integer taskCreateUserId;

    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date taskCreateTime;

    private Integer taskFinshState;

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
        this.taskTitle = taskTitle == null ? null : taskTitle.trim();
    }

    public String getTaskDes() {
        return taskDes;
    }

    public void setTaskDes(String taskDes) {
        this.taskDes = taskDes == null ? null : taskDes.trim();
    }

    public Date getTaskDeadline() {
        return taskDeadline;
    }

    public void setTaskDeadline(Date taskDeadline) {
        this.taskDeadline = taskDeadline;
    }

    public Integer getTaskCreateUserId() {
        return taskCreateUserId;
    }

    public void setTaskCreateUserId(Integer taskCreateUserId) {
        this.taskCreateUserId = taskCreateUserId;
    }

    public Date getTaskCreateTime() {
        return taskCreateTime;
    }

    public void setTaskCreateTime(Date taskCreateTime) {
        this.taskCreateTime = taskCreateTime;
    }

    public Integer getTaskFinshState() {
        return taskFinshState;
    }

    public void setTaskFinshState(Integer taskFinshState) {
        this.taskFinshState = taskFinshState;
    }
}