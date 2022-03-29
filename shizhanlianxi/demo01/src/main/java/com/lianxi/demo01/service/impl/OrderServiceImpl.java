package com.lianxi.demo01.service.impl;

import com.lianxi.demo01.dto.OrderDTO;
import com.lianxi.demo01.entity.OrderMaster;
import com.lianxi.demo01.entity.PhoneInfo;
import com.lianxi.demo01.entity.PhoneSpecs;
import com.lianxi.demo01.enums.PayStatusEnum;
import com.lianxi.demo01.enums.ResultEnum;
import com.lianxi.demo01.exception.OrderException;
import com.lianxi.demo01.resposity.OrderMasterRepository;
import com.lianxi.demo01.resposity.PhoneInfoRepository;
import com.lianxi.demo01.resposity.PhoneSpecsRepository;
import com.lianxi.demo01.service.OrderService;
import com.lianxi.demo01.service.PhoneService;
import com.lianxi.demo01.utils.OrderIdUtil;
import com.lianxi.demo01.vo.OrderDetailVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;


@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Autowired
    PhoneSpecsRepository phoneSpecsRepository;
    @Autowired
    PhoneInfoRepository phoneInfoRepository;
    @Autowired
    OrderMasterRepository orderMasterRepository;

    @Autowired
    PhoneService phoneService;
    @Override
    public OrderDTO create(OrderDTO orderDTO) {
        OrderMaster orderMaster = new OrderMaster();
        BeanUtils.copyProperties(orderDTO,orderMaster);

        Optional<PhoneSpecs> phoneSpecs = phoneSpecsRepository.findById(orderDTO.getSpecsId());
        //利用optional.isPresent方法来判断是否为空值，从而判断是否有此规格
        if(!phoneSpecs.isPresent()){
            log.error("[创建订单]规格不存在",phoneSpecs);
            throw new OrderException(ResultEnum.SPECS_NOT_EXIST);
        }
        BeanUtils.copyProperties(phoneSpecs.get(),orderMaster);

        Optional<PhoneInfo> phoneInfo = phoneInfoRepository.findById(phoneSpecs.get().getPhoneId());
        if (!phoneInfo.isPresent()){
            log.error("[创建订单]手机不存在",phoneInfo);
            throw new OrderException(ResultEnum.PHONE_NOT_EXIST);
        }
        BeanUtils.copyProperties(phoneInfo.get(),orderMaster);

        //orderAmount(总价)
        BigDecimal orderAmount = new BigDecimal(0);
        //将SpecsPrice价格由分换算为元
        orderAmount = phoneSpecs.get().getSpecsPrice().divide(new BigDecimal(100))
                .multiply(new BigDecimal(orderDTO.getPhoneQuantity()))
                .add(new BigDecimal(10));
        orderMaster.setOrderAmount(orderAmount);
        //pay status-->创建订单==未支付订单
        orderMaster.setPayStatus(PayStatusEnum.UNPAYD.getCode());

        //orderId---》生成随机的一串数字
        orderMaster.setOrderId(OrderIdUtil.createOrderId());
        orderDTO.setOrderId(orderMaster.getOrderId());
        orderMasterRepository.save(orderMaster);

        //减库存
        phoneService.subStock(orderDTO.getSpecsId() ,orderDTO.getPhoneQuantity());
        return orderDTO;
    }

    @Override
    public OrderDetailVO detail(String orderId) {
        OrderDetailVO orderDetailVO = new OrderDetailVO();
        Optional<OrderMaster> orderMaster = orderMasterRepository.findById(orderId);
        if (!orderMaster.isPresent()){
            log.error("[查询订单]订单不存在",orderMaster);
            throw new OrderException(ResultEnum.ORDER_NOT_EXIST);
        }
        BeanUtils.copyProperties(orderMaster.get(),orderDetailVO);
        orderDetailVO.setSpecsPrice(orderMaster.get().getSpecsPrice().divide(new BigDecimal(100))+" ");
        orderDetailVO.setOrderAmount(orderMaster.get().getOrderAmount().intValue());

        return orderDetailVO;
    }

    @Override
    public String pay(String orderId) {
        Optional<OrderMaster> orderMaster = orderMasterRepository.findById(orderId);
        if(!orderMaster.isPresent()){
            log.error("[支付订单]订单不存在",orderMaster);
            throw new OrderException(ResultEnum.ORDER_NOT_EXIST);
        }
        if(orderMaster.get().getPayStatus().equals(PayStatusEnum.UNPAYD.getCode())){
            orderMaster.get().setPayStatus(PayStatusEnum.PAYD.getCode());
            orderMasterRepository.save(orderMaster.get());
        }else{
        log.error("[支付订单]订单已支付",orderMaster);
        }

        return orderId;
    }
}
