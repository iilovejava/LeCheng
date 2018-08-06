package com.lanou.dao;

import com.lanou.model.Collect;

import java.util.List;

public interface CollectMapper {
    int deleteByPrimaryKey(Integer collectid);

    int insert(Collect record);

    int insertSelective(Collect record);

    Collect selectByPrimaryKey(Integer collectid);

    int updateByPrimaryKeySelective(Collect record);

    int updateByPrimaryKey(Collect record);

    List<Collect> findCollect(Integer userId);

    boolean addComment(Collect collect);

    boolean deleteCollect(Collect collect);
}