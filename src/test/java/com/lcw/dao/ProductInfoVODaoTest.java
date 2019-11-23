package com.lcw.dao;

import com.lcw.dataobject.ProductInfo;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductInfoVODaoTest {
    @Autowired
    ProductInfoDao productInfoDao;

    @Test
    public void saveTest(){
        ProductInfo productInfo = new ProductInfo();
        productInfo.setProductId("100001");
        productInfo.setProductName("农夫山泉");
        productInfo.setProductPrice(new BigDecimal(1.8));
        productInfo.setProductDescription("山泉水");
        productInfo.setProductStock(1000);
        productInfo.setProductIcon("http://xxxx.jpg");
        productInfo.setProductStatus(0);
        productInfo.setCategoryType(2);

        ProductInfo result = productInfoDao.save(productInfo);
        Assert.assertNotNull(result);

    }

    @Test
    public void findByProductStatus() throws Exception{
        List<ProductInfo> productInfoList = productInfoDao.findByProductStatus(0);
        Assert.assertNotEquals(0,productInfoList.size());

    }
}
