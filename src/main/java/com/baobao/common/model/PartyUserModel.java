/*******************************************************************************
 * Project : hbjiandang
 * Create on 2017年9月4日 下午5:17:14
 * Copyright (c) 2014 - 2016 粮达网
 * 注意：本内容仅限于粮达网内部传阅，禁止外泄以及用于其他的商业目的
 ******************************************************************************/
package com.baobao.common.model;

/**
 * <P>TODO</P>
 * @author 夏思明
 * @date 2017年9月4日 下午5:17:14
 */
public class PartyUserModel {
	private String gender;
	private String iconUrl;
	private Integer id;
	private String name;
	private Integer partyClassification;//党员类型
	private String partyOrganizationName;
	private Integer rolesType;
	/**
	 * @return the gender
	 */
	public String getGender() {
		return gender;
	}
	/**
	 * @param gender the gender to set
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}
	/**
	 * @return the iconUrl
	 */
	public String getIconUrl() {
		return iconUrl;
	}
	/**
	 * @param iconUrl the iconUrl to set
	 */
	public void setIconUrl(String iconUrl) {
		this.iconUrl = iconUrl;
	}
	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the partyClassification
	 */
	public Integer getPartyClassification() {
		return partyClassification;
	}
	/**
	 * @param partyClassification the partyClassification to set
	 */
	public void setPartyClassification(Integer partyClassification) {
		this.partyClassification = partyClassification;
	}
	/**
	 * @return the partyOrganizationName
	 */
	public String getPartyOrganizationName() {
		return partyOrganizationName;
	}
	/**
	 * @param partyOrganizationName the partyOrganizationName to set
	 */
	public void setPartyOrganizationName(String partyOrganizationName) {
		this.partyOrganizationName = partyOrganizationName;
	}
	/**
	 * @return the rolesType
	 */
	public Integer getRolesType() {
		return rolesType;
	}
	/**
	 * @param rolesType the rolesType to set
	 */
	public void setRolesType(Integer rolesType) {
		this.rolesType = rolesType;
	}
	
}
