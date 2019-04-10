package com.ysu.db.dao;

import com.ysu.db.pojo.Question;
import com.ysu.vo.QuestionInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface QuestionMapper {
    int deleteByPrimaryKey(String questionId);

    int insert(Question record);

    int insertSelective(Question record);

    Question selectByPrimaryKey(String questionId);

    int updateByPrimaryKeySelective(Question record);

    int updateByPrimaryKey(Question record);

    /**
     * 功能描述: 查询题目数量
     *
     * @auther: han jianguo
     * @date: 2019/3/20 16:03
     */
    int countQuestionByName(@Param("name") String name);

    /**
     * 功能描述: 分页查询题目信息
     *
     * @auther: han jianguo
     * @date: 2019/3/20 16:04
     */
    List<Question> selectQuestionByName(Map<String, Object> param);

    /**
     *
     * 功能描述: 查询id下题目所有信息
     *
     * @auther: han jianguo
     * @date: 2019/4/10 16:03
     */
    QuestionInfo getQuestionInfo(String questionId);
}