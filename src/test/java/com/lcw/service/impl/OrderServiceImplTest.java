package com.lcw.service.impl;

import com.lcw.dataobject.OrderDetail;
import com.lcw.dto.OrderDTO;
import com.lcw.enums.OrderStatusEnum;
import com.lcw.enums.PayStatusEnum;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class OrderServiceImplTest {

    @Autowired
    private OrderServiceImpl orderService;

    private final String BUYEROPENID = "1259754696";
    private final String OPEN_ID = "1571456958862868445";

    @Test
    public void create() {
        OrderDTO orderDTO = new OrderDTO();

        orderDTO.setBuyerAddress("安徽安庆");
        orderDTO.setBuyerName("刘成文");
        orderDTO.setBuyerPhone("17755642300");
        orderDTO.setBuyerOpenid(BUYEROPENID);

        //购物车
        List<OrderDetail> orderDetailList = new ArrayList<>();

        OrderDetail o1 = new OrderDetail();
        o1.setProductId("100001");
        o1.setProductQuantity(2);
        orderDetailList.add(o1);//对象加入

        OrderDetail o2 = new OrderDetail();
        o2.setProductId("100001");
        o2.setProductQuantity(7);
        orderDetailList.add(o2);

        orderDTO.setOrderDetailList(orderDetailList);

        OrderDTO result = orderService.create(orderDTO);
        log.info("【创建订单】 result={}",result);

        Assert.assertNotNull(result);
    }

    @Test
    public void findOne() {
        OrderDTO result = orderService.findOne(OPEN_ID);
        log.info("【查询单个订单】result={}",result);
        Assert.assertEquals(OPEN_ID,result.getOrderId());

    }

    @Test
    public void findList() {
        PageRequest request = new PageRequest(0,2);
        Page<OrderDTO> orderDTOPage = orderService.findList(BUYEROPENID, request);
        Assert.assertNotEquals(0,orderDTOPage.getTotalElements());
    }

    @Test
    public void cancle() {
        OrderDTO orderDTO = orderService.findOne(OPEN_ID);

        OrderDTO result = orderService.cancle(orderDTO);

        Assert.assertEquals(OrderStatusEnum.CANCLE.getCode(),result.getOrderStatus());
    }

    @Test
    public void finish() {
        OrderDTO orderDTO = orderService.findOne(OPEN_ID);

        OrderDTO result = orderService.finish(orderDTO);

        Assert.assertEquals(OrderStatusEnum.FINISHED.getCode(),result.getOrderStatus());
    }

    @Test
    public void paid() {
        OrderDTO orderDTO = orderService.findOne(OPEN_ID);

        OrderDTO result = orderService.paid(orderDTO);

        Assert.assertEquals(PayStatusEnum.SUCCESS.getCode(),result.getPayStatus());
    }
}