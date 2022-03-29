package com.lianxi.demo01.resposity;

import com.lianxi.demo01.entity.PhoneInfo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest

class PhoneInfoRepositoryTest {

    @Autowired
    PhoneInfoRepository phoneInfoRepository;
    @Test
    public void findAll(){
        List<PhoneInfo> all = phoneInfoRepository.findAll();
        for (PhoneInfo phoneInfo : all) {
            System.out.println(phoneInfo);
        }
    }
    @Test
    public void findByCategoryType(){
        List<PhoneInfo> byCategoryType = phoneInfoRepository.findByCategoryType(1);
        for (PhoneInfo phoneInfo : byCategoryType) {
            System.out.println(phoneInfo);
        }

    }
}