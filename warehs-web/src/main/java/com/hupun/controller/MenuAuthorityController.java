package com.hupun.controller;

import com.hupun.demo.Interface.MenuAuthorityService;
import com.hupun.demo.entity.MenuAuthority;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 处理菜单权限请求的web层
 */
@RestController
@RequestMapping(value = "menu")
public class MenuAuthorityController {

    @Autowired
    private MenuAuthorityService service;

    @RequestMapping(value = "showMenuList",method = RequestMethod.GET)
    public List<MenuAuthority> showList(){
        return service.showMenuList();
    }


}
