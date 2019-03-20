package com.ysu.service.impl;

import com.ysu.common.constants.ReturnObject;
import com.ysu.common.utils.Validator;
import com.ysu.db.dao.QuestionExtMapper;
import com.ysu.db.pojo.QuestionExt;
import com.ysu.service.IQuestionExtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @Auther: han jianguo
 * @Date: 2019/3/20 16:55
 * @Description:
 **/
@Service
public class QuestionExtService implements IQuestionExtService {

    @Autowired
    QuestionExtMapper questionExtMapper;

    @Override
    public ReturnObject save(QuestionExt questionExt) {

        QuestionExt q = questionExtMapper.selectByPrimaryKey(questionExt.getQuestionId());
        if (Validator.isNull(q)) {
            questionExt.setCreateTime(new Date());
            questionExtMapper.insertSelective(questionExt);
        } else {
            questionExt.setUpdateTime(new Date());
            questionExtMapper.updateByPrimaryKeySelective(questionExt);
        }
        return SUCCESS.emptyObject();
    }

    @Override
    public ReturnObject getQuestionExtInfo(String questionId) {
        QuestionExt questionExT = questionExtMapper.selectByPrimaryKey(questionId);
        return SUCCESS.toObject(questionExT);
    }
}
