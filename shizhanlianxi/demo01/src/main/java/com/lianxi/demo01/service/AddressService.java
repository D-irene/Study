package com.lianxi.demo01.service;

import com.lianxi.demo01.form.AddressForm;
import com.lianxi.demo01.vo.AddressVO;

import java.util.List;

public interface AddressService {
    //1.查询
    public List<AddressVO> findList();
    //2.新增或者修改
    public void saveOrUpdate(AddressForm addressForm);
}
