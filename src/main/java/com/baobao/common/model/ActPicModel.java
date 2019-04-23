package com.baobao.common.model;

public class ActPicModel {
	/**
	 * 上传活动图片的ID
	 */
    private Integer actPicId;
    /**
     * 活动发布的支部的ID
     */
    private Integer actPicBrid;
    /**
     * 活动的ID
     */
    private Integer actPicActid;
    /**
     * 图片的url
     */
    private String actPicUrl;

    public Integer getActPicId() {
        return actPicId;
    }

    public void setActPicId(Integer actPicId) {
        this.actPicId = actPicId;
    }

    public Integer getActPicBrid() {
        return actPicBrid;
    }

    public void setActPicBrid(Integer actPicBrid) {
        this.actPicBrid = actPicBrid;
    }

    public Integer getActPicActid() {
        return actPicActid;
    }

    public void setActPicActid(Integer actPicActid) {
        this.actPicActid = actPicActid;
    }

    public String getActPicUrl() {
        return actPicUrl;
    }

    public void setActPicUrl(String actPicUrl) {
        this.actPicUrl = actPicUrl == null ? null : actPicUrl.trim();
    }
}