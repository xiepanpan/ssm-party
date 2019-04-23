package com.baobao.common.model;

public class BranchInfoModel {
	/**
	 * 当前支部ID
	 */
    private Integer branchId;
    /**
     * 上级支部ID
     */
    private Integer branchFatherId;
    /**
     * 支部名称
     */
    private String branchName;
    /**
     * 支部书记
     */
    private String branchSecretary;
    /**
     * 支部组织委员
     */
    private String branchOrganizationCommittee;
    /**
     * 支部宣传委员
     */
    private String branchPublicityCommittee;
    /**
     * 备注
     */
    private String branchRemark;
    /**
     * 软删除
     */
    private Integer brIsdeleted;
    /**
     * 支部书记ID
     */
    private Integer brSeId;
    /**
     * 支部组织委员ID
     */
    private Integer brOrgId;
    /**
     * 支部宣传ID
     */
    private Integer brXcId;
    public Integer getBranchId() {
        return branchId;
    }

    public void setBranchId(Integer branchId) {
        this.branchId = branchId;
    }

    public Integer getBranchFatherId() {
        return branchFatherId;
    }

    public void setBranchFatherId(Integer branchFatherId) {
        this.branchFatherId = branchFatherId;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName == null ? null : branchName.trim();
    }

    public String getBranchSecretary() {
        return branchSecretary;
    }

    public void setBranchSecretary(String branchSecretary) {
        this.branchSecretary = branchSecretary == null ? null : branchSecretary.trim();
    }

    public String getBranchOrganizationCommittee() {
        return branchOrganizationCommittee;
    }

    public void setBranchOrganizationCommittee(String branchOrganizationCommittee) {
        this.branchOrganizationCommittee = branchOrganizationCommittee == null ? null : branchOrganizationCommittee.trim();
    }

    public String getBranchPublicityCommittee() {
        return branchPublicityCommittee;
    }

    public void setBranchPublicityCommittee(String branchPublicityCommittee) {
        this.branchPublicityCommittee = branchPublicityCommittee == null ? null : branchPublicityCommittee.trim();
    }

    public String getBranchRemark() {
        return branchRemark;
    }

    public void setBranchRemark(String branchRemark) {
        this.branchRemark = branchRemark == null ? null : branchRemark.trim();
    }

	/**
	 * @return the brIsdeleted
	 */
	public Integer getBrIsdeleted() {
		return brIsdeleted;
	}

	/**
	 * @param brIsdeleted the brIsdeleted to set
	 */
	public void setBrIsdeleted(Integer brIsdeleted) {
		this.brIsdeleted = brIsdeleted;
	}

	public Integer getBrSeId() {
		return brSeId;
	}

	public void setBrSeId(Integer brSeId) {
		this.brSeId = brSeId;
	}

	public Integer getBrOrgId() {
		return brOrgId;
	}

	public void setBrOrgId(Integer brOrgId) {
		this.brOrgId = brOrgId;
	}

	public Integer getBrXcId() {
		return brXcId;
	}

	public void setBrXcId(Integer brXcId) {
		this.brXcId = brXcId;
	}
    
}