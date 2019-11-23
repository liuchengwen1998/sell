package com.lcw.enums;

import lombok.Getter;

@Getter
public enum OrderStatusEnum {
    NEW(0,"新订单"),
    FINISHED(1,"完结"),
    CANCLE(2,"已取消"),
    ;

    private Integer code;
    private String msg;


    OrderStatusEnum(Integer code,String msg) {
        this.msg = msg;
        this.code = code;
    }
}
