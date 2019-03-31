package com.ysu.controller.client;

import com.ysu.common.constants.ReturnObject;
import com.ysu.common.constants.ReturnResult;
import com.ysu.common.utils.HttpClientUtil;
import com.ysu.common.utils.Pager;
import com.ysu.controller.base.BaseController;
import com.ysu.db.pojo.QuestionTest;
import com.ysu.service.IQuestionTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: han jianguo
 * @Date: 2019/3/21 08:46
 * @Description:
 **/
@RestController
@RequestMapping("/questionTest/teacher")
public class QuestionTestController extends BaseController {

    @Autowired
    private IQuestionTestService questionTestService;

    /**
     * 功能描述: 新增测试用例
     *
     * @auther: han jianguo
     * @date: 2019/3/21 8:46
     */
    @RequestMapping("/save")
    public String save(
            @RequestParam(value = "questionId", required = false) final String questionId,
            @RequestParam(value = "questionIn", required = false) final String questionIn,
            @RequestParam(value = "questionOut", required = false) final String questionOut
    ) {
        if (checkNull(questionId, questionIn, questionOut)) {
            return PARAM_MISSING_STRING;
        }

        QuestionTest questionTest = new QuestionTest();
        questionTest.setQuestionId(questionId);
        questionTest.setQuestionIn(questionIn);
        questionTest.setQuestionOut(questionOut);
        ReturnObject result = questionTestService.save(questionTest);
        return json(result);
    }

    /**
     * 功能描述: 删除测试用例
     *
     * @auther: han jianguo
     * @date: 2019/3/21 8:52
     */
    @RequestMapping("/delete")
    public String delete(
            @RequestParam(value = "pkid", required = false) final String pkid
    ) {
        if (checkNull(pkid)) {
            return PARAM_MISSING_STRING;
        }

        ReturnObject result = questionTestService.delete(pkid);
        return json(result);
    }

    /**
     * 功能描述: 查询某题目下测试用例
     *
     * @auther: han jianguo
     * @date: 2019/3/21 8:53
     */
    @RequestMapping("/list")
    public String list(
            @RequestParam(value = "questionId", required = false) final String questionId,
            final HttpServletRequest request
    ) {
        if (checkNull(questionId)) {
            return PARAM_MISSING_STRING;
        }

        Pager pager = getPaper(request);
        ReturnObject result = questionTestService.getQuestionTestList(questionId, pager);
        return json(result);
    }

    /**
     * '
     * 功能描述: 更新某题目下测试用例
     *
     * @auther: han jianguo
     * @date: 2019/3/27 13:54
     */
    @RequestMapping("/update")
    public String update(
            @RequestParam(value = "questionId", required = false) final String questionId
    ) {
        if (checkNull(questionId)) {
            return PARAM_MISSING_STRING;
        }
        ReturnObject result = questionTestService.updateTestcase(questionId);
        return json(result);
    }

}
