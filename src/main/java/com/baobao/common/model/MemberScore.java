package com.baobao.common.model;

public class MemberScore {
    private Integer memberId;

    private Float memberActScore;

    private Float memberTaskScore;

    private Float memberStudyScore;

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    public Float getMemberActScore() {
        return memberActScore;
    }

    public void setMemberActScore(Float memberActScore) {
        this.memberActScore = memberActScore;
    }

    public Float getMemberTaskScore() {
        return memberTaskScore;
    }

    public void setMemberTaskScore(Float memberTaskScore) {
        this.memberTaskScore = memberTaskScore;
    }

    public Float getMemberStudyScore() {
        return memberStudyScore;
    }

    public void setMemberStudyScore(Float memberStudyScore) {
        this.memberStudyScore = memberStudyScore;
    }
}