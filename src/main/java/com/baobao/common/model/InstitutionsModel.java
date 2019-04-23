package com.baobao.common.model;

public class InstitutionsModel {
	 	private int brId;
	    private int grade; //所属层级
	    private String brName; //机构名字
	    private int brRolesType; //用户在支部中的职位
		private String brRolesName;
		public int getGrade() {
			return grade;
		}
		public void setGrade(int grade) {
			this.grade = grade;
		}	
		public int getBrRolesType() {
			return brRolesType;
		}
		public void setBrRolesType(int brRolesType) {
			this.brRolesType = brRolesType;
		}
		public int getBrId() {
			return brId;
		}
		public void setBrId(int brId) {
			this.brId = brId;
		}
		public String getBrName() {
			return brName;
		}
		public void setBrName(String brName) {
			this.brName = brName;
		}
		public String getBrRolesName() {
			return brRolesName;
		}
		public void setBrRolesName(String brRolesName) {
			this.brRolesName = brRolesName;
		}
		
		
}
