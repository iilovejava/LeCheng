package com.lanou.service;

import com.lanou.model.Area;

import java.util.List;

/**
 * Created by lanou on 2018/8/1.
 */
public interface AreaService {
    List<Area> findByArea(String city);
}
