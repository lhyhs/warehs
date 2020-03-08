package com.hupun.demo.entity.Vo;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
@NoArgsConstructor
@Data
@Accessors(chain=true)
public class FindStuffMenuVO implements Serializable {
    private String number;
    private String name;
}
