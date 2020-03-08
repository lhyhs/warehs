package com.hupun.demo.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
@NoArgsConstructor
@Data
@Accessors(chain=true)
public class WhsAuthority implements Serializable {
    private String id;//仓库权限id
    private String whid;//仓库id
    private String uid;//员工id
}
