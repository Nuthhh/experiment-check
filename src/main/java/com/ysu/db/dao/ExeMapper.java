package com.ysu.db.dao;

import com.ysu.db.pojo.Exe;

public interface ExeMapper {
    int deleteByPrimaryKey(String exeId);

    int insert(Exe record);

    int insertSelective(Exe record);

    Exe selectByPrimaryKey(String exeId);

    int updateByPrimaryKeySelective(Exe record);

    int updateByPrimaryKeyWithBLOBs(Exe record);

    int updateByPrimaryKey(Exe record);
}