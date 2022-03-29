package com.lianxi.demo01.service.impl;

import com.lianxi.demo01.dto.OrderDTO;
import com.lianxi.demo01.service.OrderService;
import com.lianxi.demo01.vo.OrderDetailVO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class OrderServiceImplTest {

    @Autowired
    OrderService orderService;
    @Test
    void create() {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setBuyerAddress("广东省深圳市罗湖区科技路123号456室");
        orderDTO.setBuyerName("赵六");
        orderDTO.setBuyerPhone("13678787878");
        orderDTO.setSpecsId(3);
        orderDTO.setPhoneQuantity(1);
        OrderDTO dto1 = orderService.create(orderDTO);
        System.out.println(dto1);
    }

    @Test
    void detail() {
        OrderDetailVO detail = orderService.detail("1648273585716645789");

        System.out.println(detail);
    }
    @Test
    void pay(){
        String orderId = orderService.pay("1648273585716645788");
        System.out.println(orderId);
        int id = 0;
    }
}