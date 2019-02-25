package com.ysu.db.dao;

import com.ysu.db.pojo.Report;

import java.util.List;
import java.util.Map;

public interface ReportMapper {
    int deleteByPrimaryKey(String pkid);

    int insert(Report record);

    int insertSelective(Report record);

    Report selectByPrimaryKey(String pkid);

    int updateByPrimaryKeySelective(Report record);

    int updateByPrimaryKey(Report record);

    /**
     *
     * 功能描述: 获取某门实验课下，学生提交之前的报告信息
     *
     * @auther: han jianguo
     * @date: 2019/2/25 18:28
     */
    List<Report> selectReportBeforeNowByExpId(Map<String,Object> param);
}