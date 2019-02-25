package com.ysu.db.pojo;

import java.util.Date;

public class ReportResult {
    private String pkid;

    private Integer expId;

    private Integer preId;

    private Integer sufId;

    private Integer hamming;

    private Date createTime;

    public String getPkid() {
        return pkid;
    }

    public void setPkid(String pkid) {
        this.pkid = pkid == null ? null : pkid.trim();
    }

    public Integer getExpId() {
        return expId;
    }

    public void setExpId(Integer expId) {
        this.expId = expId;
    }

    public Integer getPreId() {
        return preId;
    }

    public void setPreId(Integer preId) {
        this.preId = preId;
    }

    public Integer getSufId() {
        return sufId;
    }

    public void setSufId(Integer sufId) {
        this.sufId = sufId;
    }

    public Integer getHamming() {
        return hamming;
    }

    public void setHamming(Integer hamming) {
        this.hamming = hamming;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}