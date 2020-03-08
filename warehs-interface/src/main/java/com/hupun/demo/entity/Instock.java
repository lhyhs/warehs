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
public class Instock implements Serializable {
    //入库id
    private String inid;
    //入库单编码
    private String incode;
    //仓库id
    private String sid;
    //入库总数
    private String sname;
    private Double innumb;
    //入库总价
    private Double inpri;
    //入库备注
    private String innote;
    //入库时间
    private String indate;
    //经手人姓名
    private String inrecname;
    //快递单号
    private String inconumb;

}
