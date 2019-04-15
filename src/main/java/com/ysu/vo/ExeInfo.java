package com.ysu.vo;

import java.util.Date;

/**
 * @Auther: han jianguo
 * @Date: 2019/4/15 14:11
 * @Description:
 **/
public class ExeInfo {

    private String exeId;

    private String questionId;

    private Integer stuId;

    private Short lang;

    private String context;

    private Integer time;

    private Integer memory;

    private Short code;

    private Date createTime;

    @Override
    public String toString() {
        return "ExeInfo{" +
                "exeId='" + exeId + '\'' +
                ", questionId='" + questionId + '\'' +
                ", stuId=" + stuId +
                ", lang=" + lang +
                ", context='" + context + '\'' +
                ", time=" + time +
                ", memory=" + memory +
                ", code=" + code +
                ", createTime=" + createTime +
                '}';
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getExeId() {
        return exeId;
    }

    public void setExeId(String exeId) {
        this.exeId = exeId;
    }

    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }

    public Integer getStuId() {
        return stuId;
    }

    public void setStuId(Integer stuId) {
        this.stuId = stuId;
    }

    public Short getLang() {
        return lang;
    }

    public void setLang(Short lang) {
        this.lang = lang;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
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

    public Short getCode() {
        return code;
    }

    public void setCode(Short code) {
        this.code = code;
    }
}
