package com.lanou.interceptor;

import com.lanou.model.User;
import com.lanou.service.UserService;
import com.lanou.service.impl.UserServiceImpl;
import com.lanou.util.ServiceResponse;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginInterceptor implements HandlerInterceptor {
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {

        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("user");
        if (user==null){
            Cookie[] cookies = request.getCookies();
            String userphone = "";
            String userpassword = "";

            for (Cookie cookie : cookies) {
                //判断
                if (cookie.getName().equals("user")) {
                    //取出对应的值  wanglong&123
                    String value = cookie.getValue();
                    String[] values = value.split("&");
                    userphone = values[0];
                    userpassword = values[1];
                }
            }

            //自动登录
            UserService userService = new UserServiceImpl();
            User user1 = new User();
            user1.setUserphone(userphone);
            user1.setUserpassword(userpassword);
            //调用登录方法
            ServiceResponse<String> serviceResponse = userService.selectByPhoneAndPassword(user1);

            // 如果返回状态码为0
            if (serviceResponse.getErrorcode()==0){
                request.getSession().setAttribute("user",user1);
            }
            return false;

        }

        return true;
    }

    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
