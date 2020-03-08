package com.hupun.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain=true)
public class Storehouse implements Serializable {
    //仓储id
    private String shid;
    //仓库id
    private String sid;
    //商品id
    private String gid;
    //仓储总数
    private Double shnumb;
    //仓储总价
    private Double shpri;
    //仓储均价
    private Double shavgpri;
    private String sname;
    private String gname;
    private String gno;
    private String gcode;
    private Double greferprice;


}
