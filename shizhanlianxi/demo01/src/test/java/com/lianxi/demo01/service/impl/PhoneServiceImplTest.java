package com.lianxi.demo01.service.impl;

import com.lianxi.demo01.resposity.PhoneCategoryRepository;
import com.lianxi.demo01.service.PhoneService;
import com.lianxi.demo01.vo.DataVO;
import com.lianxi.demo01.vo.PhoneInfoVO;
import com.lianxi.demo01.vo.SpecsPackageVO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class PhoneServiceImplTest {

    @Autowired
    PhoneService phoneService;

    @Test
    void findDataVO() {
        DataVO dataVO = phoneService.findDataVO();
        int id = 0;

    }
    @Test
    void findPhoneInfoVOByCategoryType(){

        List<PhoneInfoVO> phoneInfoVOByCategoryType = phoneService.findPhoneInfoVOByCategoryType(10);
        int id = 0;

    }

    @Test
    void findSpecsByPhoneId(){
        SpecsPackageVO specsPackageVO = phoneService.findSpecsPackageVOByPhoneId(1);
        int id = 0;
    }
    @Test
    void subStock(){
        phoneService.subStock(1,5);
    }
}