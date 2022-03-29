package com.lianxi.demo01.resposity;

import com.lianxi.demo01.entity.PhoneSpecs;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class PhoneSpecsRepositoryTest {

    @Autowired
    PhoneSpecsRepository phoneSpecsRepository;

    @Test
    public void findAll(){
        List<PhoneSpecs> all = phoneSpecsRepository.findAll();
        for (PhoneSpecs phoneSpecs : all) {
            System.out.println(phoneSpecs);
        }
    }

    @Test
    public void findSpecsByPhoneId(){
        List<PhoneSpecs> specsByPhoneId = phoneSpecsRepository.findSpecsByPhoneId(1);
        for (PhoneSpecs phoneSpecs : specsByPhoneId) {
            System.out.println(phoneSpecs);
        }
    }
}