package com.ysu.service;

import com.ysu.common.constants.BaseReturnCode;
import com.ysu.common.constants.ReturnCode;
import com.ysu.common.constants.ReturnObject;
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
}
