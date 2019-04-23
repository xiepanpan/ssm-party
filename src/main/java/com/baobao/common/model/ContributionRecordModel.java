package com.baobao.common.model;

public class ContributionRecordModel {
    private String typeName; //缴费类型
    private String date; //缴费日期
    private float money; //缴费金额

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public float getMoney() {
        return money;
    }

    public void setMoney(float money) {
        this.money = money;
    }
}
