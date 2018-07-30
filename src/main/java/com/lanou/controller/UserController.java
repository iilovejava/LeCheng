package com.lanou.controller;

import com.lanou.model.User;
import com.lanou.service.UserService;
import com.lanou.service.impl.UserServiceImpl;
import com.lanou.util.ServiceResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by lanou on 2018/7/28.
 */
@Controller
@CrossOrigin
@RequestMapping(value = "/user")
public class UserController {
    @Autowired
    private UserService userService;

    // 注册
    @ResponseBody
    @RequestMapping(value = "/reg" )
    public ServiceResponse<String> userRegister(String userphone, String userpassword) throws ServletException, IOException {
        // 根据手机查询用户是否存在
        ServiceResponse<String> serviceResponse = userService.findUserByPhone(userphone);
        if (serviceResponse.getErrorcode()!=0){
            System.out.println(userphone);
            System.out.println(userpassword);
            User user = new User();
            user.setUserphone(userphone);
            user.setUserpassword(userpassword);
            ServiceResponse<String> serviceResponse1 = userService.userRegister(user);
            if (serviceResponse1.getErrorcode()== 1){
                return ServiceResponse.createError(1,"注册失败,请重新注册");
            }
            return ServiceResponse.createSuccess("注册成功",user);
        }else {
            return ServiceResponse.createError(1,"用户名已存在,注册失败");
        }

    }

    // 登录
    @ResponseBody
    @RequestMapping(value = "/login")
    public ServiceResponse<String> login(User user, HttpServletResponse response, HttpServletRequest request) throws ServletException, IOException {
        String autologin = request.getParameter("autologin");
        ServiceResponse<String> serviceResponse = userService.selectByPhoneAndPassword(user);


        if (serviceResponse.getErrorcode()==0) {
            // 处理cookie 将账号密码拼接进去
            Cookie cookie = new Cookie("user",user.getUserphone()+"&"+user.getUserpassword());
            cookie.setPath("/");
            // 登录成功
            HttpSession session = request.getSession();
            // 判断是否点了自动登录
            if (autologin != null) {
                // 点了 设置cookie保存时间
                cookie.setMaxAge(Integer.MAX_VALUE);
                session.setAttribute("user",user);
            }else {
                // 没点 删除cookie
                cookie.setMaxAge(0);
            }

            // 将cookie添加到响应中
            response.addCookie(cookie);



            return ServiceResponse.createSuccess("登录成功",user);
        }else {
            // 登录失败
            return ServiceResponse.createError(1,"登录失败");
        }
    }


    // 注销
    @ResponseBody
    @RequestMapping(value = "cancel")
    public ServiceResponse<String> cancel(HttpServletRequest request){
        HttpSession session = request.getSession();
        session.invalidate();
        return ServiceResponse.createSuccess("注销成功");
    }
}
