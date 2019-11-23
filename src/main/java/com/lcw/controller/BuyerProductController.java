package com.lcw.controller;

import com.lcw.VO.ProductInfoVO;
import com.lcw.VO.ProductVO;
import com.lcw.VO.ResultVO;
import com.lcw.dataobject.ProductCategory;
import com.lcw.dataobject.ProductInfo;
import com.lcw.service.CategoryService;
import com.lcw.service.ProductService;
import com.lcw.untils.ResultVOUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 买家商品
 */
@RestController
@RequestMapping("/buyer/product")
public class BuyerProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;
    @GetMapping("/list")
    public ResultVO list(){
        //1、查询所有的上架商品
        List<ProductInfo> productInfoList = productService.findUpAll();
        System.out.println(productInfoList);
        //2、查询类目（一次性查询）
//        List<Integer> categoryTypeList = new ArrayList<>();
        //传统方法
//        for(ProductInfo productInfo:productInfoList){
//            categoryTypeList.add(productInfo.getCategoryType());
//        }
        //精简方法(java8,lambda)
        List<Integer> categoryTypeList =  productInfoList.stream().map(e -> e.getCategoryType()).collect(Collectors.toList());
        List<ProductCategory> productCategoryList =  categoryService.findByCategoryTypeIn(categoryTypeList);
        System.out.println(productCategoryList);
        //3、数据拼装
        List<ProductVO> productVOList = new ArrayList<>();
        for(ProductCategory productCategory: productCategoryList){
            ProductVO productVO = new ProductVO();
            productVO.setCategorytype(productCategory.getCategoryType());
            productVO.setCategoryname(productCategory.getCategoryName());
            List<ProductInfoVO> productInfoVOList = new ArrayList<>();
            for (ProductInfo productInfo:productInfoList){
                if(productInfo.getCategoryType().equals(productCategory.getCategoryType())){
                    ProductInfoVO productInfoVO = new ProductInfoVO();
                    BeanUtils.copyProperties(productInfo,productInfoVO);
                    productInfoVOList.add(productInfoVO);
                }
            }

            productVO.setProductInfoVOList(productInfoVOList);
            productVOList.add(productVO);
        }



        return ResultVOUtil.success(productVOList);
    }

}
