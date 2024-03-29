package com.lcw.VO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/**
 * 商品（包含类目）
 */
@Data
public class ProductVO {

    @JsonProperty("name")
    private String categoryname;

    @JsonProperty("type")
    private Integer categorytype;

    @JsonProperty("foods")
    private List<ProductInfoVO> productInfoVOList;
}
