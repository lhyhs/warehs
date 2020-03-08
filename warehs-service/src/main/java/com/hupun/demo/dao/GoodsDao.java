package com.hupun.demo.dao;

import com.hupun.demo.entity.HpGoods;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 商品dao接口
 */
@Mapper
public interface GoodsDao {
    //插入商品
    Integer addGoods(HpGoods goods);
    //查看全部商品
    List<HpGoods> findGoodsAll();
    //根据对应条件查询商品
    List<HpGoods> findBySearchList(HpGoods setStatu);
    //修改商品状态
    Integer updateGoodsStatu(HpGoods setStatu);
    //修改商品
    Integer changeGoods(HpGoods goods);
    //判断商品编码是否已经存在
    HpGoods getByGno(String gno);
}
