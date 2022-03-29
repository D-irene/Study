package com.lianxi.demo01.controller;

import com.lianxi.demo01.service.PhoneService;
import com.lianxi.demo01.utils.ResultVOUtil;
import com.lianxi.demo01.vo.PhoneInfoVO;
import com.lianxi.demo01.vo.ResultVO;
import jdk.nashorn.internal.objects.annotations.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/phone")
public class PhoneController {
    @Autowired
    PhoneService phoneService;

    @GetMapping("/index")
    public ResultVO index(){
        return ResultVOUtil.success(phoneService.findDataVO());
    }
    @GetMapping("/findByCategoryType/{categoryType}")
    public ResultVO findByCategoryType(@PathVariable("categoryType") Integer categoryType){
        List<PhoneInfoVO> phoneInfoVOByCategoryType = phoneService.findPhoneInfoVOByCategoryType(categoryType);
        if (phoneInfoVOByCategoryType.size() == 0){
            return ResultVOUtil.error(phoneInfoVOByCategoryType);
        }else{
        return ResultVOUtil.success(phoneInfoVOByCategoryType);}
    }
    @GetMapping("/findSpecsByPhoneId/{phoneId}")
    public ResultVO findSpecsByPhoneId(@PathVariable("phoneId") Integer phoneId){
        return ResultVOUtil.success(phoneService.findSpecsPackageVOByPhoneId(phoneId));
    }
}
