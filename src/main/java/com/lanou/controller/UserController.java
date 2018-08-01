package com.lanou.controller;

import com.lanou.model.User;
import com.lanou.service.UserService;
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
    @RequestMapping(value = "/reg")
    public ServiceResponse<String> userRegister(User user, HttpServletRequest request) throws ServletException, IOException {
        // 获取输入的验证码
        String code = request.getParameter("code");
        // 获取生成的验证码
        HttpSession session = request.getSession();
        String s = (String) session.getAttribute("mycode");
        // 根据手机查询用户是否存在
        ServiceResponse<String> serviceResponse = userService.findUserByPhone(user.getUserphone());
        if (serviceResponse.getErrorcode() != 0) {

            if (!code.equals(s)) {
                return ServiceResponse.createError(1, "注册失败,验证码错误");
            }
            ServiceResponse<String> serviceResponse1 = userService.userRegister(user);
            return ServiceResponse.createSuccess("注册成功", user);
        } else {
            return ServiceResponse.createError(1, "用户名已存在,注册失败");
        }

    }

    // 登录
    @ResponseBody
    @RequestMapping(value = "/login")
    public ServiceResponse<String> login(User user, HttpServletResponse response, HttpServletRequest request) throws ServletException, IOException {
        String autologin = request.getParameter("autologin");
        ServiceResponse<String> serviceResponse = userService.selectByPhoneAndPassword(user);
        // 获取输入的验证码
        String code = request.getParameter("code");
        // 获取生成的验证码
        HttpSession session = request.getSession();
        String s = (String) session.getAttribute("mycode");

        if (serviceResponse.getErrorcode() == 0) {
            // 判断验证码是否正确
            if (code.equals(s)) {
                // 处理cookie 将账号密码拼接进去
                Cookie cookie = new Cookie("user", user.getUserphone() + "&" + user.getUserpassword());
                cookie.setPath("/");
                // 登录成功
                // 判断是否点了自动登录
                if (autologin != null) {
                    // 点了 设置cookie保存时间
                    cookie.setMaxAge(Integer.MAX_VALUE);

                } else {
                    // 没点 删除cookie
                    cookie.setMaxAge(0);
                }
                // 将cookie添加到响应中
                response.addCookie(cookie);
                session.setAttribute("user", user);

                return ServiceResponse.createSuccess("登录成功", user);
            } else {
                // 登录失败
                return ServiceResponse.createError(1, "验证码错误,登录失败");
            }

        } else {
            // 登录失败
            return ServiceResponse.createError(1, "登录失败");
        }
    }


    // 注销
    @ResponseBody
    @RequestMapping(value = "cancel")
    public ServiceResponse<String> cancel(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.invalidate();
        return ServiceResponse.createSuccess("汪汪汪");
    }

    // 注销
    @ResponseBody
    @RequestMapping(value = "test")
    public ServiceResponse<String> test(String name, HttpServletRequest request) {
        System.out.println("共和国嘎嘎嘎");
        System.out.println(name);
        return ServiceResponse.createSuccess("汪汪汪");
    }

}
