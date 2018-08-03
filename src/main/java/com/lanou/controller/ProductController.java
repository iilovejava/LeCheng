package com.lanou.controller;

import com.github.pagehelper.PageInfo;
import com.lanou.dao.PictureMapper;
import com.lanou.dao.PriceMapper;
import com.lanou.dao.SaleMapper;
import com.lanou.model.*;
import com.lanou.service.AttrService;
import com.lanou.service.CategoryService;
import com.lanou.service.ProductService;
import com.lanou.service.ValueService;
import com.lanou.util.ServiceResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
@CrossOrigin
@RequestMapping(value = "product")
public class ProductController {

    @Autowired
    private SaleMapper saleMapper;
    @Autowired
    private PriceMapper priceMapper;
    @Autowired
    private PictureMapper pictureMapper;

    @Autowired
    private AttrService attrService;
    @Autowired
    private ValueService valueService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ProductService productService;



    // 根据attrId查询对应的value值 放入attr
    @ResponseBody
    @RequestMapping(value = "value")
    public Attr selectValuesByAttr(Integer id) {
        //  根据id获取对应的attr
        Attr attr = attrService.selectByPrimaryKey(id);
        // 根据attrId获得对应的value属性值
        List<Value> values = valueService.selectValuesByAttrId(attr.getId());
        attr.setValues(values);
        return  attr;
    }

    // 根据分类的id查看该类中的value值
    @ResponseBody
    @RequestMapping(value = "cateAttr")
    public Category selectValuesByCate(Integer id) {
        // 根据id获得分类
        Category category = categoryService.selectByPrimaryKey(id);
        // 根据分类的id获得对应的 该类拥有的attr属性
        List<Attr> list = attrService.selectByCateId(category.getCategoryid());
        category.setAttrs(list);
        for (Attr attr : list) {
            // 获得attr属性中的属性值
            List<Value> valuelist = valueService.selectValuesByAttrId(attr.getId());
            attr.setValues(valuelist);
        }
        return category;
    }

    // 查询单个商品(具体化)
    // 根据proid查询对应商品
    @ResponseBody
    @RequestMapping(value = "product")
    public Product selectProductByProId(Integer id) {
        // 根据proId查询value值 放入product
        Product product = productService.selectByPrimaryKey(id);
        // 获取商品所属分类id
        Integer cateid = product.getCateid();
        // 通过商品所属分类 获取该类中的商品属性和属性值
        Category category = selectValuesByCate(cateid);
        // 将属性和属性值放入商品
        product.setAttrs(category.getAttrs());
        //  获取商品对应的 sale price 和 picture 放入商品
        List<Sale> sales = saleMapper.selectSale(id);
        product.setSales(sales);
        product.setPrices(priceMapper.selectPrice(id));
        product.setPictures(pictureMapper.selectPicture(id));
        return  product;
    }

    // 根据分类查询
    // 查询分类中的所有商品
    @ResponseBody
    @RequestMapping(value = "cate")
    public Category selectProductsByCateId(Integer id) {
        // 商品类
        Category category = categoryService.selectByPrimaryKey(id);
        // 类中包含的商品
        List<Product> products = productService.selectProductsByCateId(id);
        for (Product product : products) {
            // 商品详情化
            selectProductByProId(product.getProductid());
        }
        // 将具体商品放入商品类
        category.setProducts(products);
        return category;
    }


    // 模糊查询
    @ResponseBody
    @RequestMapping(value = "likename")
    public ServiceResponse selectProductsLikeName(String string) {
        Product product = new Product();
        product.setProductname(string);
        List<Product> list = productService.selectProductsLikeName(product);
        // 创建集合接收结果集
        ArrayList<Product> arrayList = new ArrayList<Product>();
        for (Product pro : list) {
            // 将具体商品放入arrayList
            arrayList.add(selectProductByProId(pro.getProductid()));
        }

        if (arrayList.size() == 0) {
            ServiceResponse serviceResponse = ServiceResponse.createError(100,"未找到该商品");
            return serviceResponse;
        }
            PageInfo pageInfo = new PageInfo(arrayList);
            ServiceResponse serviceResponse = ServiceResponse.createSuccess("查询成功",pageInfo);
            return serviceResponse;

    }



}
