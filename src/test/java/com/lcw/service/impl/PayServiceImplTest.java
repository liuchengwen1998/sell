package com.lcw.service.impl;

import com.lcw.dto.OrderDTO;
import com.lcw.service.OrderService;
import com.lcw.service.PayService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class PayServiceImplTest {

    @Autowired
    private PayService payService;

    @Autowired
    private OrderService orderService;
    @Test
    public void create() {
        OrderDTO orderDTO = orderService.findOne("1571714496823915414");
        payService.create(orderDTO);
    }
}