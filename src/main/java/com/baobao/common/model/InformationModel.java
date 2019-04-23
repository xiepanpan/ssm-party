package com.baobao.common.model;

/**
 * <P>信息</P>
 */
public class InformationModel {
	/**
	 * 新闻ID
	 */
	private int id; 
	/**
	 * 新闻title即新闻的摘要
	 */
	private String title;  
	/**
	 * 点击率
	 */
	private int readCount; 
	/**
	 * 新闻发布的时间
	 */
	private String releaseTime; 
	/**
	 * 新闻详情url
	 */
	private String detailsPageUrl; 
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getReadCount() {
		return readCount;
	}
	public void setReadCount(int readCount) {
		this.readCount = readCount;
	}
	public String getReleaseTime() {
		return releaseTime;
	}
	public void setReleaseTime(String releaseTime) {
		this.releaseTime = releaseTime;
	}
	public String getDetailsPageUrl() {
		return detailsPageUrl;
	}
	public void setDetailsPageUrl(String detailsPageUrl) {
		this.detailsPageUrl = detailsPageUrl;
	}

}
