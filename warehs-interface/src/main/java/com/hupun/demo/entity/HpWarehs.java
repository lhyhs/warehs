package com.hupun.demo.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

@NoArgsConstructor
@Data
@Accessors(chain=true)
public class HpWarehs implements Serializable {
    //仓库id
    private String id;
    //仓库编码
    private String code;
//    仓库名
    private String name;
    ////仓库联系人
    private String contacts;
    //仓库电话
    private String phone;
    //仓库状态
    private Integer statu;
    //仓库地址
    private String address;
}
