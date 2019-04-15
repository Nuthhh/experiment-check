package com.ysu.vo;

import java.util.Date;

/**
 * @Auther: han jianguo
 * @Date: 2019/4/15 15:12
 * @Description: 教师端查看程序评测结果需返回的值
 **/
public class ExeTeacher {

    private Integer stuId; // 学生id

    private Short pass; // 是否通过某题 1通过 2否

    private Integer submitSum; // 对于某道题目的作答次数

    private Date createTime; // 最后一次作答题目的时间

    @Override
    public String toString() {
        return "ExeTeacher{" +
                "stuId=" + stuId +
                ", pass=" + pass +
                ", submitSum=" + submitSum +
                ", createTime=" + createTime +
                '}';
    }

    public Integer getStuId() {
        return stuId;
    }

    public void setStuId(Integer stuId) {
        this.stuId = stuId;
    }

    public Short getPass() {
        return pass;
    }

    public void setPass(Short pass) {
        this.pass = pass;
    }

    public Integer getSubmitSum() {
        return submitSum;
    }

    public void setSubmitSum(Integer submitSum) {
        this.submitSum = submitSum;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
