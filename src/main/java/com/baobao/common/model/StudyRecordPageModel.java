package com.baobao.common.model;

import java.util.List;

public class StudyRecordPageModel {
	 private int toYearCountTime; //今年累计时间
	    private int toMonthCountTime; //本月累计时间
	    private List<StudyRecordNewModel> studyRecordList; //当前月的学习时间记录


	    public int getToYearCountTime() {
	        return toYearCountTime;
	    }

	    public void setToYearCountTime(int toYearCountTime) {
	        this.toYearCountTime = toYearCountTime;
	    }

	    public int getToMonthCountTime() {
	        return toMonthCountTime;
	    }

	    public void setToMonthCountTime(int toMonthCountTime) {
	        this.toMonthCountTime = toMonthCountTime;
	    }

	    public List<StudyRecordNewModel> getStudyRecordList() {
	        return studyRecordList;
	    }

	    public void setStudyRecordList(List<StudyRecordNewModel> studyRecordList) {
	        this.studyRecordList = studyRecordList;
	    }
}
