package com.lianxi.demo01.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
public class PhoneCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer categoryId;
    @Column
    private String categoryName;
    @Column
    private Integer categoryType;
    @Column
    private Date createTime;
    @Column
    private Date updateTime;
}
