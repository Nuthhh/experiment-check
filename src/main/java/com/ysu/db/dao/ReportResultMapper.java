package com.ysu.db.dao;

import com.ysu.db.pojo.ReportResult;

import java.util.List;
import java.util.Map;

public interface ReportResultMapper {
    int deleteByPrimaryKey(String pkid);

    int insert(ReportResult record);

    int insertSelective(ReportResult record);

    ReportResult selectByPrimaryKey(String pkid);

    int updateByPrimaryKeySelective(ReportResult record);

    int updateByPrimaryKey(ReportResult record);

    /**
     *
     * 功能描述: 批量添加
     *
     * @auther: han jianguo
     * @date: 2019/2/25 18:30
     */
    int batchInsert(List<ReportResult> reportResultList);

    /**
     *
     * 功能描述: 查询某实验下抄袭的记录数
     *
     * @auther: han jianguo
     * @date: 2019/3/19 9:24
     */
    int countCopyByExpId(Integer expId);

    /**
     *
     * 功能描述: 查询某实验下抄袭的记录
     *
     * @auther: han jianguo
     * @date: 2019/3/19 9:27
     */
    List<ReportResult> getCopyList(Map<String,Object> param);
}