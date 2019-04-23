package com.baobao.common.model;

import java.util.Date;

public class ActRecordModel {
    private Integer actRecordId;

    private Integer actMemberId;

    private Integer actRecActid;

    private String actReport;

    private Integer actReStatus;

    private Integer actComId;

    private String actComment;

    private Integer actComStatus;

    private Date actRepottime;

    private Date actComtime;

    private Integer actStatus;

    public Integer getActRecordId() {
        return actRecordId;
    }

    public void setActRecordId(Integer actRecordId) {
        this.actRecordId = actRecordId;
    }

    public Integer getActMemberId() {
        return actMemberId;
    }

    public void setActMemberId(Integer actMemberId) {
        this.actMemberId = actMemberId;
    }

    public Integer getActRecActid() {
        return actRecActid;
    }

    public void setActRecActid(Integer actRecActid) {
        this.actRecActid = actRecActid;
    }

    public String getActReport() {
        return actReport;
    }

    public void setActReport(String actReport) {
        this.actReport = actReport == null ? null : actReport.trim();
    }

    public Integer getActReStatus() {
        return actReStatus;
    }

    public void setActReStatus(Integer actReStatus) {
        this.actReStatus = actReStatus;
    }

    public Integer getActComId() {
        return actComId;
    }

    public void setActComId(Integer actComId) {
        this.actComId = actComId;
    }

    public String getActComment() {
        return actComment;
    }

    public void setActComment(String actComment) {
        this.actComment = actComment == null ? null : actComment.trim();
    }

    public Integer getActComStatus() {
        return actComStatus;
    }

    public void setActComStatus(Integer actComStatus) {
        this.actComStatus = actComStatus;
    }

    public Date getActRepottime() {
        return actRepottime;
    }

    public void setActRepottime(Date actRepottime) {
        this.actRepottime = actRepottime;
    }

    public Date getActComtime() {
        return actComtime;
    }

    public void setActComtime(Date actComtime) {
        this.actComtime = actComtime;
    }

    public Integer getActStatus() {
        return actStatus;
    }

	/**
	 * @param actStatus the actStatus to set
	 */
	public void setActStatus(Integer actStatus) {
		this.actStatus = actStatus;
	}
    
 
}