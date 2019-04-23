package com.baobao.common.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class StudyRecordModel {
	/**
	 * 学习记录ID
	 */
    private Integer sturecordId;
    
    /**
     * 学习日期
     */
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date stuDate;
    /**
     * 学习时长
     */
    private Integer stuTimes;
    /**
     * 今日学习时长
     */
    private Integer todayTime;
    /**
     * 连续学习天数
     */
    private Integer continiuityStudydays;
    /**
     * 党员ID
     */
    private Integer stuMemberId;

    public Integer getSturecordId() {
        return sturecordId;
    }

    public void setSturecordId(Integer sturecordId) {
        this.sturecordId = sturecordId;
    }

    public Date getStuDate() {
        return stuDate;
    }

    public void setStuDate(Date stuDate) {
        this.stuDate = stuDate;
    }

    public Integer getStuTimes() {
        return stuTimes;
    }

    public void setStuTimes(Integer stuTimes) {
        this.stuTimes = stuTimes;
    }

    public Integer getTodayTime() {
        return todayTime;
    }

    public void setTodayTime(Integer todayTime) {
        this.todayTime = todayTime;
    }

    public Integer getContiniuityStudydays() {
        return continiuityStudydays;
    }

    public void setContiniuityStudydays(Integer continiuityStudydays) {
        this.continiuityStudydays = continiuityStudydays;
    }

    public Integer getStuMemberId() {
        return stuMemberId;
    }

    public void setStuMemberId(Integer stuMemberId) {
        this.stuMemberId = stuMemberId;
    }
}