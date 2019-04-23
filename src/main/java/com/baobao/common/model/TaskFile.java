package com.baobao.common.model;

public class TaskFile {
    private Integer taskFileId;

    private String taskFileName;

    private String taskFileUrl;

    private String taskFileSize;

    private Integer taskFileBelong;

    public Integer getTaskFileId() {
        return taskFileId;
    }

    public void setTaskFileId(Integer taskFileId) {
        this.taskFileId = taskFileId;
    }

    public String getTaskFileName() {
        return taskFileName;
    }

    public void setTaskFileName(String taskFileName) {
        this.taskFileName = taskFileName == null ? null : taskFileName.trim();
    }

    public String getTaskFileUrl() {
        return taskFileUrl;
    }

    public void setTaskFileUrl(String taskFileUrl) {
        this.taskFileUrl = taskFileUrl == null ? null : taskFileUrl.trim();
    }

    public String getTaskFileSize() {
        return taskFileSize;
    }

    public void setTaskFileSize(String taskFileSize) {
        this.taskFileSize = taskFileSize == null ? null : taskFileSize.trim();
    }

    public Integer getTaskFileBelong() {
        return taskFileBelong;
    }

    public void setTaskFileBelong(Integer taskFileBelong) {
        this.taskFileBelong = taskFileBelong;
    }
}