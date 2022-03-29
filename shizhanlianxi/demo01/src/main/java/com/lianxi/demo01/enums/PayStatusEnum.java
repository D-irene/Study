package com.lianxi.demo01.enums;

import lombok.Getter;

@Getter
public enum PayStatusEnum {
    PAYD(1,"已支付"),
    UNPAYD(0,"未支付");

    private Integer code;
    private String msg;

     PayStatusEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
