package com.baobao.common.model.andshow;

import java.util.Date;
import java.util.List;

public class ActComment extends AppUser {

	private Integer commentId;

	private String commentContent;

	private Date commentTime;

	private Integer goodNumber;
	
	private Integer goodState;
	
	private List<AppTaskFile> files;

	public String getCommentContent() {
		return commentContent;
	}

	public void setCommentContent(String commentContent) {
		this.commentContent = commentContent;
	}

	public Date getCommentTime() {
		return commentTime;
	}

	public void setCommentTime(Date commentTime) {
		this.commentTime = commentTime;
	}

	public List<AppTaskFile> getFiles() {
		return files;
	}

	public void setFiles(List<AppTaskFile> files) {
		this.files = files;
	}

	public Integer getCommentId() {
		return commentId;
	}

	public void setCommentId(Integer commentId) {
		this.commentId = commentId;
	}

	public Integer getGoodNumber() {
		return goodNumber;
	}

	public void setGoodNumber(Integer goodNumber) {
		this.goodNumber = goodNumber;
	}

	public Integer getGoodState() {
		return goodState;
	}

	public void setGoodState(Integer goodState) {
		this.goodState = goodState;
	}

	
}
