package com.lanou.service;

import com.lanou.model.Address;

import java.util.List;

public interface AddressService {

    int selectByUserid(Address address);

    int updateAddress(Address address);

    List<Address> linkmans(Integer userid);

    int updateMoren(Integer id);

    int deleteAddress(Integer id);


}
