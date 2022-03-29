package com.lianxi.demo01.service.impl;

import com.lianxi.demo01.form.AddressForm;
import com.lianxi.demo01.service.AddressService;
import com.lianxi.demo01.vo.AddressVO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class AddressServiceImplTest {

    @Autowired
    AddressService addressService;
    @Test
    void findList() {
        List<AddressVO> list = addressService.findList();
        int id = 0;
    }
    @Test
    void saveOrCreate(){
        AddressForm addressForm = new AddressForm();
        addressForm.setId(4);
        addressForm.setName("李四");
        addressForm.setTel("15767890987");
        addressForm.setProvince("山东省");
        addressForm.setCity("青岛市");
        addressForm.setCounty("城阳区");
        addressForm.setAreaCode("123000");
        addressForm.setAddressDetail("高新区洪融路1230号");
        addressService.saveOrUpdate(addressForm);
    }
}