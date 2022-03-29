package com.lianxi.demo01.form;


import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class AddressForm {
    private Integer id;
    @NotEmpty(message="姓名不能为空")
    private String name;
    @NotEmpty(message = "电话不能为空")
    private String tel;

    private String country;
    @NotEmpty(message = "省不能为空")
    private String province;
    @NotEmpty(message = "市不能为空")
    private String city;
    @NotEmpty(message = "区不能为空")
    private String county;
    @NotEmpty(message = "编码不能为空")
    private String areaCode;

    private String postalCode;
    @NotEmpty(message = "地址详情不能为空")
    private String addressDetail;

    private Boolean isDefault;

}
