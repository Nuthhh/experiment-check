package com.ysu.service.impl.test;

import com.ysu.db.pojo.QuestionExt;
import com.ysu.service.impl.QuestionExtService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class QuestionExtServiceTest {

    @Autowired
    QuestionExtService questionExtService;

    @Test
    public void save() {
        QuestionExt questionExt = new QuestionExt();
        questionExt.setQuestionId("1E3B294F2EE0ADE71A2C5973436D2C43");
        questionExt.setContext("过河问题很有趣");
        questionExt.setExample("这节课上的反馈的首付款减肥的上课");
        questionExtService.save(questionExt);
    }

    @Test
    public void getQuestionExtInfo() {

        System.out.println(questionExtService.getQuestionExtInfo("1E3B294F2EE0ADE71A2C5973436D2C43").getData().toString());
    }
}