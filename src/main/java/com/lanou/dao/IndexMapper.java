package com.lanou.dao;

import com.lanou.model.Index;

public interface IndexMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Index record);

    int insertSelective(Index record);

    Index selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Index record);

    int updateByPrimaryKey(Index record);
}