package com.lanou.dao;

import com.lanou.model.Picture;

import java.util.List;

public interface PictureMapper {
    int deleteByPrimaryKey(Integer picid);

    int insert(Picture record);

    int insertSelective(Picture record);

    Picture selectByPrimaryKey(Integer picid);

    int updateByPrimaryKeySelective(Picture record);

    int updateByPrimaryKey(Picture record);

    List<Picture> selectPicture(Integer id);
}