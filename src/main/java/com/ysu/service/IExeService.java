package com.ysu.service;

import com.ysu.common.constants.BaseReturnCode;
import com.ysu.common.constants.ReturnCode;
import com.ysu.common.constants.ReturnObject;
import com.ysu.common.utils.Pager;
import com.ysu.db.pojo.Exe;

/**
 * @Auther: han jianguo
 * @Date: 2019/3/22 09:01
 * @Description:
 **/
public interface IExeService extends BaseReturnCode {

    ReturnCode NO_LANG = ReturnCode.build(1, "当前编程语言类型不支持");

    /**
     * 功能描述: 学生提交程序，返回评测结果
     *
     * @auther: han jianguo
     * @date: 2019/3/22 9:13
     */
    ReturnObject submit(Exe exe);

    /**
     * 功能描述: 学生查看某题的提交记录
     *
     * @auther: han jianguo
     * @date: 2019/4/15 14:12
     */
    ReturnObject answer(String questionId, Integer stuId);

    /**
     *
     * 功能描述: 学生查看某次自己提交的详细记录
     *
     * @auther: han jianguo
     * @date: 2019/4/16 13:42
     */
    ReturnObject answerOne(String exeId);

    /**
     * 功能描述: 教师查看某道题目下学生作答情况
     *
     * @auther: han jianguo
     * @date: 2019/4/15 14:17
     */
    ReturnObject exeResultList(String questionId, Pager pager);


}
