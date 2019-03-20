package com.ysu.service;

import com.ysu.common.constants.BaseReturnCode;
import com.ysu.common.constants.ReturnObject;
import com.ysu.db.pojo.QuestionExt;

/**
 * @Auther: han jianguo
 * @Date: 2019/3/20 16:51
 * @Description:
 **/
public interface IQuestionExtService extends BaseReturnCode {

    /**
     *
     * 功能描述: 新增、修改 题目扩展信息
     *
     * @auther: han jianguo
     * @date: 2019/3/20 16:51
     */
    ReturnObject save(QuestionExt questionExt);

    /**
     *
     * 功能描述: 查询某题目下的扩展信息
     *
     * @auther: han jianguo
     * @date: 2019/3/20 16:53
     */
    ReturnObject getQuestionExtInfo(String questionId);
}
