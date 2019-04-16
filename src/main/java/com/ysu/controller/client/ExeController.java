package com.ysu.controller.client;

import com.ysu.common.constants.ReturnObject;
import com.ysu.common.utils.Pager;
import com.ysu.controller.base.BaseController;
import com.ysu.db.pojo.Exe;
import com.ysu.service.IExeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @Auther: han jianguo
 * @Date: 2019/3/22 09:19
 * @Description:
 **/
@RestController
@RequestMapping("/exe")
public class ExeController extends BaseController {

    @Autowired
    private IExeService exeService;

    /**
     * 功能描述: 学生提交程序评测，返回评测结果
     *
     * @auther: han jianguo
     * @date: 2019/3/22 9:20
     */
    @RequestMapping("/student/submit")
    public String studentSubmit(
            @RequestParam(value = "questionId", required = false) final String questionId,
            @RequestParam(value = "stuId", required = false) final String stuId,
            @RequestParam(value = "context", required = false) final String context,
            @RequestParam(value = "lang", required = false) final String lang
    ) {
        if (checkNull(questionId, stuId, context, lang)) {
            return PARAM_MISSING_STRING;
        }
        if (checkInteger(stuId)) {
            return PARAM_FORMAT_ERROR_STRING;
        }

        Exe exe = new Exe();
        exe.setQuestionId(questionId);
        exe.setStuId(parseInt(stuId));
        exe.setContext(context);
        exe.setLang(parseShort(lang));
        ReturnObject result = exeService.submit(exe);
        return json(result);
    }

    /**
     * 功能描述: 学生查看某一道题的作答情况
     *
     * @auther: han jianguo
     * @date: 2019/4/15 14:00
     */
    @RequestMapping("/student/answer")
    public String studentAnswer(
            @RequestParam(value = "questionId", required = false) final String questionId,
            @RequestParam(value = "stuId", required = false) final String stuId

    ) {
        if (checkNull(questionId, stuId)) {
            return PARAM_MISSING_STRING;
        }
        if (checkInteger(stuId)) {
            return PARAM_FORMAT_ERROR_STRING;
        }

        ReturnObject result = exeService.answer(questionId, parseInt(stuId));
        return json(result);
    }

    /**
     * 功能描述: 学生查看自己某次提交的详细情况
     *
     * @auther: han jianguo
     * @date: 2019/4/16 13:40
     */
    @RequestMapping("/student/answerOne")
    public String studentAnswerOne(
            @RequestParam(value = "exeId", required = false) final String exeId
    ) {
        if (checkNull(exeId)) {
            return PARAM_MISSING_STRING;
        }

        ReturnObject result = exeService.answerOne(exeId);
        return json(result);
    }


    /**
     * 功能描述: 教师查看学生作答情况
     *
     * @auther: han jianguo
     * @date: 2019/4/15 14:05
     */
    @RequestMapping("/teacher/result")
    public String teacherResult(
            @RequestParam(value = "questionId", required = false) final String questionId,
            final HttpServletRequest request
    ) {
        if (checkNull(questionId)) {
            return PARAM_MISSING_STRING;
        }

        Pager pager = getPaper(request);
        ReturnObject result = exeService.exeResultList(questionId, pager);
        return json(result);
    }


}
