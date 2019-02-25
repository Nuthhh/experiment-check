package com.ysu.service;

import com.ysu.common.constants.BaseReturnCode;
import com.ysu.common.constants.ReturnObject;

/**
 * @Auther: han jianguo
 * @Date: 2019/2/22 17:48
 * @Description:
 **/
public interface IReportService extends BaseReturnCode {

    /**
     *
     * 功能描述: 计算实验报告SimHash、Hamming Distance
     *
     * @auther: han jianguo
     * @date: 2019/2/22 17:53
     */
    ReturnObject dealReport(String url,Integer expId,Integer stuId);
}
