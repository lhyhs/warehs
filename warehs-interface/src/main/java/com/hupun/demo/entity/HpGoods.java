package com.hupun.demo.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

@NoArgsConstructor
@Data
@Accessors(chain=true)
public class HpGoods implements Serializable {
    //商品id
    private String gid;
    //商品编码
    private String gno ;
    //商品条码
    private String gcode;
    //商品名
    private String gname;
    //商品售价
    private Double gprice;
    //商品批发价
    private Double gtradeprice ;
    //参考进价
    private Double greferprice;
    //商品重量
    private Double gweight;
    //商品体积
    private Double gvolume;
    //商品状态
    private Integer statu;

}
