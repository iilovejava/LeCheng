package com.lanou.service;

import com.lanou.model.Value;

import java.util.List;

public interface ValueService {

    List<Value> selectValuesByAttrId(Integer id);

    List<Value> selectValuesByProId(Integer id);
}
