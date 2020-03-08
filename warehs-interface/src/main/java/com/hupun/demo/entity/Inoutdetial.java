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
public class Inoutdetial implements Serializable {
    //出入库明细id
    private String ioid;
    /**
     * 出入库明细类型，0表示出库，1表示入库
     */
    private Integer iotype;
    //出入库编码
    private String iocode;
    //商品id
    private String gid;
    //商品名称
    private String gname;
    //商品编码
    private String gno;
    //明细数量
    private Double ionumb;
    //明细价格
    private Double iopri;
    //明细备注
    private String iodnote;

}
