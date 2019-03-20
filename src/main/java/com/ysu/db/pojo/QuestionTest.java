package com.ysu.db.pojo;

import java.util.Date;

public class QuestionTest {
    private String pkid;

    private String questionId;

    private String questionIn;

    private String questionOut;

    private Date createTime;

    @Override
    public String toString() {
        return "QuestionTest{" +
                "pkid='" + pkid + '\'' +
                ", questionId='" + questionId + '\'' +
                ", questionIn='" + questionIn + '\'' +
                ", questionOut='" + questionOut + '\'' +
                ", createTime=" + createTime +
                '}';
    }

    public String getPkid() {
        return pkid;
    }

    public void setPkid(String pkid) {
        this.pkid = pkid == null ? null : pkid.trim();
    }

    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId == null ? null : questionId.trim();
    }

    public String getQuestionIn() {
        return questionIn;
    }

    public void setQuestionIn(String questionIn) {
        this.questionIn = questionIn == null ? null : questionIn.trim();
    }

    public String getQuestionOut() {
        return questionOut;
    }

    public void setQuestionOut(String questionOut) {
        this.questionOut = questionOut == null ? null : questionOut.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}