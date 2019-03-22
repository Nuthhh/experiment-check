package com.ysu.controller.client;

import com.sun.xml.internal.ws.server.ServerRtException;
import com.ysu.common.constants.ReturnObject;
import com.ysu.controller.base.BaseController;
import com.ysu.db.pojo.Exe;
import com.ysu.service.IExeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
    public String submit(
            @RequestParam(value = "questionId", required = false) final String questionId,
            @RequestParam(value = "stuId", required = false) final String stuId,
            @RequestParam(value = "context", required = false) final String context,
            @RequestParam(value = "lang", required = false) final String lang
    ) {
        if(checkNull(questionId,stuId,context,lang)){
            return PARAM_MISSING_STRING;
        }
        if(checkInteger(stuId)){
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


}
