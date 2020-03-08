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
public class Outstock implements Serializable {
    //出库id
    private String outid;
    //出库单编码
    private String outcode;
    //仓库id
    private String sid;
    //仓库id名
    private String sname;
    //出库总数
    private Double outnumb;
    //出库总价
    private Double outpri;
    //出库备注
    private String outnote;
    //出库时间
    private String outdate;
    //收件人姓名
    private String outrecname;
    //收件人电话
    private String outrectel;
    //收件人地址
    private String outrecaddr;



}
