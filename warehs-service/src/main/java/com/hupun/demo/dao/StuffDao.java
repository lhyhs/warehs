package com.hupun.demo.dao;

import com.hupun.demo.entity.HpStuff;
import com.hupun.demo.entity.Vo.FindStuffWhsVO;
import com.hupun.demo.entity.Vo.SearchStuffVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 员工dao接口
 */
@Mapper
public interface StuffDao {
    //添加员工
    //boolean addStuff(HpStuff stuff);
    //通过账号查询员工
    HpStuff findByPhone(String phone);
     //查询所有员工
    List<HpStuff> findStuffList();
    //根据条件查询员工列表
    List<HpStuff> findBySearchList(SearchStuffVO searchStuff);
    //根据员工状态查询员工列表
    List<HpStuff> findStuffByStatu(Integer statu);
    //修改员工状态
    Integer changeStuffStatu(HpStuff stuff);
    //新增员工
    Integer insertStuff(HpStuff stuff);
    //查询员工对应的仓库
    List<FindStuffWhsVO> getChoosedWhsList(String eid);
    //修改员工信息
    Integer UpdateStuff(HpStuff stuff);
    //添加仓库是whcount加一
    void updateWhcount();
    //通过权限id查找
    List<HpStuff> findStuffByEmana(Integer emana);
}
