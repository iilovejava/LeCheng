package com.lanou.dao;

import com.lanou.model.Value;

import java.util.List;

public interface ValueMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Value record);

    int insertSelective(Value record);

    Value selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Value record);

    int updateByPrimaryKey(Value record);

    List<Value> selectValuesByAttrId(Integer id);

    List<Value> selectValuesByProId(Value value);
}