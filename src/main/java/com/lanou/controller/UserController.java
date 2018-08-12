package com.lanou.controller;

import com.lanou.dao.PictureMapper;
import com.lanou.dao.PriceMapper;
import com.lanou.model.*;
import com.lanou.service.*;
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
import java.util.List;

import static org.apache.ibatis.ognl.DynamicSubscript.all;

/**
 * Created by lanou on 2018/7/28.
 */
@Controller
@CrossOrigin
@RequestMapping(value = "/user")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private CommentService commentService;
    @Autowired
    private CollectService collectService;
    @Autowired
    private ProductService productService;
    @Autowired
    private PictureMapper pictureMapper;
    @Autowired
    private PriceMapper priceMapper;


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

//            if (!code.equals(s)) {
//                return ServiceResponse.createError(1, "注册失败,验证码错误");
//            }
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
        System.out.println(s);
        System.out.println(code);
        if (serviceResponse.getErrorcode() == 0) {
            // 判断验证码是否正确
            //if (code.equals(s)) {
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
                int i = userService.selectIdByUser(user);
                user.setUserid(i);

            session.setAttribute("user", user);


                return ServiceResponse.createSuccess("登录成功", user);
//            } else {
//                // 登录失败
//                return ServiceResponse.createError(1, "验证码错误,登录失败");
//            }

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




    // 更新个人信息
    @ResponseBody
    @RequestMapping(value = "message")
    public ServiceResponse<String> message(User user){
        boolean res = userService.updateUser(user);
        if (res == true){
            return ServiceResponse.createSuccess("修改成功");
        }
        return ServiceResponse.createError(1,"用户信息有误");
    }

    // 编辑个人信息
    @ResponseBody
    @RequestMapping(value = "usermsg")
    public ServiceResponse usermsg(Integer userid) {
        User user = userService.findById(userid);
        return ServiceResponse.createSuccess("用户编辑",user);
    }

    // 修改密码
    @ResponseBody
    @RequestMapping(value = "updatePassword")
    public ServiceResponse<String> updatePassword(Integer userid,String newpass, String newword){
        System.out.println("****************************************");
        System.out.println(newpass);
        System.out.println(newword);
        if (newword.equals(newpass)) {
            boolean res = userService.updatePassword(userid,newpass);
            if (res) {
                return ServiceResponse.createSuccess("密码修改成功");
            }
            return ServiceResponse.createError(1,"密码修改失败");
        }

        return ServiceResponse.createError(1,"两次输入密码不一致,修改失败");

    }


    // 查询商品的评论
    @ResponseBody
    @RequestMapping(value = "comment")
    public List<Comment> findComment(Integer productId) {
        List<Comment> all = commentService.findComment(productId);
        for (Comment comment : all) {
            Integer userid = comment.getUserid();
            User u = userService.findById(userid);
            comment.setUser(u);
        }
        return all;
    }

    // 添加商品的评论
    @ResponseBody
    @RequestMapping(value = "addcomment")
    public ServiceResponse<String> addComment(Comment comment) {
        boolean all = commentService.addComment(comment);
        if (all){
            return ServiceResponse.createSuccess("评论成功");
        }
        return ServiceResponse.createError(1,"评论失败");
    }

    // 判断该用户是否收藏该商品
    @ResponseBody
    @RequestMapping(value = "iscollect")
    public Boolean isCollect(Collect collect) {
        List<Collect> list = collectService.findCollect(collect.getUserid());
        for (Collect col : list) {
            if (col.getProductid() == collect.getProductid()){
                return true;
            }
        }
        return false;
    }

    // 查看收藏
    @ResponseBody
    @RequestMapping(value = "findcollect")
    public List<Collect> findCollect(Integer userId) {
        List<Collect> collects = collectService.findCollect(userId);
        for (Collect collect : collects) {
            Integer productid = collect.getProductid();
            Product product = productService.selectByPrimaryKey(productid);
            List<Picture> list = pictureMapper.selectPicture(productid);
            // 图片
            product.setProducturl(list.get(0).getPicurl());
            // 价格
            List<Price> prices = priceMapper.selectPrice(productid);
            product.setPrice(prices.get(0));
            collect.setProduct(product);
        }
        return collects;
    }

    // 添加用户收藏
    @ResponseBody
    @RequestMapping(value = "addcollect")
    public ServiceResponse<String> addCollect(Collect collect) {
        int all = collectService.addCollect(collect);
        if (all == 1){
            return ServiceResponse.createSuccess("收藏成功");
        }
        return ServiceResponse.createError(1,"收藏失败");
    }

    // 删除用户收藏
    @ResponseBody
    @RequestMapping(value = "deletecollect")
    public ServiceResponse<String> deleteCollect(Collect collect) {
        boolean all = collectService.deleteCollect(collect);
        if (all){
            return ServiceResponse.createSuccess("删除成功");
        }
        return ServiceResponse.createError(1,"删除失败");
    }

}
