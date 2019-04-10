package com.ysu.service.impl;

import com.ysu.common.constants.ReturnObject;
import com.ysu.common.utils.GUID;
import com.ysu.common.utils.Pager;
import com.ysu.common.utils.PagerSearchMapBuilder;
import com.ysu.common.utils.Validator;
import com.ysu.db.dao.QuestionExtMapper;
import com.ysu.db.dao.QuestionMapper;
import com.ysu.db.dao.QuestionTestMapper;
import com.ysu.db.pojo.Question;
import com.ysu.db.pojo.QuestionExt;
import com.ysu.service.IQuestionService;
import com.ysu.vo.QuestionInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Auther: han jianguo
 * @Date: 2019/3/20 15:01
 * @Description:
 **/
@Service
public class QuestionService implements IQuestionService {

    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private QuestionExtMapper questionExtMapper;
    @Autowired
    private QuestionTestMapper questionTestMapper;

    @Override
    public ReturnObject saveQuestion(Question question) {

        if (Validator.isNull(question.getQuestionId())) {
            // 新增题目信息
            question.setQuestionId(GUID.getGUID());
            question.setCreateTime(new Date());
            questionMapper.insertSelective(question);
        } else {
            // 修改题目信息
            question.setUpdateTime(new Date());
            questionMapper.updateByPrimaryKeySelective(question);
        }
        return SUCCESS.emptyObject();
    }

    @Override
    public ReturnObject saveQuestionAll(Question question, QuestionExt questionExt) {

        if (Validator.isNull(question.getQuestionId())) {
            // 新增题目信息
            question.setQuestionId(GUID.getGUID());
            question.setCreateTime(new Date());
            questionMapper.insertSelective(question);
            questionExt.setQuestionId(question.getQuestionId());
            questionExt.setCreateTime(new Date());
            questionExtMapper.insertSelective(questionExt);
        } else {
            // 修改题目信息
            question.setUpdateTime(new Date());
            questionMapper.updateByPrimaryKeySelective(question);
            questionExt.setUpdateTime(new Date());
            questionExtMapper.updateByPrimaryKeySelective(questionExt);
        }

        return SUCCESS.emptyObject();
    }

    @Override
    public ReturnObject deleteQuestion(String questionId) {

        if (Validator.isNull(questionId)) {
            return SUCCESS.emptyObject();
        }
        questionMapper.deleteByPrimaryKey(questionId);
        questionExtMapper.deleteByPrimaryKey(questionId);
        questionTestMapper.deleteByQuestionId(questionId);
        return SUCCESS.emptyObject();
    }

    @Override
    public ReturnObject getQuestionList(String questionName, Pager pager) {

        Map<String, Object> param = PagerSearchMapBuilder.on(pager)
                .append("name", questionName).toSearchMap();

        int count = questionMapper.countQuestionByName(questionName);
        List<Question> list = new ArrayList<>();
        if (count > 0) {
            list = questionMapper.selectQuestionByName(param);
        }
        return SUCCESS.toObject(pager.toShowPageBean(count, list));
    }

    @Override
    public ReturnObject getQuestionInfo(String questionId) {
        QuestionInfo questionInfo = questionMapper.getQuestionInfo(questionId);
        return SUCCESS.toObject(questionInfo);
    }
}
