package com.lcw.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.lcw.dataobject.OrderDetail;
import com.lcw.untils.serializer.Data2LongSerializer;
import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Transient;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
//@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
//@JsonInclude(JsonInclude.Include.NON_NULL)    //去除返回的是null的属性
public class OrderDTO {
    //订单id
    @Id
    private String orderId;
    //买家名字
    private String buyerName;
    //买家手机号
    private String buyerPhone;
    //买家地址
    private String buyerAddress;
    //买家微信id
    private String buyerOpenid;
    //订单总金额
    private BigDecimal orderAmount;
    //订单状态,默认0新下单
    private Integer orderStatus;
    //支付状态，默认0未支付
    private Integer payStatus;
    //创建时间
    @JsonSerialize(using = Data2LongSerializer.class)
    private Date createTime;
    //更新时间
    @JsonSerialize(using = Data2LongSerializer.class)
    private Date updateTime;

    @Transient//数据库对应时忽略
    private List<OrderDetail> orderDetailList;
}
