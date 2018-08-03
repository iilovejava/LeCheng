package com.lanou.service.impl;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lanou.dao.ProductMapper;
import com.lanou.model.Product;
import com.lanou.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("productService")
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductMapper productMapper;

    public Product selectByPrimaryKey(Integer productid) {
        return productMapper.selectByPrimaryKey(productid);
    }

    public List<Product> selectProductsByCateId(Integer id) {
        return productMapper.selectProductsByCateId(id);
    }

    public List<Product> selectProductsLikeName(Product pro) {
//        PageHelper.startPage(1,10);
//        PageInfo info = new PageInfo(productMapper.selectProductsLikeName(pro));
        List<Product> list = productMapper.selectProductsLikeName(pro);
        return list;
    }
}
