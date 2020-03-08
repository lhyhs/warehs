package com.hupun.demo.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

@NoArgsConstructor
@Data
@Accessors(chain=true)
public class HpStuff implements Serializable {
    //员工id
    private String eid;
    //员工名字
    private String name;
    //电话号码
    private String phone;
    //密码
    private String password;
    //备注
    private String remarks;
    //管理员权限
    private Integer emana;
    //管理仓库的数量
    private Integer whcount;
    //员工状态  0表示被禁用，1表示已启用
    private Integer statu;
    //管理的仓库id字符串
    private String chooseData;
    //管理的菜单id字符串
    private String menuchooseData;
    //管理菜单权限的集合
    private List<MenuAuthority> menuList;

}
