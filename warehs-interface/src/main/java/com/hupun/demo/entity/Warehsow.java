package com.hupun.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class Warehsow implements Serializable {
    //流水id
    private String wowid;
    /**
     * 单据类型，0表示出库，1表示入库
     */
    private Integer iotype;
    //单据编号
    private String iocode;
    //商品id
    private String gid;
    //仓库id
    private String sid;
    //出入库数量
    private Double ionumb;
    //出入量价格
    private Double iopri;
    //当前仓储总数
    private Double shnumb;
    //当前仓储总价
    private Double shpri;
    private String iodate;

}
