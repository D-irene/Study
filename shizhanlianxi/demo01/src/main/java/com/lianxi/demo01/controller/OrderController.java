package com.lianxi.demo01.controller;

import com.lianxi.demo01.dto.OrderDTO;
import com.lianxi.demo01.exception.PhoneException;
import com.lianxi.demo01.form.OrderForm;
import com.lianxi.demo01.service.OrderService;
import com.lianxi.demo01.utils.ResultVOUtil;
import com.lianxi.demo01.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/order")
@Slf4j
public class OrderController {
    @Autowired
    OrderService orderService;

    @PostMapping("/create")
    public ResultVO create(@Valid @RequestBody OrderForm orderForm, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            log.error("[创建订单]参数错误,orderForm={}",orderForm);
            throw new PhoneException(bindingResult.getFieldError().getDefaultMessage());
        }
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setBuyerName(orderForm.getName());
        orderDTO.setBuyerPhone(orderForm.getTel());
        orderDTO.setBuyerAddress(orderForm.getAddress());
        orderDTO.setSpecsId(orderForm.getSpecsId());
        orderDTO.setPhoneQuantity(orderForm.getQuantity());
        OrderDTO orderDTO1 = orderService.create(orderDTO);
        Map<String,String> map = new HashMap<>();
        map.put("orderId",orderDTO1.getOrderId());
        return ResultVOUtil.success(map);

    }
    @GetMapping("/detail/{orderId}")
    public ResultVO detail(@PathVariable("orderId") String orderId){
        //orderService.detail(orderId);
        return ResultVOUtil.success(orderService.detail(orderId));

    }
    @PutMapping("/pay/{orderId}")
    public ResultVO pay(@PathVariable("orderId") String orderId){
        Map<String, String> map = new HashMap<>();
        map.put("orderId",orderService.pay(orderId));
        return ResultVOUtil.success(map);
    }
}
