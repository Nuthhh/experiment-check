package com.ysu.service;

import com.ysu.common.constants.BaseReturnCode;
import com.ysu.common.constants.ReturnObject;

/**
 * @Auther: han jianguo
 * @Date: 2019/2/18 22:09
 * @Description:
 **/
public interface ITestService extends BaseReturnCode {

    ReturnObject getTest(Integer id);
}
