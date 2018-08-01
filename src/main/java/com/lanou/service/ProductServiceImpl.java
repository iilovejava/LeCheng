package com.lanou.service;


import com.lanou.dao.ProductMapper;
import com.lanou.model.Product;
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
}
