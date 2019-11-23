package com.lcw.dataobject;


import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Proxy;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
@Proxy(lazy = false)
@DynamicUpdate //动态更新
@Data

public class ProductCategory {

    /**
     * 类目id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer categoryId;

    /**
     * 类目编号
     */
    private Integer categoryType;

    /**
     * 类目名字
     */
    private String categoryName;

    private Date updateTime;

    private Date createTime;

    public ProductCategory(Integer categoryType, String categoryName) {
        this.categoryType = categoryType;
        this.categoryName = categoryName;
    }
    public ProductCategory(){}
}
