package com.baobao.common.model;

public class MemRoleModel {
    private Integer relationId;

    private Integer relationMemberId;

    private Integer relationPartBranchId;
    
    private Integer relationRole;
    

    public MemRoleModel() {
	}

    public MemRoleModel(Integer relationMemberId) {
    	this.relationMemberId = relationMemberId;
    }
	public MemRoleModel(Integer relationMemberId, Integer relationPartBranchId) {
		this.relationMemberId = relationMemberId;
		this.relationPartBranchId = relationPartBranchId;
	}

	public Integer getRelationId() {
        return relationId;
    }

    public void setRelationId(Integer relationId) {
        this.relationId = relationId;
    }

    public Integer getRelationMemberId() {
        return relationMemberId;
    }

    public void setRelationMemberId(Integer relationMemberId) {
        this.relationMemberId = relationMemberId;
    }

    public Integer getRelationPartBranchId() {
        return relationPartBranchId;
    }

    public void setRelationPartBranchId(Integer relationPartBranchId) {
        this.relationPartBranchId = relationPartBranchId;
    }

	public Integer getRelationRole() {
		return relationRole;
	}

	public void setRelationRole(Integer relationRole) {
		this.relationRole = relationRole;
	}
    
}