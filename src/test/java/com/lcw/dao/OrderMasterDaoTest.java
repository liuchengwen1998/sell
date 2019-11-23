package com.lcw.dao;

import com.lcw.dataobject.OrderMaster;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderMasterDaoTest {

    @Autowired
    private OrderMasterDao orderMasterDao;

    private final String OPENID = "1000001";

    @Test
    public void saveTest(){
        OrderMaster orderMaster = new OrderMaster();
        orderMaster.setOrderId("10002");
        orderMaster.setBuyerName("小张");
        orderMaster.setBuyerPhone("12345678901");
        orderMaster.setBuyerAddress("安徽蚌埠");
        orderMaster.setBuyerOpenid(OPENID);
        orderMaster.setOrderAmount(new BigDecimal(3.9));

        OrderMaster result = orderMasterDao.save(orderMaster);
        Assert.assertNotNull(result);
    }
    
    @Test
    public void findByBuyerOpenid() {
        PageRequest request = new PageRequest(1,3);

        Page<OrderMaster> result =  orderMasterDao.findByBuyerOpenid(OPENID,request);
        Assert.assertNotEquals(0,result.getTotalElements());
        System.out.println(result.getTotalElements());
        System.out.println(result.getTotalPages());
    }
}