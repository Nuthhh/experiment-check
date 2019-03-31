package com.ysu.controller.client;

import com.ysu.controller.base.BaseController;
import com.ysu.service.ITestServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: han jianguo
 * @Date: 2019/3/31 14:05
 * @Description:
 **/
@RestController
@RequestMapping("/test")
public class TestController extends BaseController {

    @Autowired
    ITestServer testServer;

    @RequestMapping("/run")
    public String run() {
        return json(testServer.run());
    }

    @RequestMapping("/update")
    public String update(){
        return json(testServer.update());
    }


}
