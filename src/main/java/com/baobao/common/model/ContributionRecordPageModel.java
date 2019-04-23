package com.baobao.common.model;

import java.util.List;

public class ContributionRecordPageModel {
    private Float currentMembershipDues; //本月应缴
    private Float totalHistorical; //历史缴费统计
    private Float totalYear; //本年已缴统计
    private List<ContributionRecordModel> yearRecordList; //本年缴费记录

    public float getCurrentMembershipDues() {
        return currentMembershipDues;
    }

    public void setCurrentMembershipDues(Float currentMembershipDues) {
        this.currentMembershipDues = currentMembershipDues;
    }

    public float getTotalHistorical() {
        return totalHistorical;
    }

    public void setTotalHistorical(Float totalHistorical) {
        this.totalHistorical = totalHistorical;
    }

    public float getTotalYear() {
        return totalYear;
    }

    public void setTotalYear(Float totalYear) {
        this.totalYear = totalYear;
    }

    public List<ContributionRecordModel> getYearRecordList() {
        return yearRecordList;
    }

    public void setYearRecordList(List<ContributionRecordModel> yearRecordList) {
        this.yearRecordList = yearRecordList;
    }
}
