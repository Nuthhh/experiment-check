package com.ysu.service.impl;

import com.ysu.common.constants.Constants;
import com.ysu.common.constants.ReturnObject;
import com.ysu.common.utils.FileUtil;
import com.ysu.common.utils.GUID;
import com.ysu.db.dao.ExeMapper;
import com.ysu.db.dao.ExeResultMapper;
import com.ysu.db.pojo.Exe;
import com.ysu.service.IExeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.Date;

/**
 * @Auther: han jianguo
 * @Date: 2019/3/22 09:16
 * @Description:
 **/
@Service
public class ExeService implements IExeService {

    @Autowired
    private ExeMapper exeMapper;
    @Autowired
    private ExeResultMapper exeResultMapper;

    @Override
    public ReturnObject submit(Exe exe) {

        // 将学生程序储存到评测机
        String filePath = Constants.FILE_SERVER_ADDRESS_PRE + Constants.EXE_SERVER_ADDRESS_PRE; // 学生提交程序在服务器临时储存位置
        StringBuffer fileName = new StringBuffer(); // 学生提交程序临时命名
        fileName.append(exe.getQuestionId() + "-" + exe.getStuId() + "-" + System.currentTimeMillis());
        if (Constants.EXE_LANG_JAVA == exe.getLang()) {
            fileName.append(".java");
        } else if (Constants.EXE_LANG_CC == exe.getLang()) {
            fileName.append(".cpp");
        } else {
            return NO_LANG.emptyObject();
        }
        FileUtil.uploadString(exe.getContext(), filePath, fileName.toString());

        // 将学生作答相关信息储存到数据库
        exe.setExeId(GUID.getGUID());
        exe.setCreateTime(new Date());
        exeMapper.insert(exe);

        // 提交学生程序进行评测，获取评测结果


        return SUCCESS.emptyObject();
    }
}
