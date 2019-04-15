package com.ysu.db.dao;

import com.ysu.db.pojo.Exe;
import com.ysu.vo.ExeInfo;
import com.ysu.vo.ExeTeacher;

import java.util.List;
import java.util.Map;

public interface ExeMapper {
    int deleteByPrimaryKey(String exeId);

    int insert(Exe record);

    int insertSelective(Exe record);

    Exe selectByPrimaryKey(String exeId);

    int updateByPrimaryKeySelective(Exe record);

    int updateByPrimaryKeyWithBLOBs(Exe record);

    int updateByPrimaryKey(Exe record);

    /**
     * 功能描述: 多少学生作答某道题目
     *
     * @auther: han jianguo
     * @date: 2019/4/15 15:24
     */
    int countStudentAnswerByQuestionId(String questionId);

    /**
     * 功能描述: 教师查看某道题学生作答记录
     *
     * @auther: han jianguo
     * @date: 2019/4/15 15:20
     */
    List<ExeTeacher> selectStudentAnswerByQuestionId(Map<String, Object> param);

    /**
     *
     * 功能描述: 学生查看某道题自己作答情况
     *
     * @auther: han jianguo
     * @date: 2019/4/15 15:42
     */
    List<ExeInfo> selectAnswerByQuestionIdAndStuId(Map<String,Object> param);
}