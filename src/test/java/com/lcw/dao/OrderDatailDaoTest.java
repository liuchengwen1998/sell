package com.lcw.dao;

import com.lcw.dataobject.OrderDetail;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderDatailDaoTest {

    @Autowired
    private OrderDatailDao orderDatailDao;

    @Test
    public void save(){
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setDetailId("100010");
        orderDetail.setOrderId("111112");
        orderDetail.setProductId("100010");
        orderDetail.setProductIcon("http://xxxx.jps");
        orderDetail.setProductName("可乐");
        orderDetail.setProductPrice(new BigDecimal(15));
        orderDetail.setProductQuantity(5);

        OrderDetail result = orderDatailDao.save(orderDetail);

        Assert.assertNotNull(result);

    }

    @Test
    public void findByOrderId() {
        List<OrderDetail> orderDetailList = orderDatailDao.findByOrderId("111112");

        Assert.assertNotEquals(0,orderDetailList.size());
        System.out.println(orderDetailList);


    }
}