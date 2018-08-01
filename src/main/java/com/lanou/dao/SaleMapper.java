package com.lanou.dao;

import com.lanou.model.Sale;

import java.util.List;

public interface SaleMapper {
    int deleteByPrimaryKey(Integer saleid);

    int insert(Sale record);

    int insertSelective(Sale record);

    Sale selectByPrimaryKey(Integer saleid);

    int updateByPrimaryKeySelective(Sale record);

    int updateByPrimaryKey(Sale record);

    List<Sale> selectSale(Integer id);
}