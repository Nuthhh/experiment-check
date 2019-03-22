package com.ysu.db.dao;

import com.ysu.db.pojo.ExeResult;

public interface ExeResultMapper {
    int deleteByPrimaryKey(String exeId);

    int insert(ExeResult record);

    int insertSelective(ExeResult record);

    ExeResult selectByPrimaryKey(String exeId);

    int updateByPrimaryKeySelective(ExeResult record);

    int updateByPrimaryKey(ExeResult record);
}