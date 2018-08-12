package com.lanou.service.impl;

import com.lanou.dao.ProvinceMapper;
import com.lanou.model.Province;
import com.lanou.service.ProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by lanou on 2018/8/1.
 */
@Service("provinceService")
public class ProvinceServiceImpl implements ProvinceService {

    @Autowired
    private ProvinceMapper provinceMapper;

    // 查询全部省
    // 查询用户是否唯一
    public List<Province> findAll(){
        List<Province> list = provinceMapper.findAll();
        return list;
    }

    public String findProvince(String code) {
        Province province = provinceMapper.findProvince(code);
        return province.getName();
    }

}
