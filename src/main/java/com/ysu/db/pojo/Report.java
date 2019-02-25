package com.ysu.db.pojo;

import java.util.Date;

public class Report {
    private String pkid;

    private Integer expId;

    private Integer stuId;

    private String path;

    private String simhash;

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

    public Integer getStuId() {
        return stuId;
    }

    public void setStuId(Integer stuId) {
        this.stuId = stuId;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path == null ? null : path.trim();
    }

    public String getSimhash() {
        return simhash;
    }

    public void setSimhash(String simhash) {
        this.simhash = simhash == null ? null : simhash.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}