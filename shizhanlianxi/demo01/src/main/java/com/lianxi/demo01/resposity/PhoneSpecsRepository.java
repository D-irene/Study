package com.lianxi.demo01.resposity;

import com.lianxi.demo01.entity.PhoneSpecs;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PhoneSpecsRepository extends JpaRepository<PhoneSpecs,Integer> {

    public List<PhoneSpecs> findSpecsByPhoneId(Integer phoneId);
}
