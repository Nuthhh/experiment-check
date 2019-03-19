package com.ysu.service;

import com.ysu.common.constants.BaseReturnCode;
import com.ysu.common.constants.ReturnObject;
import com.ysu.common.utils.Pager;

/**
 * @Auther: han jianguo
 * @Date: 2019/2/22 17:48
 * @Description:
 **/
public interface IReportService extends BaseReturnCode {

    /**
     *
     * 功能描述: 计算实验报告SimHash、Hamming Distance
     * @param url 报告在文件服务器的路径
     * @param expId 实验id
     * @param stuId  学生id
     *
     * @auther: han jianguo
     * @date: 2019/2/22 17:53
     */
    ReturnObject dealReport(String url,Integer expId,Integer stuId);

   /**
    *
    * 功能描述: 查询某实验下判定抄袭列表
    *
    * @auther: han jianguo
    * @date: 2019/3/19 9:18
    */
   ReturnObject getCopyList(Integer expId, Pager pager);
}
