package com.ysu.controller.client;

import com.ysu.common.constants.ReturnObject;
import com.ysu.controller.base.BaseController;
import com.ysu.db.pojo.QuestionExt;
import com.ysu.service.IQuestionExtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: han jianguo
 * @Date: 2019/3/20 17:02
 * @Description:
 **/
@RestController
@RequestMapping("/questionExt/teacher")
public class QuestionExtController extends BaseController {

    @Autowired
    IQuestionExtService questionExtService;

    /**
     * 功能描述: 新增、修改题目扩展信息
     *
     * @auther: han jianguo
     * @date: 2019/3/20 17:03
     */
    @RequestMapping("/save")
    public String save(
            @RequestParam(value = "questionId", required = false) final String questionId,
            @RequestParam(value = "context", required = false) final String context,
            @RequestParam(value = "example", required = false) final String example
    ) {
        if (checkNull(questionId, context, example)) {
            return PARAM_MISSING_STRING;
        }

        QuestionExt questionExt = new QuestionExt();
        questionExt.setQuestionId(questionId);
        questionExt.setContext(context);
        questionExt.setExample(example);
        ReturnObject result = questionExtService.save(questionExt);
        return json(result);
    }

    /**
     * 功能描述: 查找某题目下扩展信息
     *
     * @auther: han jianguo
     * @date: 2019/3/20 17:07
     */
    @RequestMapping("/getExtInfo")
    public String getExtInfo(
            @RequestParam(value = "questionId", required = false) final String questionId
    ) {
        if (checkNull(questionId)) {
            return PARAM_MISSING_STRING;
        }

        ReturnObject reslut = questionExtService.getQuestionExtInfo(questionId);
        return json(reslut);
    }
}
