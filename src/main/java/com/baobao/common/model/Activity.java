package com.baobao.common.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class Activity {
    private Integer activityId;

    private String activityName;

    private String activityContent;

    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date activityStartTime;

    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date activityEndTime;

    private String activityAddress;

    private Byte activitySign;

    private Integer activityCreateUserId;

    private Integer activityState;

    public Integer getActivityId() {
        return activityId;
    }

    public void setActivityId(Integer activityId) {
        this.activityId = activityId;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName == null ? null : activityName.trim();
    }

    public String getActivityContent() {
        return activityContent;
    }

    public void setActivityContent(String activityContent) {
        this.activityContent = activityContent == null ? null : activityContent.trim();
    }

    public Date getActivityStartTime() {
        return activityStartTime;
    }

    public void setActivityStartTime(Date activityStartTime) {
        this.activityStartTime = activityStartTime;
    }

    public Date getActivityEndTime() {
        return activityEndTime;
    }

    public void setActivityEndTime(Date activityEndTime) {
        this.activityEndTime = activityEndTime;
    }

    public String getActivityAddress() {
        return activityAddress;
    }

    public void setActivityAddress(String activityAddress) {
        this.activityAddress = activityAddress == null ? null : activityAddress.trim();
    }

    public Byte getActivitySign() {
        return activitySign;
    }

    public void setActivitySign(Byte activitySign) {
        this.activitySign = activitySign;
    }

    public Integer getActivityCreateUserId() {
        return activityCreateUserId;
    }

    public void setActivityCreateUserId(Integer activityCreateUserId) {
        this.activityCreateUserId = activityCreateUserId;
    }

    public Integer getActivityState() {
        return activityState;
    }

    public void setActivityState(Integer activityState) {
        this.activityState = activityState;
    }
}