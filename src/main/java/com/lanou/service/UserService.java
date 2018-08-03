package com.lanou.service;

import com.lanou.model.User;
import com.lanou.util.ServiceResponse;
import sun.jvm.hotspot.oops.Oop;

/**
 * Created by lanou on 2018/7/28.
 */
public interface UserService {
    // 用户注册
    ServiceResponse<String> userRegister(User user);

    // 查询用户是否唯一
    ServiceResponse<String> findUserByPhone(String userPhone);
    // 用户登录
    ServiceResponse<String> selectByPhoneAndPassword(User user);
    // 查询id
    int selectIdByUser(User user);
    // 根据id查询
    User findById(Integer id);
    // 修改信息
    boolean updateUser(User user);
    // 修改密码
    boolean updatePassword(User user);
}
