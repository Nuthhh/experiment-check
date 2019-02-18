package com.ysu.controller.client;

import com.ysu.controller.base.BaseController;
import com.ysu.service.ITestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: han jianguo
 * @Date: 2019/2/18 22:15
 * @Description:
 **/
@RestController
public class TestController extends BaseController {

    @Autowired
    private ITestService testService;

    @RequestMapping("/hi")
    public String hi(@RequestParam(value = "id", required = false) int id) {
        return json(testService.getTest(id));
    }
}
