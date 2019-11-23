package com.lcw.dataobject;


import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Proxy;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

@Entity
@Proxy(lazy = false)
@DynamicUpdate //动态更新
@Data
public class ProductInfo {

    @Id
    private String productId;
    //名字
    private String productName;
    //单价
    private BigDecimal productPrice;
    //库存
    private Integer productStock;
    //商品描述
    private String productDescription;
    //商品的小图
    private String productIcon;
    //状态，0正常1下架
    private Integer productStatus;
    //类目编号
    private Integer categoryType;
}
