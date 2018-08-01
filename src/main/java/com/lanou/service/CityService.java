package com.lanou.service;

import com.lanou.model.City;

import java.util.List;

/**
 * Created by lanou on 2018/8/1.
 */
public interface CityService {

    List<City> findByCode(String province);
}
