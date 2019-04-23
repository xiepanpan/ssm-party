package com.baobao.common.model;

public class BannerModel {
	private int id;
	/**
	 * 轮播图片地址
	 */
	private String imageUrl; 
	/**
	 * 详情HTML的URL
	 */
    private String detailsPageUrl;
    
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public String getDetailsPageUrl() {
		return detailsPageUrl;
	}
	public void setDetailsPageUrl(String detailsPageUrl) {
		this.detailsPageUrl = detailsPageUrl;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	} 
    
}
