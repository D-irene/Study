package com.lianxi.demo01.resposity;

import com.lianxi.demo01.entity.BuyerAddress;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;
import java.util.Optional;


@SpringBootTest

class BuyerAddressRepositoryTest {

    @Autowired
    BuyerAddressRepository buyerAddressRepository;

    @Test
    public void findAll(){
        List<BuyerAddress> all = buyerAddressRepository.findAll();
        for (BuyerAddress buyerAddress : all) {
            System.out.println(buyerAddress);
        }
    }

    @Test
    public void save(){
        BuyerAddress buyerAddress = new BuyerAddress();

        buyerAddress.setAreaCode("217000");
        buyerAddress.setBuyerAddress("山东省泰安市");
        buyerAddress.setBuyerName("qq");
        buyerAddress.setBuyerPhone("12345678901");
//        buyerAddress.setCreateTime(new Date());
//        buyerAddress.setUpdateTime(new Date());
        BuyerAddress save = buyerAddressRepository.save(buyerAddress);
        System.out.println(save);
    }
    @Test
    public void update(){
        //调用get取出是为了防止空值异常
        BuyerAddress buyerAddress = buyerAddressRepository.findById(3).get();
        buyerAddress.setBuyerName("小丽");
        buyerAddressRepository.save(buyerAddress);
        System.out.println(buyerAddress);

    }
}