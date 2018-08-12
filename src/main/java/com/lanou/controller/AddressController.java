package com.lanou.controller;

import com.lanou.dao.AddressMapper;
import com.lanou.model.Address;
import com.lanou.model.Area;
import com.lanou.model.City;
import com.lanou.model.Province;
import com.lanou.service.AddressService;
import com.lanou.service.AreaService;
import com.lanou.service.CityService;
import com.lanou.service.ProvinceService;
import com.lanou.util.ServiceResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@CrossOrigin
@RequestMapping(value = "address")
public class AddressController {

    @Autowired
    private ProvinceService provinceService;
    @Autowired
    private CityService cityService;
    @Autowired
    private AreaService areaService;
    @Autowired
    private AddressService addressService;


    // 选择省
    @ResponseBody
    @RequestMapping(value = "province")
    public ServiceResponse province() {
        List<Province> provinces = provinceService.findAll();
        return ServiceResponse.createSuccess("选择省份",provinces);
    }

    // 选择市
    @ResponseBody
    @RequestMapping(value = "city")
    public ServiceResponse city(String procode) {
        List<City> citys = cityService.findByCode(procode);
        return ServiceResponse.createSuccess("选择所在市",citys);
    }

    // 选择县
    @ResponseBody
    @RequestMapping(value = "area")
    public ServiceResponse findByArea(String citycode) {
        List<Area> areas = areaService.findByArea(citycode);
        return ServiceResponse.createSuccess("选择所在县/区",areas);
    }

    // 添加地址
    @ResponseBody
    @RequestMapping(value = "address")
    public ServiceResponse addAddress(Address address) {
        int res = addressService.selectByUserid(address);
        if (res != 1) {
            return ServiceResponse.createError(1,"添加失败");
        }
        return ServiceResponse.createSuccess("添加成功");

    }

    // 修改地址
    @ResponseBody
    @RequestMapping(value = "update")
    public ServiceResponse updateAddress(Address address) {
        int res = addressService.updateAddress(address);
        if (res != 1) {
            return ServiceResponse.createError(1,"修改失败");
        }
        return ServiceResponse.createSuccess("修改成功",address);
    }

    // 选择默认地址
    @ResponseBody
    @RequestMapping(value = "moren")
    public ServiceResponse morenAddress(Integer id) {
        int res = addressService.updateMoren(id);
        if (res != 1) {
            return ServiceResponse.createError(1,"设置失败");
        }
        return ServiceResponse.createSuccess("设置成功");
    }

    // 收货人列表
    @ResponseBody
    @RequestMapping(value = "linkman")
    public ServiceResponse linkmans(Integer userid) {
        List<Address> addresses = addressService.linkmans(userid);
        return ServiceResponse.createSuccess("收货人地址列表",addresses);
    }

    // 删除地址
    @ResponseBody
    @RequestMapping(value = "delete")
    public ServiceResponse deleteAddress(Integer id){
        int res = addressService.deleteAddress(id);
        if (res != 1) {
            return ServiceResponse.createError(1,"删除失败");
        }
        return ServiceResponse.createSuccess("删除成功");
    }
}
