package com.hupun.demo.entity.Vo;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

@NoArgsConstructor
@Data
@Accessors(chain=true)
public class FindStuffWhsVO implements Serializable {
    private String id;
    private String name;

}
