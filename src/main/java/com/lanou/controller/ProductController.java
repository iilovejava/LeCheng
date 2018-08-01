package com.lanou.controller;

import com.lanou.dao.PictureMapper;
import com.lanou.dao.PriceMapper;
import com.lanou.dao.SaleMapper;
import com.lanou.model.*;
import com.lanou.service.AttrService;
import com.lanou.service.CategoryService;
import com.lanou.service.ProductService;
import com.lanou.service.ValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
    public Attr selectValues(Integer id) {
        //  根据id获取对应的attr
        Attr attr = attrService.selectByPrimaryKey(id);
        // 根据attrId获得对应的value属性值
        List<Value> values = valueService.selectValuesByAttrId(attr.getId());
        attr.setValues(values);
        return  attr;
    }

    // 根据分类查询
    // 查询分类中的所有商品
    @ResponseBody
    @RequestMapping(value = "cate")
    public Category selectProductsByCateId(Integer id) {
        Category category = categoryService.selectByPrimaryKey(id);
        List<Product> products = productService.selectProductsByCateId(id);

        for (Product product : products) {
            selectValuesByProId(product.getProductid());
        }
        category.setProducts(products);
        return category;
    }

    // 查询单个商品
    // 根据id查询对应商品
    @ResponseBody
    @RequestMapping(value = "product")
    public Product selectValuesByProId(Integer id) {
        // 根据proId查询value值 放入product
        Product product = productService.selectByPrimaryKey(id);
        Integer proid = product.getCateid();
        // 通过商品对应分类获取分类中的属性和属性值
        Category category = selectByCateId(proid);
        product.setAttrs(category.getAttrs());
        List<Sale> sales = saleMapper.selectSale(proid);
        product.setSales(sales);
        product.setPrices(priceMapper.selectPrice(proid));
        product.setPictures(pictureMapper.selectPicture(proid));
        return  product;
    }

    // 查看所有attr中的所有属性
//    @ResponseBody
//    @RequestMapping(value = "allValue")
//    public List<Attr> selectAllAttr() {
//        // 所有的attr
//        List<Attr> list = attrService.selectAllAttr();
//        for (Attr attr : list) {
//            // 每个attr对应的value
//            List<Value> valuelist = valueService.selectValuesByAttrId(attr.getId());
//            attr.setValues(valuelist);
//        }
//        return list;
//    }

    // 通过类的id查看该类中attr对应的value值
    @ResponseBody
    @RequestMapping(value = "cateAttr")
    public Category selectByCateId(Integer id) {
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

}
