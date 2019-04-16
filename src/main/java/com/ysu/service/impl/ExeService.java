package com.ysu.service.impl;

import com.ysu.common.constants.Constants;
import com.ysu.common.constants.ReturnObject;
import com.ysu.common.utils.*;
import com.ysu.db.dao.ExeMapper;
import com.ysu.db.dao.ExeResultMapper;
import com.ysu.db.pojo.Exe;
import com.ysu.db.pojo.ExeResult;
import com.ysu.service.IExeService;
import com.ysu.vo.ExeInfo;
import com.ysu.vo.ExeTeacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        String exeId = GUID.getGUID();
        exe.setExeId(exeId);
        exe.setCreateTime(new Date());
        exeMapper.insert(exe);

        // 提交学生程序进行评测，获取评测结果
        // exeId、程序路径、题目id、lang
        PythonUtil.runPython(Constants.PYTHON_COMMAND, Constants.PYTHON_SCRIPTPATH_JUDGE, exeId, fileName.toString(), exe.getQuestionId(), String.valueOf(exe.getLang()));
        ExeResult exeResult = exeResultMapper.selectByPrimaryKey(exeId);

        return SUCCESS.toObject(exeResult);
    }

    @Override
    public ReturnObject answer(String questionId, Integer stuId) {
        Map<String, Object> param = new HashMap<>();
        param.put("questionId", questionId);
        param.put("stuId", stuId);
        List<ExeInfo> list = exeMapper.selectAnswerByQuestionIdAndStuId(param);
        return SUCCESS.toObject(list);
    }

    @Override
    public ReturnObject answerOne(String exeId) {
        ExeInfo exeInfo = exeMapper.getExeInfo(exeId);
        return SUCCESS.toObject(exeInfo);
    }

    @Override
    public ReturnObject exeResultList(String questionId, Pager pager) {

        Map<String, Object> param = PagerSearchMapBuilder.on(pager)
                .append("questionId", questionId).toSearchMap();

        int count = exeMapper.countStudentAnswerByQuestionId(questionId);
        List<ExeTeacher> list = null;
        if (count > 0) {
            list = exeMapper.selectStudentAnswerByQuestionId(param);
        }
        return SUCCESS.toObject(pager.toShowPageBean(count, list));
    }
}
