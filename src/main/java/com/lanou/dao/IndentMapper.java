package com.lanou.dao;

import com.lanou.model.Indent;

public interface IndentMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Indent record);

    int insertSelective(Indent record);

    Indent selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Indent record);

    int updateByPrimaryKey(Indent record);

    int addIndent(Indent indent);


}