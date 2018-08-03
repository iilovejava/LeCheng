package com.lanou.dao;

import com.lanou.model.HomePage;

import java.util.List;

public interface HomePageMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(HomePage record);

    int insertSelective(HomePage record);

    HomePage selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(HomePage record);

    int updateByPrimaryKey(HomePage record);

    List<HomePage> findByParentId(Integer parentId);
}