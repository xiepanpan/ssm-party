package com.baobao.common.model;

public class ActivityType {
    private Integer activityTypeId;

    private String activityTypeName;

    private Integer activityTypeState;

    public Integer getActivityTypeId() {
        return activityTypeId;
    }

    public void setActivityTypeId(Integer activityTypeId) {
        this.activityTypeId = activityTypeId;
    }

    public String getActivityTypeName() {
        return activityTypeName;
    }

    public void setActivityTypeName(String activityTypeName) {
        this.activityTypeName = activityTypeName == null ? null : activityTypeName.trim();
    }

    public Integer getActivityTypeState() {
        return activityTypeState;
    }

    public void setActivityTypeState(Integer activityTypeState) {
        this.activityTypeState = activityTypeState;
    }
}