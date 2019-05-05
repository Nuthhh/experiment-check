package com.ysu.controller.client;

import com.ysu.common.constants.Constants;
import com.ysu.common.constants.ReturnObject;
import com.ysu.common.utils.FileUtil;
import com.ysu.common.utils.Pager;
import com.ysu.controller.base.BaseController;
import com.ysu.service.IReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

/**
 * @Auther: han jianguo
 * @Date: 2019/3/16 17:11
 * @Description:
 **/
@RestController
@RequestMapping("/report")
public class ReportController extends BaseController {

    @Autowired
    private IReportService reportService;

    /**
     * 功能描述: 学生上交实验报告
     *
     * @auther: han jianguo
     * @date: 2019/3/16 20:32
     */
    @RequestMapping("/student/submit")
    public String submit(
            @RequestParam(value = "report", required = false) final MultipartFile report,
            @RequestParam(value = "stuId", required = false) final String stuId,
            @RequestParam(value = "expId", required = false) final String expId
    ) {
        if (checkNull(stuId, expId, report.getOriginalFilename())) {
            return PARAM_MISSING_STRING;
        }

        if (checkInteger(stuId, expId)) {
            return PARAM_FORMAT_ERROR_STRING;
        }

        // 上传文件
        String path = Constants.FILE_SERVER_ADDRESS_PRE + Constants.REPORT_SERVER_ADRESS_PRE;
        String fileName = FileUtil.uploadOneFile(report, path);

        ReturnObject result = reportService.dealReport(path + fileName, parseInt(expId), parseInt(stuId));

        return json(result);
    }

    /**
     * 功能描述: 学生报告查重
     *
     * @auther: han jianguo
     * @date: 2019/5/5 10:23
     */
    @RequestMapping("/student/simhash")
    public String studentSimhash(
            @RequestParam(value = "reportUrl", required = false) final String reportUrl,
            @RequestParam(value = "stuId", required = false) final String stuId,
            @RequestParam(value = "expId", required = false) final String expId
    ) {
        if (checkNull(stuId, expId, reportUrl)) {
            return PARAM_MISSING_STRING;
        }

        if (checkInteger(stuId, expId)) {
            return PARAM_FORMAT_ERROR_STRING;
        }

        ReturnObject result = reportService.dealReport(reportUrl, parseInt(expId), parseInt(stuId));
        return json(result);
    }

    /**
     * 功能描述: 教师查询某实验下抄袭列表
     *
     * @auther: han jianguo
     * @date: 2019/3/19 9:32
     */
    @RequestMapping("/teacher/getCopyList")
    public String getCopyList(
            @RequestParam(value = "expId", required = false) final String expId,
            final HttpServletRequest request
    ) {
        if (checkNull(expId)) {
            return PARAM_MISSING_STRING;
        }
        if (checkInteger(expId)) {
            return PARAM_FORMAT_ERROR_STRING;
        }

        Pager pager = getPaper(request);
        ReturnObject result = reportService.getCopyList(parseInt(expId), pager);

        return json(result);
    }


}
