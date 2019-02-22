package com.ysu.service.impl;

import com.ysu.common.constants.ReturnObject;
import com.ysu.service.IReportService;
import org.springframework.stereotype.Service;

/**
 * @Auther: han jianguo
 * @Date: 2019/2/22 18:01
 * @Description:
 **/
@Service
public class ReportService implements IReportService {

    @Override
    public ReturnObject dealReport(String url) {

        // 计算SimHash

        // 计算Hamming Distance，超过3则写入数据库

        return SUCCESS.emptyObject();
    }
}
