package com.lanou.service.impl;

import com.lanou.dao.UserMapper;
import com.lanou.model.User;
import com.lanou.service.UserService;
import com.lanou.util.ServiceResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by lanou on 2018/7/28.
 */
@Service("userService")
public class UserServiceImpl implements UserService{




    @Autowired
    private UserMapper userMapper;


    // 用户注册
    public ServiceResponse<String>  userRegister(User user){
        System.out.println(user.getUserphone());
        int i = userMapper.userRegister(user);
        if (i==1){
            return ServiceResponse.createSuccess("注册成功",user);
        }
        return ServiceResponse.createError(1,"注册失败");
    }

    // 查询用户是否唯一
    public ServiceResponse<String> findUserByPhone(String userPhone){
        User user = userMapper.findUserByPhone(userPhone);
        if (user==null){
            return ServiceResponse.createError(1,"查询失败");
        }

        return ServiceResponse.createSuccess("查询成功",user);
    }
    // 登录
    public ServiceResponse<String> selectByPhoneAndPassword(User user) {
        System.out.println(userMapper);
        User user1  = userMapper.selectByPhoneAndPassword(user);
        if (user1==null){
            return ServiceResponse.createError(1,"登录失败");
        }

        return ServiceResponse.createSuccess("登录成功",user1);
    }

}
