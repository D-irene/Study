package com.lianxi.demo01.exception;

import com.lianxi.demo01.enums.ResultEnum;

public class PhoneException extends RuntimeException {
    public PhoneException(ResultEnum resultEnum){
        super(resultEnum.getMsg());
    }
    public PhoneException(String error){
        super(error);
    }
}
