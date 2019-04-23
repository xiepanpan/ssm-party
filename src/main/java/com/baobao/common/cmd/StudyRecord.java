package com.baobao.common.cmd;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

public class StudyRecord {
	
	private Integer informationId;

	private List<TimeRecordModel> mTimeRecords;

	public Integer getInformationId() {
		return informationId;
	}

	public void setInformationId(Integer informationId) {
		this.informationId = informationId;
	}

	public List<TimeRecordModel> getmTimeRecords() {
		return mTimeRecords;
	}

	public void setmTimeRecords(List<TimeRecordModel> mTimeRecords) {
		this.mTimeRecords = mTimeRecords;
	}
	
	
	
	
}
