package com.baobao.common.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class PayInfoModel {
    private Integer payId;

    private Double payFees;

    private Integer payStatus;

    private String payTel;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date payMonth;
   
    private String payName;//缴费人姓名
    
    private Integer payBrId;//缴费人所在支部ID 
    
    private String payBrName;
    
    public Integer getPayId() {
        return payId;
    }

    public void setPayId(Integer payId) {
        this.payId = payId;
    }

    public Double getPayFees() {
        return payFees;
    }

    public void setPayFees(Double payFees) {
        this.payFees = payFees;
    }

    public Integer getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(Integer payStatus) {
        this.payStatus = payStatus;
    }

    public String getPayTel() {
        return payTel;
    }

    public void setPayTel(String payTel) {
        this.payTel = payTel == null ? null : payTel.trim();
    }

    public Date getPayMonth() {
        return payMonth;
    }

    public void setPayMonth(Date payMonth) {
        this.payMonth = payMonth;
    }

	public String getPayName() {
		return payName;
	}

	public void setPayName(String payName) {
		this.payName = payName;
	}

	public Integer getPayBrId() {
		return payBrId;
	}

	public void setPayBrId(Integer payBrId) {
		this.payBrId = payBrId;
	}

	public String getPayBrName() {
		return payBrName;
	}

	public void setPayBrName(String payBrName) {
		this.payBrName = payBrName;
	}

	
    
}