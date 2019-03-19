package com.ysu.service.impl;

import com.ysu.common.constants.Constants;
import com.ysu.common.constants.ReturnObject;
import com.ysu.common.utils.FileUtil;
import com.ysu.common.utils.GUID;
import com.ysu.common.utils.Pager;
import com.ysu.common.utils.PagerSearchMapBuilder;
import com.ysu.db.dao.ReportMapper;
import com.ysu.db.dao.ReportResultMapper;
import com.ysu.db.pojo.Report;
import com.ysu.db.pojo.ReportResult;
import com.ysu.service.IReportService;
import com.ysu.textsimilarity.HammingDistance;
import com.ysu.textsimilarity.SimHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @Auther: han jianguo
 * @Date: 2019/2/22 18:01
 * @Description:
 **/
@Service
public class ReportService implements IReportService {

    @Autowired
    ReportMapper reportMapper;
    @Autowired
    ReportResultMapper reportResultMapper;

    @Override
    public ReturnObject dealReport(String url, Integer expId, Integer stuId) {

        // todo 先不考虑对于学生重复提交的问题，对于每次学生提交的实验报告都当做一个新的记录

        // 读取文档内容
        String context = FileUtil.readWordByUrl(url);

        // 计算SimHash
        SimHash simHash = new SimHash(context);
        Report report = new Report();
        report.setPkid(GUID.getGUID());
        report.setExpId(expId);
        report.setStuId(stuId);
        report.setPath(url);
        report.setSimhash(simHash.getStrSimHash());
        report.setCreateTime(new Date());
        reportMapper.insert(report);

        // 计算Hamming Distance，小于等于3则写入数据库
        Map<String, Object> param = new HashMap<>();
        param.put("expId", expId);
        param.put("stuId", stuId);
        List<Report> reports = reportMapper.selectReportBeforeNowByExpId(param);
        List<ReportResult> results = new ArrayList<>();
        for (Report rep : reports) {
            int distance = HammingDistance.getDistance(simHash.getStrSimHash(), rep.getSimhash());
            if (distance <= Constants.HAMMING_DISTANCE_WARN) {
                ReportResult reportResult = new ReportResult();
                reportResult.setPkid(GUID.getGUID());
                reportResult.setExpId(expId);
                reportResult.setHamming(distance);
                reportResult.setPreId(rep.getStuId());
                reportResult.setSufId(stuId);
                reportResult.setCreateTime(new Date());
                results.add(reportResult);
            }
        }
        reportResultMapper.batchInsert(results);

        return SUCCESS.emptyObject();
    }

    @Override
    public ReturnObject getCopyList(Integer expId, Pager pager) {

        Map<String, Object> param = PagerSearchMapBuilder.on(pager)
                .append("expId", expId).toSearchMap();

        int count = reportResultMapper.countCopyByExpId(expId);
        List<ReportResult> list = new ArrayList<>();
        if (count > 0) {
            list = reportResultMapper.getCopyList(param);
        }
        return SUCCESS.toObject(pager.toShowPageBean(count, list));
    }
}
