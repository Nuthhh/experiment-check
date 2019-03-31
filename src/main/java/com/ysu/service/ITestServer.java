package com.ysu.service;

import com.ysu.common.constants.BaseReturnCode;
import com.ysu.common.constants.ReturnObject;

/**
 * @Auther: han jianguo
 * @Date: 2019/3/31 14:07
 * @Description:
 **/
public interface ITestServer extends BaseReturnCode {

    ReturnObject run();

    ReturnObject update();

}
