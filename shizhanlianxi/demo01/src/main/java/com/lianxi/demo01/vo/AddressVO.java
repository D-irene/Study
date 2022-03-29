package com.lianxi.demo01.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class AddressVO {

    private String areaCode;
    @JsonProperty("id")
    private Integer addressId;
    @JsonProperty("name")
    private String buyerName;
    @JsonProperty("tel")
    private String buyerPhone;
    @JsonProperty("address")
    private String buyerAddress;

}
