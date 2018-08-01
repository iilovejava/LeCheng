package com.lanou.service;

import com.lanou.model.Attr;

import java.util.List;

public interface AttrService {

    List<Attr> selectAllAttr();

    Attr selectByPrimaryKey(Integer id);

    List<Attr> selectByCateId(Integer id);
}
