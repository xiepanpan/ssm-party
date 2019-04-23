package com.baobao.common.model;

import java.util.Date;

public class Comment {
    private Integer commentId;

    private Integer commentObjectId;

    private Integer commentUserId;

    private String commentContent;

    private Date commentTime;

    public Integer getCommentId() {
        return commentId;
    }

    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }

    public Integer getCommentObjectId() {
        return commentObjectId;
    }

    public void setCommentObjectId(Integer commentObjectId) {
        this.commentObjectId = commentObjectId;
    }

    public Integer getCommentUserId() {
        return commentUserId;
    }

    public void setCommentUserId(Integer commentUserId) {
        this.commentUserId = commentUserId;
    }

    public String getCommentContent() {
        return commentContent;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent == null ? null : commentContent.trim();
    }

    public Date getCommentTime() {
        return commentTime;
    }

    public void setCommentTime(Date commentTime) {
        this.commentTime = commentTime;
    }
}