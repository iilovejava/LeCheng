package com.lanou.dao;

import com.lanou.model.Price;

import java.util.List;

public interface PriceMapper {
    int deleteByPrimaryKey(Integer priceid);

    int insert(Price record);

    int insertSelective(Price record);

    Price selectByPrimaryKey(Integer priceid);

    int updateByPrimaryKeySelective(Price record);

    int updateByPrimaryKey(Price record);

    List<Price> selectPrice(Integer id);
}