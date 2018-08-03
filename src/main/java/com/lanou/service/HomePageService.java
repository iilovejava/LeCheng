package com.lanou.service;

import com.lanou.model.HomePage;

import java.util.List;

public interface HomePageService {

    List<HomePage> findByParentId(Integer parentId);
}
