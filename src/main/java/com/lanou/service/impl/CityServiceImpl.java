package com.lanou.service.impl;

import com.lanou.dao.CityMapper;
import com.lanou.model.City;
import com.lanou.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by lanou on 2018/8/1.
 */
@Service("cityService")
public class CityServiceImpl implements CityService{
    @Autowired
    private CityMapper cityMapper;

    public List<City> findByCode(String province){

        List<City> list = cityMapper.findByCode(province);
        return list;
    }
}
