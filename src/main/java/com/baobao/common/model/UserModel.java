package com.baobao.common.model;

public class UserModel {
	private UserBriefModel briefInfo; //基本信息
    private String mobileNumber; //联系电话
    private String userIdcard; //身份证号码
    private String email; //邮箱
    private long birthDay; //生日
    private String workUnitName; //工作单位名称
    
	public UserBriefModel getBriefInfo() {
		return briefInfo;
	}
	public void setBriefInfo(UserBriefModel briefInfo) {
		this.briefInfo = briefInfo;
	}
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public String getUserIdcard() {
		return userIdcard;
	}
	public void setUserIdcard(String userIdcard) {
		this.userIdcard = userIdcard;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public long getBirthDay() {
		return birthDay;
	}
	public void setBirthDay(long birthDay) {
		this.birthDay = birthDay;
	}
	public String getWorkUnitName() {
		return workUnitName;
	}
	public void setWorkUnitName(String workUnitName) {
		this.workUnitName = workUnitName;
	}
	
}
