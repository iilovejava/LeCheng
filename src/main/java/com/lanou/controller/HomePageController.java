package com.lanou.controller;

import com.github.pagehelper.PageInfo;
import com.lanou.model.HomePage;
import com.lanou.service.HomePageService;
import com.lanou.util.ServiceResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@CrossOrigin
@RequestMapping(value = "home")
public class HomePageController {
    @Autowired
    private HomePageService indexService;

    @ResponseBody
    @RequestMapping(value = "homeCate")
    public ServiceResponse findByParentId(Integer id) {
        List<HomePage> list = indexService.findByParentId(id);
        if (list.size() == 0) {
            ServiceResponse serviceResponse = ServiceResponse.createError(200,"首页没有该模块");
            return  serviceResponse;
        } else {
            PageInfo pageInfo = new PageInfo(list);
            ServiceResponse serviceResponse = ServiceResponse.createSuccess("成功",pageInfo);
            return  serviceResponse;
        }

    }

}
