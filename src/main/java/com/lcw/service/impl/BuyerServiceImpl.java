package com.lcw.service.impl;

import com.lcw.dto.OrderDTO;
import com.lcw.enums.ResultEnum;
import com.lcw.exception.SellException;
import com.lcw.service.BuyerService;
import com.lcw.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class BuyerServiceImpl implements BuyerService {

    @Autowired
    private OrderService orderService;

    @Override
    public OrderDTO findOrderOne(String openId, String orderId) {
        return checkOrderOwner(openId,orderId);
    }

    @Override
    public OrderDTO cancleOrder(String openId, String orderId) {
        OrderDTO orderDTO = checkOrderOwner(openId,orderId);
        if (orderDTO ==null){
            log.error("【取消订单】查不到该订单.orderId={}",openId);
            throw new SellException(ResultEnum.ORDER_NOT_EXIST);
        }
        return orderService.cancle(orderDTO);
    }

    private OrderDTO checkOrderOwner(String openId, String orderId) {
        OrderDTO orderDTO = orderService.findOne(orderId);
        if (orderDTO == null){
            return null;
        }
        //判断用户的id和订单的id是否一致
        if (!orderDTO.getBuyerOpenid().equalsIgnoreCase(openId)){
            log.error("【查询订单】订单的openId不一致. openId={},orderDTO={}",openId,orderDTO);
            throw new SellException(ResultEnum.order_owner_error);
        }
        return orderDTO;
    }

}
