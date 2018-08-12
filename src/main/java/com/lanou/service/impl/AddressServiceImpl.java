package com.lanou.service.impl;

import com.lanou.dao.AddressMapper;
import com.lanou.model.Address;
import com.lanou.service.AddressService;
import com.lanou.service.AreaService;
import com.lanou.service.CityService;
import com.lanou.service.ProvinceService;
import com.lanou.util.ServiceResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("addressService")
public class AddressServiceImpl  implements AddressService {
    @Autowired
    private AddressMapper addressMapper;
    @Autowired
    private ProvinceService provinceService;
    @Autowired
    private CityService cityService;
    @Autowired
    private AreaService areaService;

    // 新增收货地址
    public int selectByUserid(Address address) {
        // 判断新增地址是不是已设为默认地址
        if (address.getMoren().equals("true")){
            List<Address> addresses = addressMapper.selectByUserid(address.getUserid());
            for (Address ress : addresses) {
                ress.setMoren("false");
                addressMapper.updateByPrimaryKeySelective(ress);
            }
        }
        int res = addressMapper.insertSelective(address);
        return res;
    }

    // 修改收货地址
    public int updateAddress(Address address) {
        // 如果修改后的收货地址被设为默认
        if (address.getMoren().equals("true")) {
           int res = updateMoren(address.getId());
           return res;
        }
        int res = addressMapper.updateByPrimaryKeySelective(address);
        return res;
    }

    // 选择默认地址
    public int updateMoren(Integer id) {
        Address address = addressMapper.selectByPrimaryKey(id);
        List<Address> list = addressMapper.selectByUserid(address.getUserid());
        for (Address address1 : list) {
            address1.setMoren("false");
            addressMapper.updateIsTrue(address1);
        }
        address.setMoren("true");
        int res = addressMapper.updateByPrimaryKeySelective(address);
        return res;
    }

    // 删除地址
    public int deleteAddress(Integer id) {
        int res = addressMapper.deleteByPrimaryKey(id);
        return res;
    }

    // 查看用户的所有收货地址
    public List<Address> linkmans(Integer userid) {
        List<Address> addresses = addressMapper.selectByUserid(userid);
        for (Address address : addresses) {
            String pro = provinceService.findProvince(address.getProvinceid());
            String city = cityService.findCityByCode(address.getCityid());
            String area = areaService.findBycode(address.getAreaid());
            address.setSsq(pro + " " + city + " " + area);
        }
        return addresses;
    }

}
