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
    public List<HomePage> findByParentId(Integer id) {
        List<HomePage> list = indexService.findByParentId(id);
        return list;
    }

}
