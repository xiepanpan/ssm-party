package com.baobao.common.model;

public class TimeRecordIosModel {
	
	private Long startTime;
	
	private Long endTime;

	public Long getStartTime() {
		return startTime;
	}

	public void setStartTime(Long startTime) {
		this.startTime = startTime;
	}

	public Long getEndTime() {
		return endTime;
	}

	public void setEndTime(Long endTime) {
		this.endTime = endTime;
	}

	@Override
	public String toString() {
		return "TimeRecordIosModel [startTime=" + startTime + ", endTime="
				+ endTime + "]";
	}
	
	
	
}
