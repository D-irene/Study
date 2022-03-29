package com.lianxi.demo01.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;


@Data
public class OrderDTO {
    private String orderId;
    @JsonProperty("name")
    private String buyerName;
    @JsonProperty("tel")
    private String buyerPhone;
    @JsonProperty("address")
    private String buyerAddress;
    private Integer specsId;
    @JsonProperty("quantity")
    private Integer phoneQuantity;
}
