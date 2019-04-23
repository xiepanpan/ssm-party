package com.baobao.common.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class AppraisalModel {
	private Integer id;
	private Integer year;
	/**
	 * 评定日期
	 */
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date date;
	/**
	 * 应到人数
	 */
	private Integer shouldPeopleNumber;
	/**
	 * 实到人数
	 */
	private Integer aactuallyPeopleNumber;
	/**
	 * 行动不便人数
	 */
	private Integer movesDifficultyPeopleNumber;
	/**
	 * 优秀票数
	 */
	private Integer goodVotes;
	/**
	 *合格票数 
	 * 	 
	 */
	private Integer qualifiedVotes;
	/**
	 * 基本合格票数
	 */
	private Integer baseQualifiedVotes;
	/**
	 * 不合格票数
	 */
	private Integer unqualifiedVotes;
	/**
	 * 评定等次
	 */
	private Integer evaluationCode;
	/**
	 * 评议接收人ID
	 */
	private Integer uid;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getYear() {
		return year;
	}
	public void setYear(Integer year) {
		this.year = year;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Integer getShouldPeopleNumber() {
		return shouldPeopleNumber;
	}
	public void setShouldPeopleNumber(Integer shouldPeopleNumber) {
		this.shouldPeopleNumber = shouldPeopleNumber;
	}
	
	public Integer getAactuallyPeopleNumber() {
		return aactuallyPeopleNumber;
	}
	public void setAactuallyPeopleNumber(Integer aactuallyPeopleNumber) {
		this.aactuallyPeopleNumber = aactuallyPeopleNumber;
	}
	public Integer getMovesDifficultyPeopleNumber() {
		return movesDifficultyPeopleNumber;
	}
	public void setMovesDifficultyPeopleNumber(Integer movesDifficultyPeopleNumber) {
		this.movesDifficultyPeopleNumber = movesDifficultyPeopleNumber;
	}
	public Integer getGoodVotes() {
		return goodVotes;
	}
	public void setGoodVotes(Integer goodVotes) {
		this.goodVotes = goodVotes;
	}
	public Integer getQualifiedVotes() {
		return qualifiedVotes;
	}
	public void setQualifiedVotes(Integer qualifiedVotes) {
		this.qualifiedVotes = qualifiedVotes;
	}
	public Integer getBaseQualifiedVotes() {
		return baseQualifiedVotes;
	}
	public void setBaseQualifiedVotes(Integer baseQualifiedVotes) {
		this.baseQualifiedVotes = baseQualifiedVotes;
	}
	public Integer getUnqualifiedVotes() {
		return unqualifiedVotes;
	}
	public void setUnqualifiedVotes(Integer unqualifiedVotes) {
		this.unqualifiedVotes = unqualifiedVotes;
	}
	public Integer getEvaluationCode() {
		return evaluationCode;
	}
	public void setEvaluationCode(Integer evaluationCode) {
		this.evaluationCode = evaluationCode;
	}
	public Integer getUid() {
		return uid;
	}
	public void setUid(Integer uid) {
		this.uid = uid;
	}
	
}
