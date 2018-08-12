package com.lanou.dao;

import com.lanou.model.User;

public interface UserMapper {
    int deleteByPrimaryKey(Integer userid);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer userid);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    // 用户注册 
    int userRegister(User user);
    // 查询手机号是否存在 
    User findUserByPhone(String userPhone);
    // 登录
    User selectByPhoneAndPassword(User user);
    // 查询id
    int selectIdByUser(String userphone);
    // 根据id查询
    User findById(Integer id);
    // 修改信息
    int updateUser(User user);
    // 修改密码
    boolean updatePassword(User user);
}