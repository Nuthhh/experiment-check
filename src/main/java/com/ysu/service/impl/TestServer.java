package com.ysu.service.impl;

import com.ysu.common.constants.Constants;
import com.ysu.common.constants.ReturnObject;
import com.ysu.common.utils.GUID;
import com.ysu.common.utils.PythonUtil;
import com.ysu.db.dao.ExeResultMapper;
import com.ysu.db.pojo.ExeResult;
import com.ysu.service.ITestServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Auther: han jianguo
 * @Date: 2019/3/31 14:08
 * @Description:
 **/
@Service
public class TestServer implements ITestServer {

    @Autowired
    ExeResultMapper exeResultMapper;

    @Override
    public ReturnObject run() {
        String exeId = GUID.getGUID();
        String fileName = "add.cpp";
        String questionId = "1E3B294F2EE0ADE71A2C5973436D2C43";
        int lang = 2;
        PythonUtil.runPython(Constants.PYTHON_COMMAND, Constants.PYTHON_SCRIPTPATH_JUDGE, exeId, fileName, questionId, String.valueOf(lang));
        ExeResult exeResult = exeResultMapper.selectByPrimaryKey(exeId);
        return SUCCESS.toObject(exeResult);
    }

    @Override
    public ReturnObject update() {
        String questionId = "1E3B294F2EE0ADE71A2C5973436D2C43";
        PythonUtil.runPython(Constants.PYTHON_COMMAND, Constants.PYTHON_SCRIPTPATH_UPDATE,questionId );
        return SUCCESS.emptyObject();
    }
}
