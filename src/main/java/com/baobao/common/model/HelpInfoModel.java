package com.baobao.common.model;

public class HelpInfoModel {
    private Integer helpId;

    private String helpQuesition;

    private String helpAnswer;

    public Integer getHelpId() {
        return helpId;
    }

    public void setHelpId(Integer helpId) {
        this.helpId = helpId;
    }

    public String getHelpQuesition() {
        return helpQuesition;
    }

    public void setHelpQuesition(String helpQuesition) {
        this.helpQuesition = helpQuesition == null ? null : helpQuesition.trim();
    }

    public String getHelpAnswer() {
        return helpAnswer;
    }

    public void setHelpAnswer(String helpAnswer) {
        this.helpAnswer = helpAnswer == null ? null : helpAnswer.trim();
    }
}