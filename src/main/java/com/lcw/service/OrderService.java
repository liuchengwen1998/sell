package com.lcw.service;

import com.lcw.dataobject.OrderMaster;
import com.lcw.dto.OrderDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface OrderService {

    //创建订单
    OrderDTO create(OrderDTO orderDTO);
    //查询单个订单
    OrderDTO findOne(String orderId);
    //查询订单列表
    Page<OrderDTO> findList(String buerOpneid, Pageable pageable);
    //取消订单
    OrderDTO cancle(OrderDTO orderDTO);
    //完结订单
    OrderDTO finish(OrderDTO orderDTO);
    //支付订单
    OrderDTO paid(OrderDTO orderDTO);


}
