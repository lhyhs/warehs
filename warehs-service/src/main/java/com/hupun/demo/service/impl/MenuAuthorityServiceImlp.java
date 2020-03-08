package com.hupun.demo.service.impl;

import com.hupun.demo.Interface.MenuAuthorityService;
import com.hupun.demo.dao.MenuAuthorityDao;
import com.hupun.demo.entity.MenuAuthority;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 菜单权限实现类
 */
@RestController
public class MenuAuthorityServiceImlp implements MenuAuthorityService {
    @Autowired
    private MenuAuthorityDao dao;
    //查询所有的菜单权限
    @RequestMapping(value = "showMenuList",method = RequestMethod.GET)
    public List<MenuAuthority> showMenuList() {
        return dao.findMenuList();
    }
}
