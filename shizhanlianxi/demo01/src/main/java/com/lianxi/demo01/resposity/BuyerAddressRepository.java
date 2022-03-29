package com.lianxi.demo01.resposity;

import com.lianxi.demo01.entity.BuyerAddress;
import org.springframework.data.jpa.repository.JpaRepository;


public interface BuyerAddressRepository extends JpaRepository<BuyerAddress,Integer> {

}
