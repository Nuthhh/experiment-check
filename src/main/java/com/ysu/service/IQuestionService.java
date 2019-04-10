package com.ysu.service;

import com.ysu.common.constants.BaseReturnCode;
import com.ysu.common.constants.ReturnObject;
import com.ysu.common.utils.Pager;
import com.ysu.db.pojo.Question;
import com.ysu.db.pojo.QuestionExt;

/**
 * @Auther: han jianguo
 * @Date: 2019/3/20 15:01
 * @Description:
 **/
public interface IQuestionService extends BaseReturnCode {

    /**
     * 功能描述: 增加/修改题目信息
     *
     * @auther: han jianguo
     * @date: 2019/3/20 15:02
     */
    ReturnObject saveQuestion(Question question);

    /**
     * 功能描述: 增加、修改题目所有信息
     *
     * @auther: han jianguo
     * @date: 2019/4/10 15:50
     */
    ReturnObject saveQuestionAll(Question question, QuestionExt questionExt);

    /**
     * 功能描述: 删除题目
     *
     * @auther: han jianguo
     * @date: 2019/3/20 15:04
     */
    ReturnObject deleteQuestion(String questionId);

    /**
     * 功能描述: 查询题库信息
     *
     * @auther: han jianguo
     * @date: 2019/3/20 15:04
     */
    ReturnObject getQuestionList(String questionName, Pager pager);

    /**
     * 功能描述: 查询id下题目所有信息
     *
     * @auther: han jianguo
     * @date: 2019/4/10 16:02
     */
    ReturnObject getQuestionInfo(String questionId);


}
