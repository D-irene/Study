package com.lianxi.demo01.service;

import com.lianxi.demo01.vo.DataVO;
import com.lianxi.demo01.vo.PhoneInfoVO;
import com.lianxi.demo01.vo.SpecsPackageVO;

import java.util.List;


public interface PhoneService {
    //1.首页数据
    public DataVO findDataVO();
    //2.根据类型查询手机
    public List<PhoneInfoVO> findPhoneInfoVOByCategoryType(Integer categoryId);
    //3.查询手机规格
    public SpecsPackageVO findSpecsPackageVOByPhoneId(Integer phoneId);
    //4.减库存
    public void subStock(Integer specsId,Integer quantity);


}
