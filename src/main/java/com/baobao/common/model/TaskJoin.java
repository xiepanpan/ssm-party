package com.baobao.common.model;

import java.util.Date;

public class TaskJoin {
    private Integer taskJoinId;

    private Integer taskJoinBelong;

    private Integer taskJoinUserId;

    private Integer taskJoinFinshState;

    private String taskJoinFinshDes;

    private Date taskJoinFinshTime;

    public Integer getTaskJoinId() {
        return taskJoinId;
    }

    public void setTaskJoinId(Integer taskJoinId) {
        this.taskJoinId = taskJoinId;
    }

    public Integer getTaskJoinBelong() {
        return taskJoinBelong;
    }

    public void setTaskJoinBelong(Integer taskJoinBelong) {
        this.taskJoinBelong = taskJoinBelong;
    }

    public Integer getTaskJoinUserId() {
        return taskJoinUserId;
    }

    public void setTaskJoinUserId(Integer taskJoinUserId) {
        this.taskJoinUserId = taskJoinUserId;
    }

    public Integer getTaskJoinFinshState() {
        return taskJoinFinshState;
    }

    public void setTaskJoinFinshState(Integer taskJoinFinshState) {
        this.taskJoinFinshState = taskJoinFinshState;
    }

    public String getTaskJoinFinshDes() {
        return taskJoinFinshDes;
    }

    public void setTaskJoinFinshDes(String taskJoinFinshDes) {
        this.taskJoinFinshDes = taskJoinFinshDes == null ? null : taskJoinFinshDes.trim();
    }

    public Date getTaskJoinFinshTime() {
        return taskJoinFinshTime;
    }

    public void setTaskJoinFinshTime(Date taskJoinFinshTime) {
        this.taskJoinFinshTime = taskJoinFinshTime;
    }
}