package com.baobao.common.cmd;

/**
 * <P>
 *  新闻管理-时政学习 查询类
 * </P>
 * @author 袁杨柳 (17607177525)
 * 
 * 2017年8月24日 上午11:13:45
 */
public class SearchCondition {
	/**
	 *  模糊查询
	 */
	private String searchValue;

	/**
	 * 新闻类型
	 */
	private Integer newsType;
	
	/**
	 * 判断进入1.新闻列表，或2.详情
	 */
	private Integer isFlag;
	
	/**
	 * 绑定id
	 */
	private Integer tCid;
	/**
	 * 新闻id
	 */
	private Integer tid;
	public String getSearchValue() {
		return searchValue;
	}
	public void setSearchValue(String searchValue) {
		this.searchValue = searchValue;
	}
	public Integer getNewsType() {
		return newsType;
	}
	public void setNewsType(Integer newsType) {
		this.newsType = newsType;
	}
	public Integer getIsFlag() {
		return isFlag;
	}
	public void setIsFlag(Integer isFlag) {
		this.isFlag = isFlag;
	}
	public Integer getTid() {
		return tid;
	}
	public void setTid(Integer tid) {
		this.tid = tid;
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
	
	
}
