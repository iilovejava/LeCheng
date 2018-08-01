package com.lanou.dao;

import com.lanou.model.Attr;

import java.util.List;

public interface AttrMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Attr record);

    int insertSelective(Attr record);

    Attr selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Attr record);

    int updateByPrimaryKey(Attr record);

    List<Attr> selectAllAttr();

    List<Attr> selectByCateId(Integer id);
}