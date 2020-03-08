package com.hupun.demo.entity.Vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
@AllArgsConstructor
@NoArgsConstructor
@Data
@Accessors(chain=true)
public class SearchStuffVO implements Serializable {
    private String phone;
    private String name;
}
