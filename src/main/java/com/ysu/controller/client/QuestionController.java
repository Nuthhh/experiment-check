package com.ysu.controller.client;

import com.ysu.common.constants.ReturnObject;
import com.ysu.common.utils.Pager;
import com.ysu.controller.base.BaseController;
import com.ysu.db.pojo.Question;
import com.ysu.db.pojo.QuestionExt;
import com.ysu.service.IQuestionExtService;
import com.ysu.service.IQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @Auther: han jianguo
 * @Date: 2019/3/20 15:09
 * @Description:
 **/
@RestController
@RequestMapping("/question/teacher")
public class QuestionController extends BaseController {

    @Autowired
    private IQuestionService questionService;
    @Autowired
    IQuestionExtService questionExtService;

    /**
     * 功能描述: 新增、修改题目
     *
     * @auther: han jianguo
     * @date: 2019/3/20 15:10
     */
    @RequestMapping("/save")
    public String save(
            @RequestParam(value = "questionId", required = false) final String questionId,
            @RequestParam(value = "name", required = false) final String name,
            @RequestParam(value = "time", required = false) final String time,
            @RequestParam(value = "memory", required = false) final String memory
    ) {

        if (checkNull(name, time, memory)) {
            return PARAM_MISSING_STRING;
        }
        if (checkInteger(time, memory)) {
            return PARAM_FORMAT_ERROR_STRING;
        }

        Question question = new Question();
        question.setQuestionId(questionId);
        question.setName(name);
        question.setTime(parseInt(time));
        question.setMemory(parseInt(memory));
        ReturnObject result = questionService.saveQuestion(question);
        return json(result);
    }

    /**
     * 功能描述: 保存、修改题目下所有信息
     *
     * @auther: han jianguo
     * @date: 2019/4/10 15:46
     */
    @RequestMapping("/saveAll")
    public String saveAll(
            @RequestParam(value = "questionId", required = false) final String questionId,
            @RequestParam(value = "name", required = false) final String name,
            @RequestParam(value = "time", required = false) final String time,
            @RequestParam(value = "memory", required = false) final String memory,
            @RequestParam(value = "context", required = false) final String context,
            @RequestParam(value = "example", required = false) final String example
    ) {
        if (checkNull(name, time, memory, context, example)) {
            return PARAM_MISSING_STRING;
        }
        if (checkInteger(time, memory)) {
            return PARAM_FORMAT_ERROR_STRING;
        }

        Question question = new Question();
        question.setQuestionId(questionId);
        question.setName(name);
        question.setTime(parseInt(time));
        question.setMemory(parseInt(memory));
        QuestionExt questionExt = new QuestionExt();
        questionExt.setQuestionId(questionId);
        questionExt.setContext(context);
        questionExt.setExample(example);
        ReturnObject result = questionService.saveQuestionAll(question, questionExt);

        return json(result);
    }

    /**
     * 功能描述: 删除题目下全部信息
     *
     * @auther: han jianguo
     * @date: 2019/3/20 15:19
     */
    @RequestMapping("/delete")
    public String delete(
            @RequestParam(value = "questionId", required = false) final String questionId
    ) {
        if (checkNull(questionId)) {
            return PARAM_MISSING_STRING;
        }

        ReturnObject result = questionService.deleteQuestion(questionId);
        return json(result);
    }

    /**
     * 功能描述: 获取题目列表
     *
     * @auther: han jianguo
     * @date: 2019/3/20 15:20
     */
    @RequestMapping("/list")
    public String list(
            @RequestParam(value = "name", required = false) final String name,
            final HttpServletRequest request
    ) {
        Pager pager = getPaper(request);
        ReturnObject result = questionService.getQuestionList(name, pager);
        return json(result);
    }


    /**
     * 功能描述: 根据id获取题目下所有信息
     *
     * @auther: han jianguo
     * @date: 2019/4/10 15:55
     */
    @RequestMapping("/info")
    public String info(
            @RequestParam(value = "questionId", required = false) final String questionId
    ) {
        if (checkNull(questionId)) {
            return PARAM_MISSING_STRING;
        }
        ReturnObject result = questionService.getQuestionInfo(questionId);
        return json(result);
    }

}
