package com.lanou.service;

import com.lanou.model.Product;

import java.util.List;

public interface ProductService {

    Product selectByPrimaryKey(Integer productid);

    List<Product> selectProductsByCateId(Integer id);

    List<Product> selectProductsLikeName(Product pro);
}
