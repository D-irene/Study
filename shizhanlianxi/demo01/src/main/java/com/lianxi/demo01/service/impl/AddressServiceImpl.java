package com.lianxi.demo01.service.impl;

import com.lianxi.demo01.entity.BuyerAddress;
import com.lianxi.demo01.form.AddressForm;
import com.lianxi.demo01.resposity.BuyerAddressRepository;
import com.lianxi.demo01.service.AddressService;
import com.lianxi.demo01.vo.AddressVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    BuyerAddressRepository buyerAddressRepository;
    //1.查询地址
    @Override
    public List<AddressVO> findList() {
        List<BuyerAddress> buyerAddressList = buyerAddressRepository.findAll();
        List<AddressVO> addressVOList = new ArrayList<>();
        for (BuyerAddress buyerAddress : buyerAddressList) {
            AddressVO addressVO = new AddressVO();
            BeanUtils.copyProperties(buyerAddress,addressVO);
            addressVOList.add(addressVO);
        }
        return addressVOList;
    }

    //2.新增或修改地址
    @Override
    public void saveOrUpdate(AddressForm addressForm) {
    BuyerAddress buyerAddress ;
    if(addressForm.getId() == null){
        buyerAddress = new BuyerAddress();
    }else{
        buyerAddress = buyerAddressRepository.getById(addressForm.getId());
    }
        buyerAddress.setBuyerName(addressForm.getName());
        buyerAddress.setBuyerPhone(addressForm.getTel());
        buyerAddress.setBuyerAddress(addressForm.getProvince()+addressForm.getCity()+addressForm.getCounty()+addressForm.getAddressDetail());
        buyerAddress.setAreaCode(addressForm.getAreaCode());

        buyerAddressRepository.save(buyerAddress);
    }
}
