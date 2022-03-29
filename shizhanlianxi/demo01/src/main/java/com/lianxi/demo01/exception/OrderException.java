package com.lianxi.demo01.exception;

import com.lianxi.demo01.enums.ResultEnum;

public class OrderException extends RuntimeException {
    public OrderException(ResultEnum resultEnum) {
        super(resultEnum.getMsg());
    }
}
