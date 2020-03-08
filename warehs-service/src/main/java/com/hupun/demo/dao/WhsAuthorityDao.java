package com.hupun.demo.dao;

import com.hupun.demo.entity.WhsAuthority;
import org.apache.ibatis.annotations.Mapper;

/**
 * 仓库权限dao接口
 */
@Mapper
public interface WhsAuthorityDao {
    //添加员工对应的参数
    Integer addWhsAuthority(WhsAuthority wa);
    //删除员工对应的仓库权限
    Integer deleteWhs(String eid);
    //添加仓库时给管理员分配仓库权限
    Integer addWhsAuthorityByEmana(WhsAuthority whsAuthority);
}
