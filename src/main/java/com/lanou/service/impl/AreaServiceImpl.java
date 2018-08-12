package com.lanou.service.impl;

import com.lanou.dao.AreaMapper;
import com.lanou.model.Area;
import com.lanou.service.AreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by lanou on 2018/8/1.
 */
@Service("areaService")
public class AreaServiceImpl implements AreaService{
    @Autowired
    private AreaMapper areaMapper;

    public List<Area> findByArea(String city){

        List<Area> list = areaMapper.findByArea(city);
        return list;
    }

    public String findBycode(String code) {
        Area area = areaMapper.findBycode(code);
        return area.getName();
    }

}
