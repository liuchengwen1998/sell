package com.lcw.controller;


import com.lcw.dto.OrderDTO;
import com.lcw.enums.ResultEnum;
import com.lcw.exception.SellException;
import com.lcw.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/pay")
public class PayController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/create")
    public void create(@RequestParam("openId") String openId,
                       @RequestParam("returnUrl") String returenUrl){
        //1、查询订单
        OrderDTO orderDTO = orderService.findOne(openId);
        if (orderDTO == null ){
            throw new SellException(ResultEnum.ORDER_NOT_EXIST);
        }

        //发起支付


    }


}
