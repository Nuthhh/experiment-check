package com.ysu.vo;

import java.util.Date;

/**
 * @Auther: han jianguo
 * @Date: 2019/4/10 16:01
 * @Description:
 **/
public class QuestionInfo {

    private String questionId;

    private String name;

    private Integer time;

    private Integer memory;

    private String context;

    private String example;

    @Override
    public String toString() {
        return "QuestionInfo{" +
                "questionId='" + questionId + '\'' +
                ", name='" + name + '\'' +
                ", time=" + time +
                ", memory=" + memory +
                ", context='" + context + '\'' +
                ", example='" + example + '\'' +
                '}';
    }

    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public String getExample() {
        return example;
    }

    public void setExample(String example) {
        this.example = example;
    }
}
