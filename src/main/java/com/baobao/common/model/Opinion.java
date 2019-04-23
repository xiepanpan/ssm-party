package com.baobao.common.model;

import java.util.Date;

public class Opinion {
    private Integer opinionId;

	private Integer opinionUserId;

	private String opinionContent;

	private Date opinionTime;

	public Integer getOpinionId() {
		return opinionId;
	}

	public void setOpinionId(Integer opinionId) {
		this.opinionId = opinionId;
	}

	public Integer getOpinionUserId() {
		return opinionUserId;
	}

	public void setOpinionUserId(Integer opinionUserId) {
		this.opinionUserId = opinionUserId;
	}

	public String getOpinionContent() {
		return opinionContent;
	}

	public void setOpinionContent(String opinionContent) {
		this.opinionContent = opinionContent == null ? null : opinionContent
				.trim();
	}

	public Date getOpinionTime() {
		return opinionTime;
	}

	public void setOpinionTime(Date opinionTime) {
		this.opinionTime = opinionTime;
	}

	
}