package com.hupun.demo.dao;

import com.hupun.demo.entity.HpWarehs;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TestDao {
    List<HpWarehs> showWarehsList();
}
