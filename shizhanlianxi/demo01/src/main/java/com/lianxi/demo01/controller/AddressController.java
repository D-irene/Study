package com.lianxi.demo01.controller;

import com.lianxi.demo01.exception.PhoneException;
import com.lianxi.demo01.form.AddressForm;
import com.lianxi.demo01.service.AddressService;
import com.lianxi.demo01.utils.ResultVOUtil;
import com.lianxi.demo01.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/address")
@Slf4j
public class AddressController {
    @Autowired
    AddressService addressService;

    @GetMapping("/list")
    public ResultVO list(){
        return ResultVOUtil.success(addressService.findList());
    }

    @PostMapping("/create")
    public ResultVO create(@Valid @RequestBody AddressForm addressForm, BindingResult bindingResult){
        System.out.println(bindingResult.hasErrors());
        if (bindingResult.hasErrors()) {
            log.error("[添加地址]参数错误",addressForm);
            throw new PhoneException(bindingResult.getFieldError().getDefaultMessage());
        }
        addressService.saveOrUpdate(addressForm);
    return ResultVOUtil.success(null);
    }

    @PutMapping("/update")
    public ResultVO update(@Valid @RequestBody AddressForm addressForm,BindingResult bindingResult){
        System.out.println(bindingResult.hasErrors());
        if (bindingResult.hasErrors()){
            log.error("[修改地址参数错误]",addressForm);
            throw new PhoneException(bindingResult.getFieldError().getDefaultMessage());
        }
        addressService.saveOrUpdate(addressForm);
        return ResultVOUtil.success(null);

    }

}
