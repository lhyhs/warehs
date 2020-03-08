package com.hupun.demo.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
@NoArgsConstructor
@Data
@Accessors(chain=true)
public class MenuAuthority implements Serializable {
    private String id;//菜单权限id
    private Integer number;//权限号
    //private String name;//权限名
    private String uid;//员工id
}
