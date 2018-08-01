package com.lanou.service.impl;

import com.lanou.dao.CategoryMapper;
import com.lanou.model.Category;
import com.lanou.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("categoryService")
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    public Category selectByPrimaryKey(Integer id) {
        Category category = categoryMapper.selectByPrimaryKey(id);
        return category;
    }
}
