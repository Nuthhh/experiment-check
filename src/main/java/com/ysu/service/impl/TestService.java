package com.ysu.service.impl;

import com.ysu.common.constants.ReturnObject;
import com.ysu.db.dao.TestMapper;
import com.ysu.service.ITestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Auther: han jianguo
 * @Date: 2019/2/18 22:10
 * @Description:
 **/
@Service
public class TestService implements ITestService {

    @Autowired
    private TestMapper testMapper;

    @Override
    public ReturnObject getTest(Integer id) {
        return SUCCESS.toObject(testMapper.selectByPrimaryKey(id));
    }
}
