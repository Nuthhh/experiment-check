package com.ysu.db.pojo;

import java.util.Date;

public class Question {
    private String questionId;

    private String name;

    private Integer time;

    private Integer memory;

    private Date createTime;

    private Date updateTime;

    @Override
    public String toString() {
        return "Question{" +
                "questionId='" + questionId + '\'' +
                ", name='" + name + '\'' +
                ", time=" + time +
                ", memory=" + memory +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }

    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId == null ? null : questionId.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }

    public Integer getMemory() {
        return memory;
    }

    public void setMemory(Integer memory) {
        this.memory = memory;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}