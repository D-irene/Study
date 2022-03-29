package com.lianxi.demo01.service;

import com.lianxi.demo01.dto.OrderDTO;
import com.lianxi.demo01.vo.OrderDetailVO;

public interface OrderService {
    //1.创建订单
    public OrderDTO create(OrderDTO orderDTO);
    //2.订单详情
    public OrderDetailVO detail(String orderId);
    //3.支付订单
    public String pay(String orderId);
}
