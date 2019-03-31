package com.ysu.service.impl;

import com.ysu.common.constants.Constants;
import com.ysu.common.constants.ReturnObject;
import com.ysu.common.utils.*;
import com.ysu.db.dao.QuestionTestMapper;
import com.ysu.db.pojo.QuestionTest;
import com.ysu.service.IQuestionTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Auther: han jianguo
 * @Date: 2019/3/20 17:17
 * @Description:
 **/
@Service
public class QuestionTestService implements IQuestionTestService {

    @Autowired
    private QuestionTestMapper questionTestMapper;

    @Override
    public ReturnObject save(QuestionTest questionTest) {

        questionTest.setPkid(GUID.getGUID());
        questionTest.setCreateTime(new Date());
        questionTestMapper.insertSelective(questionTest);

        return SUCCESS.emptyObject();
    }

    @Override
    public ReturnObject delete(String pkId) {
        questionTestMapper.deleteByPrimaryKey(pkId);
        return SUCCESS.emptyObject();
    }

    @Override
    public ReturnObject getQuestionTestList(String questionId, Pager pager) {
        Map<String, Object> param = PagerSearchMapBuilder.on(pager)
                .append("questionId", questionId).toSearchMap();

        int count = questionTestMapper.countTestByQuestionId(questionId);
        List<QuestionTest> list = new ArrayList<>();
        if (count > 0) {
            list = questionTestMapper.getTestByQuestionId(param);
        }
        return SUCCESS.toObject(pager.toShowPageBean(count, list));
    }

    @Override
    public ReturnObject updateTestcase(String questionId) {
        PythonUtil.runPython(Constants.PYTHON_COMMAND, Constants.PYTHON_SCRIPTPATH_UPDATE, questionId);
        return SUCCESS.emptyObject();
    }
}
