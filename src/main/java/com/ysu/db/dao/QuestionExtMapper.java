package com.ysu.db.dao;

import com.ysu.db.pojo.QuestionExt;

public interface QuestionExtMapper {
    int deleteByPrimaryKey(String questionId);

    int insert(QuestionExt record);

    int insertSelective(QuestionExt record);

    QuestionExt selectByPrimaryKey(String questionId);

    int updateByPrimaryKeySelective(QuestionExt record);

    int updateByPrimaryKey(QuestionExt record);
}