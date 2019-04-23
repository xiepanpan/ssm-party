package com.baobao.common.model;

public class SequenceModel {
    private String name;

    private Integer seqValue;

    private Integer seqIncrement;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getSeqValue() {
        return seqValue;
    }

    public void setSeqValue(Integer seqValue) {
        this.seqValue = seqValue;
    }

    public Integer getSeqIncrement() {
        return seqIncrement;
    }

    public void setSeqIncrement(Integer seqIncrement) {
        this.seqIncrement = seqIncrement;
    }
}