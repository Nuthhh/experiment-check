package com.ysu.service;

import com.ysu.common.constants.BaseReturnCode;
import com.ysu.common.constants.ReturnObject;
import com.ysu.common.utils.Pager;
import com.ysu.db.pojo.QuestionTest;

/**
 * @Auther: han jianguo
 * @Date: 2019/3/20 17:15
 * @Description:
 **/
public interface IQuestionTestService extends BaseReturnCode {

    /**
     * 功能描述: 添加测试用例
     *
     * @auther: han jianguo
     * @date: 2019/3/20 17:15
     */
    ReturnObject save(QuestionTest questionTest);

    /**
     * 功能描述: 删除测试用例
     *
     * @auther: han jianguo
     * @date: 2019/3/20 17:16
     */
    ReturnObject delete(String pkId);

    /**
     * 功能描述: 查看所有测试用例
     *
     * @auther: han jianguo
     * @date: 2019/3/20 17:16
     */
    ReturnObject getQuestionTestList(String questionId,Pager pager);

    /**
     *
     * 功能描述: 更新评测机的测试用例
     *
     * @auther: han jianguo
     * @date: 2019/3/31 14:50
     */
    ReturnObject updateTestcase(String questionId);

}
