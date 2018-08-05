package com.lanou.dao;

import com.lanou.model.XqPicture;

import java.util.List;

public interface XqPictureMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(XqPicture record);

    int insertSelective(XqPicture record);

    XqPicture selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(XqPicture record);

    int updateByPrimaryKey(XqPicture record);

    List<XqPicture> selectXqPic(Integer proId);
}