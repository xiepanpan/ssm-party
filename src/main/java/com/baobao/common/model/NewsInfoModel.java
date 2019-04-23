package com.baobao.common.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class NewsInfoModel {
	/**
	 * 新闻ID
	 */
    private Integer tid;
    
    /**
     * 新闻摘要
     */
    private String title;
    /**
     * 新闻点击量
     */
    private Integer readcount;
    /**
     * 新闻发布时间
     */
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm")
    private Date releasetime;
    /**
     * 新闻内容
     */
    private String newsContext;
    /**
     * 新闻的图片路径
     */
    private String imgUrl;
    /**
     * 新闻类型
     */
    private Integer newsType;
    /**
     * 访问的html地址
     */
    private String detailspageUrl;
    /*
     * 新闻热度 默认为0
     */
    private Integer heatNum;
    /**
     * 与轮播对应的ID
     */
    private Integer tCid;
    /**
     * 删除状态0 正常 1已删
     */
    private Integer tIsdeleted;
    public Integer getTid() {
        return tid;
    }

    public void setTid(Integer tid) {
        this.tid = tid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public Integer getReadcount() {
        return readcount;
    }

    public void setReadcount(Integer readcount) {
        this.readcount = readcount;
    }

    public Date getReleasetime() {
        return releasetime;
    }

    public void setReleasetime(Date releasetime) {
        this.releasetime = releasetime;
    }

    public String getNewsContext() {
        return newsContext;
    }

    public void setNewsContext(String newsContext) {
        this.newsContext = newsContext == null ? null : newsContext.trim();
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl == null ? null : imgUrl.trim();
    }

    public Integer getNewsType() {
        return newsType;
    }

    public void setNewsType(Integer newsType) {
        this.newsType = newsType;
    }

    public String getDetailspageUrl() {
        return detailspageUrl;
    }

    public void setDetailspageUrl(String detailspageUrl) {
        this.detailspageUrl = detailspageUrl == null ? null : detailspageUrl.trim();
    }

	/**
	 * @return the heatNum
	 */
	public Integer getHeatNum() {
		return heatNum;
	}

	/**
	 * @param heatNum the heatNum to set
	 */
	public void setHeatNum(Integer heatNum) {
		this.heatNum = heatNum;
	}

	/**
	 * @return the tCid
	 */
	public Integer gettCid() {
		return tCid;
	}

	/**
	 * @param tCid the tCid to set
	 */
	public void settCid(Integer tCid) {
		this.tCid = tCid;
	}

	/**
	 * @return the tIsdeleted
	 */
	public Integer gettIsdeleted() {
		return tIsdeleted;
	}

	/**
	 * @param tIsdeleted the tIsdeleted to set
	 */
	public void settIsdeleted(Integer tIsdeleted) {
		this.tIsdeleted = tIsdeleted;
	}
    
}