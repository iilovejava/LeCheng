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

    // 根据手机号查ID
    public int selectIdByUser(User user) {
        String userphone = user.getUserphone();
        int id = userMapper.selectIdByUser(userphone);
        return id;

    }

    // 根据ID查询
    public User findById(Integer id) {
        User user = userMapper.findById(id);
        return user;
    }

    // 修改信息
    public boolean updateUser(User user) {
        boolean user1 = userMapper.updateUser(user);
        return user1;
    }

    // 修改密码
    public boolean updatePassword(User user) {
        boolean user1 =userMapper.updatePassword(user);
        return user1;
    }
}
