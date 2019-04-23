package com.baobao.common.model;

public class FeesModel {
	 	private float currentMembershipDues; //本月应缴
	    private float totalHistorical; //历史缴费统计
	    private float totalYear; //本年已缴统计
		public float getCurrentMembershipDues() {
			return currentMembershipDues;
		}
		public void setCurrentMembershipDues(float currentMembershipDues) {
			this.currentMembershipDues = currentMembershipDues;
		}
		public float getTotalHistorical() {
			return totalHistorical;
		}
		public void setTotalHistorical(float totalHistorical) {
			this.totalHistorical = totalHistorical;
		}
		public float getTotalYear() {
			return totalYear;
		}
		public void setTotalYear(float totalYear) {
			this.totalYear = totalYear;
		}
	    
}
