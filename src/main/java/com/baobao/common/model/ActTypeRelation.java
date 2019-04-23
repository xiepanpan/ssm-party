package com.baobao.common.model;

public class ActTypeRelation {
    private Integer actTypeRelationId;

    private Integer actId;

    private Integer typeId;

    public Integer getActTypeRelationId() {
        return actTypeRelationId;
    }

    public void setActTypeRelationId(Integer actTypeRelationId) {
        this.actTypeRelationId = actTypeRelationId;
    }

    public Integer getActId() {
        return actId;
    }

    public void setActId(Integer actId) {
        this.actId = actId;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }
}