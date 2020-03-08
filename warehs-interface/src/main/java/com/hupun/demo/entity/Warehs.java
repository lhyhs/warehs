package com.hupun.demo.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
@NoArgsConstructor
@Data
@Accessors(chain=true)
public class Warehs implements Serializable{
    private int sno;
    private String sname;
    private String stel;
    private String scont;
    private String saddr;
    public Warehs(String sname,String stel,String scont,String saddr){
        super();
        this.sname = sname;
        this.stel = stel;
        this.scont = scont;
        this.saddr = saddr;
    }


}
