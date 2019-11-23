package com.lcw.controller;


import com.lcw.VO.ResultVO;
import com.lcw.converter.OrderForm2OrderDTOConverter;
import com.lcw.dto.OrderDTO;
import com.lcw.enums.ResultEnum;
import com.lcw.exception.SellException;
import com.lcw.form.OrderForm;
import com.lcw.service.BuyerService;
import com.lcw.service.OrderService;
import com.lcw.untils.ResultVOUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/buyer/order")
@Slf4j
public class BuyerOrderController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private BuyerService buyerService;
    //创建订单
    @PostMapping("/create")
    public ResultVO<Map<String,String>> create(@Valid OrderForm orderForm, BindingResult bindingResult){

        if (bindingResult.hasErrors()){
            log.error("【创建订单】参数不正确，orderForm={}",orderForm);
            throw new SellException(ResultEnum.PARAM_ERROR.getCode(),
                    bindingResult.getFieldError().getDefaultMessage());
        }
        OrderDTO orderDTO = OrderForm2OrderDTOConverter.convert(orderForm);

        if (CollectionUtils.isEmpty(orderDTO.getOrderDetailList())){
            log.error("【创建订单】购物车不能为空");
            throw new SellException(ResultEnum.cart_empty);
        }
        OrderDTO createResult = orderService.create(orderDTO);
        Map<String,String> map = new HashMap<>();
        map.put("orderId",createResult.getOrderId());

        return ResultVOUtil.success(map);

    }

    //订单列表
    @GetMapping("/list")
    public ResultVO<List<OrderDTO>> list(@RequestParam("openId") String openId,
                                         @RequestParam(value = "page",defaultValue = "0") Integer page,
                                         @RequestParam(value = "size",defaultValue = "10") Integer size){
        if (StringUtils.isEmpty(openId)){
            log.error("【查询订单列表】openid为空");
            throw new SellException(ResultEnum.PARAM_ERROR);
        }

        PageRequest request = new PageRequest(page,size);
        Page<OrderDTO> orderDTOPage = orderService.findList(openId, request);


        return ResultVOUtil.success(orderDTOPage.getContent());
//        return ResultVOUtil.success();

    }
    //订单详情
    @GetMapping("/detail")
    public ResultVO<OrderDTO> detail (@RequestParam("openId") String openId,
                                      @RequestParam("orderId")String orderId){
        OrderDTO orderDTO = buyerService.findOrderOne(openId,orderId);
        return ResultVOUtil.success(orderDTO);

    }
    //取消订单
    @PostMapping("/cancle")
    public ResultVO<OrderDTO> cancle (@RequestParam("openId") String openId,
                                     @RequestParam("orderId")String orderId){
        buyerService.cancleOrder(openId,orderId);
        return ResultVOUtil.success();
    }
}
