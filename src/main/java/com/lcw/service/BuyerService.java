package com.lcw.service;

import com.lcw.converter.OrderForm2OrderDTOConverter;
import com.lcw.dto.OrderDTO;

/**
 * 买家
 */
public interface BuyerService {
    //查询一个订单
    OrderDTO findOrderOne(String openId, String orderId);
    //取消一个订单
    OrderDTO cancleOrder(String openId, String orderId);
}
