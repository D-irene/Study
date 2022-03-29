package com.lianxi.demo01.resposity;

import com.lianxi.demo01.entity.OrderMaster;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;



@SpringBootTest
class OrderMasterRepositoryTest {
    @Autowired
    OrderMasterRepository orderMasterRepository;

    @Test
    public void save(){
        OrderMaster orderMaster = new OrderMaster();
        orderMaster.setOrderId("4");
        orderMaster.setBuyerAddress("安徽省蚌埠市");
        orderMaster.setBuyerName("张三");
        orderMaster.setBuyerPhone("123456718190");
        orderMaster.setPayStatus(1);
        orderMaster.setOrderAmount(new BigDecimal(1));
        orderMaster.setPayStatus(1);
//        orderMaster.setCreateTime(new Date());
//        orderMaster.setUpdateTime(new Date());
        orderMasterRepository.save(orderMaster);
        System.out.println(orderMaster);
    }
    @Test
    public void findAll(){
        List<OrderMaster> all = orderMasterRepository.findAll();
        for (OrderMaster orderMaster : all) {
            System.out.println(orderMaster);
        }
    }

    @Test
    public void findById(){
        OrderMaster byId = orderMasterRepository.findById("1").get();
        System.out.println(byId);
    }

    @Test
    public void payStatus(){
        OrderMaster orderMaster = orderMasterRepository.findById("3").get();
        if (orderMaster.getPayStatus()==0){
        orderMaster.setPayStatus(1);
        }
        OrderMaster save = orderMasterRepository.save(orderMaster);
        System.out.println("支付成功："+save);
    }
}