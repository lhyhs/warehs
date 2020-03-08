package com.hupun.demo.dao;


import com.hupun.demo.entity.HpWarehs;
import com.hupun.demo.entity.Vo.SearchWhsVO;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

/**
 * 仓库dao接口
 */
@Mapper
public interface WarehsDao
{
    //查看员工管理的仓库
    List<HpWarehs> showWarehsList(String eid);
    //添加仓库
    Integer addWarehs(HpWarehs warehs);
    //查找所有的仓库
    List<HpWarehs> findAll();
    //查询对应条件的仓库
    List<HpWarehs> findWhsSearchList(SearchWhsVO warehs);
    //修改仓库状态
    Integer updateWhsStatu(HpWarehs setStatu);
    //修改仓库信息
    Integer updateWhsByid(HpWarehs setAddress);
    //判断仓库编码是否已经存在
    HpWarehs getByCode(String code);
}
