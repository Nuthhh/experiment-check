package com.ysu.controller.client;

import com.sun.org.apache.bcel.internal.generic.FALOAD;
import com.sun.org.apache.regexp.internal.RE;
import com.ysu.common.constants.Constants;
import com.ysu.common.constants.ReturnObject;
import com.ysu.common.utils.FileUtil;
import com.ysu.common.utils.Pager;
import com.ysu.controller.base.BaseController;
import com.ysu.service.IReportService;
import jdk.nashorn.internal.ir.RuntimeNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.ServletRequestBindingException;
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
@RequestMapping("/client/report")
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
            @RequestParam(value = "expId", required = false) final String expId,
            final HttpServletRequest request
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
     * 功能描述: 教室查询某实验下抄袭列表
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
