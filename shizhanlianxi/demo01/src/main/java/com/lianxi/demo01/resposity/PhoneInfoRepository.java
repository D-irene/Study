package com.lianxi.demo01.resposity;

import com.lianxi.demo01.entity.PhoneInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PhoneInfoRepository extends JpaRepository<PhoneInfo,Integer> {

    public List<PhoneInfo> findByCategoryType(Integer categoryType);
}
