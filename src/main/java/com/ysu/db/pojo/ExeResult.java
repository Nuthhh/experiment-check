package com.ysu.db.pojo;

import java.util.Date;

public class ExeResult {
    private String exeId;

    private Short lang;

    private Integer time;

    private Integer memory;

    private Short code;

    private Date createTime;

    public String getExeId() {
        return exeId;
    }

    public void setExeId(String exeId) {
        this.exeId = exeId == null ? null : exeId.trim();
    }

    public Short getLang() {
        return lang;
    }

    public void setLang(Short lang) {
        this.lang = lang;
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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}