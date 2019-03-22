package com.ysu.service.impl.test;

import com.ysu.common.utils.Pager;
import com.ysu.common.utils.PagerBean;
import com.ysu.db.pojo.Question;
import com.ysu.service.impl.QuestionService;
import org.hibernate.validator.constraints.EAN;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class QuestionServiceTest {

    @Autowired
    QuestionService questionService;

    @Test
    public void saveQuestion() {
        Question question = new Question();
        // question.setQuestionId("461C33D9B44709DA77FD49B0F0B3D160");
        question.setName("过河");
        question.setTime(123);
        question.setMemory(345);
        questionService.saveQuestion(question);
    }

    @Test
    public void deleteQuestion() {

        questionService.deleteQuestion("461C33D9B44709DA77FD49B0F0B3D160");

    }

    @Test
    public void getQuestionList() {

        Pager pager = new Pager();
        pager.setPageSize(1);
        pager.setCurrentPage(2);
        PagerBean pagerBean = (PagerBean) questionService.getQuestionList("", pager).getData();
        List<Question> list = pagerBean.getList();
        System.out.println("size:" + list.size());
        for (Question question : list) {
            System.out.println(question.toString());
        }
    }
}