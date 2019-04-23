package com.baobao.common.model;

import java.util.List;

public class UserInfoAll {
	 private UserBriefModel userBrief;
	 private InstitutionsModel institutions;
	 
	 public UserBriefModel getUserBrief() {
	        return userBrief;
	 }

	 public void setUserBrief(UserBriefModel userBrief) {
	        this.userBrief = userBrief;
	 }

    public InstitutionsModel getInstitutions() {
        return institutions;
    }

    public void setInstitutions(InstitutionsModel institutions) {
        this.institutions = institutions;
    }

	
}
