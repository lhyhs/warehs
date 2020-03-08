package com.hupun.demo.dao;

import com.hupun.demo.entity.MenuAuthority;
import com.hupun.demo.entity.Vo.FindStuffMenuVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 菜单权限dao接口
 */
@Mapper
public interface MenuAuthorityDao {
    /**
     * 根据用户id找到对应的菜单权限
     * @param eid
     * @return
     */
    List<MenuAuthority> findMenuByStuffId(String eid);

    /**
     * 查找所有的的权限
     * @return
     */
    List<MenuAuthority> findMenuList();

    /**
     * 给对应的员工添加菜单权限
     * @param ma
     * @return
     */
    Integer addMenuAuthority(MenuAuthority ma);

    /**
     * 根据用户id找到对应的菜单权限
     * @param eid
     * @return
     */
    List<FindStuffMenuVO> getChoosedWhsList(String eid);

    /**
     * 删除菜单权限
     * @param eid
     * @return
     */
    Integer deleteMenu(String eid);
}
