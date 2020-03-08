package com.hupun.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

@NoArgsConstructor
@Data
@AllArgsConstructor
@Accessors(chain=true)
public class JsonResult<T> implements Serializable {
    private Integer status;//状态码
    private T data;//返回的数据
    private String message;//状态描述

    public JsonResult(Integer status, T data) {
        this.status = status;
        this.data = data;
    }
    public JsonResult(Integer status, String message) {
        this.status = status;
        this.message = message;
    }

    public JsonResult(Integer status) {
        super();
        this.status = status;
    }
    public JsonResult(Throwable e) {
        super();
        setMessage(e.getMessage());
    }
}
