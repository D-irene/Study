package com.lianxi.demo01.resposity;

import com.lianxi.demo01.entity.PhoneCategory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class PhoneCategoryRepositoryTest {
    @Autowired
    PhoneCategoryRepository phoneCategoryRepository;

    @Test
    public void findAll(){
        List<PhoneCategory> all = phoneCategoryRepository.findAll();

        for (PhoneCategory phoneCategory : all) {
            System.out.println(phoneCategory);
        }
    }
    @Test
    public void findByCategoryType(){
        PhoneCategory byCategoryType = phoneCategoryRepository.findByCategoryType(1);
        System.out.println(byCategoryType);
    }

}