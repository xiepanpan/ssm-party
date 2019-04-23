package com.baobao.common.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class ActivityModel {
    private Integer activityId;

    private String activityTitle;

    private String activityContext;

    private String activityPlace;

    private String activityLeadername;

    private Integer activityLeaderid;
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date activityCreatetime;
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm")
    private Date activityStarttime;
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm")
    private Date activityEndtime;
    
    private Integer activityGrade;
    
    private Integer activityStatus;
    
    private Double latitude;//纬度
    
	private Double longitude;//经度
    public Integer getActivityId() {
        return activityId;
    }

    public void setActivityId(Integer activityId) {
        this.activityId = activityId;
    }

    public String getActivityTitle() {
        return activityTitle;
    }

    public void setActivityTitle(String activityTitle) {
        this.activityTitle = activityTitle == null ? null : activityTitle.trim();
    }

    public String getActivityContext() {
        return activityContext;
    }

    public void setActivityContext(String activityContext) {
        this.activityContext = activityContext == null ? null : activityContext.trim();
    }

    public String getActivityPlace() {
        return activityPlace;
    }

    public void setActivityPlace(String activityPlace) {
        this.activityPlace = activityPlace == null ? null : activityPlace.trim();
    }

    public String getActivityLeadername() {
        return activityLeadername;
    }

    public void setActivityLeadername(String activityLeadername) {
        this.activityLeadername = activityLeadername == null ? null : activityLeadername.trim();
    }

    public Integer getActivityLeaderid() {
        return activityLeaderid;
    }

    public void setActivityLeaderid(Integer activityLeaderid) {
        this.activityLeaderid = activityLeaderid;
    }

    public Date getActivityCreatetime() {
        return activityCreatetime;
    }

    public void setActivityCreatetime(Date activityCreatetime) {
        this.activityCreatetime = activityCreatetime;
    }

    public Date getActivityStarttime() {
        return activityStarttime;
    }

    public void setActivityStarttime(Date activityStarttime) {
        this.activityStarttime = activityStarttime;
    }

    public Date getActivityEndtime() {
        return activityEndtime;
    }

    public void setActivityEndtime(Date activityEndtime) {
        this.activityEndtime = activityEndtime;
    }

	public Integer getActivityGrade() {
		return activityGrade;
	}

	public void setActivityGrade(Integer activityGrade) {
		this.activityGrade = activityGrade;
	}

	public Integer getActivityStatus() {
		return activityStatus;
	}

	public void setActivityStatus(Integer activityStatus) {
		this.activityStatus = activityStatus;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}
    
}