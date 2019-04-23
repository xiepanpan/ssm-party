package com.baobao.common.model;

public class PartyBranchModel {
    private Integer partyId;

    private String partyBranchName;

    private Integer partyBranchSecretaryId;

    private String partyBranchSecretary;

    private Integer partyBranchOrganizationId;

    private String partyBranchOrganization;

    private Integer partyBranchPublicityId;

    private String partyBranchPublicity;

    private Integer partyBranchType;

    private Integer partyBranchDel;

    public Integer getPartyId() {
        return partyId;
    }

    public void setPartyId(Integer partyId) {
        this.partyId = partyId;
    }

    public String getPartyBranchName() {
        return partyBranchName;
    }

    public void setPartyBranchName(String partyBranchName) {
        this.partyBranchName = partyBranchName == null ? null : partyBranchName.trim();
    }

    public Integer getPartyBranchSecretaryId() {
        return partyBranchSecretaryId;
    }

    public void setPartyBranchSecretaryId(Integer partyBranchSecretaryId) {
        this.partyBranchSecretaryId = partyBranchSecretaryId!=-1?partyBranchSecretaryId:null;
    }

    public String getPartyBranchSecretary() {
        return partyBranchSecretary;
    }

    public void setPartyBranchSecretary(String partyBranchSecretary) {
        this.partyBranchSecretary = partyBranchSecretary == null ? null : partyBranchSecretary.trim();
    }

    public Integer getPartyBranchOrganizationId() {
        return partyBranchOrganizationId;
    }

    public void setPartyBranchOrganizationId(Integer partyBranchOrganizationId) {
        this.partyBranchOrganizationId = partyBranchOrganizationId!=-1?partyBranchOrganizationId:null;
    }

    public String getPartyBranchOrganization() {
        return partyBranchOrganization;
    }

    public void setPartyBranchOrganization(String partyBranchOrganization) {
        this.partyBranchOrganization = partyBranchOrganization == null ? null : partyBranchOrganization.trim();
    }

    public Integer getPartyBranchPublicityId() {
        return partyBranchPublicityId;
    }

    public void setPartyBranchPublicityId(Integer partyBranchPublicityId) {
        this.partyBranchPublicityId = partyBranchPublicityId!=-1?partyBranchPublicityId:null;;
    }

    public String getPartyBranchPublicity() {
        return partyBranchPublicity;
    }

    public void setPartyBranchPublicity(String partyBranchPublicity) {
        this.partyBranchPublicity = partyBranchPublicity == null ? null : partyBranchPublicity.trim();
    }

    public Integer getPartyBranchType() {
        return partyBranchType;
    }

    public void setPartyBranchType(Integer partyBranchType) {
        this.partyBranchType = partyBranchType;
    }

    public Integer getPartyBranchDel() {
        return partyBranchDel;
    }

    public void setPartyBranchDel(Integer partyBranchDel) {
        this.partyBranchDel = partyBranchDel;
    }
}