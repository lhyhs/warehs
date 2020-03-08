package com.hupun.demo.entity.Vo;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@NoArgsConstructor
@Data
@Accessors(chain=true)
public class SearchWhsVO {
    private String code;
    private String name;
    private Integer statu;
    private String eid;
}
