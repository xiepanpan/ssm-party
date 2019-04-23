package com.baobao.common.model;

public class CollectionModel {
    private Integer id;
    /**
     * 新闻ID
     */
    private Integer newId;
    /**
     * 用户ID
     */
    private Integer memberId;
    /**
     * 是否已删除
     */
    private Integer isCollected;
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getNewId() {
        return newId;
    }

    public void setNewId(Integer newId) {
        this.newId = newId;
    }

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

	/**
	 * @return the isCollected
	 */
	public Integer getIsCollected() {
		return isCollected;
	}

	/**
	 * @param isCollected the isCollected to set
	 */
	public void setIsCollected(Integer isCollected) {
		this.isCollected = isCollected;
	}
    
}