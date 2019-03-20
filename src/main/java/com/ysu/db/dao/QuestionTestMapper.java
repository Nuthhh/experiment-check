package com.ysu.db.dao;

import com.ysu.db.pojo.QuestionTest;

import java.util.List;
import java.util.Map;

public interface QuestionTestMapper {
    int deleteByPrimaryKey(String pkid);

    int insert(QuestionTest record);

    int insertSelective(QuestionTest record);

    QuestionTest selectByPrimaryKey(String pkid);

    int updateByPrimaryKeySelective(QuestionTest record);

    int updateByPrimaryKey(QuestionTest record);

    /**
     *
     * 功能描述: 按题目id删除单元测试用例
     *
     * @auther: han jianguo
     * @date: 2019/3/20 15:30
     */
    int deleteByQuestionId(String questionId);

    /**
     *
     * 功能描述: 获得某题目下总共的测试用例数
     *
     * @auther: han jianguo
     * @date: 2019/3/20 17:22
     */
    int countTestByQuestionId(String questionId);

    /**
     *
     * 功能描述: 获取某题目下测试用例
     *
     * @auther: han jianguo
     * @date: 2019/3/20 17:24
     */
    List<QuestionTest> getTestByQuestionId(Map<String,Object> param);

}