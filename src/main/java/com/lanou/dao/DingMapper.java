package com.lanou.dao;

import com.lanou.model.Ding;

import java.util.List;

public interface DingMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Ding record);

    int insertSelective(Ding record);

    Ding selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Ding record);

    int updateByPrimaryKey(Ding record);

    List<Ding> findByUserid(Integer userid);
}