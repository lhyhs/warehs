package com.hupun.demo.Interface;

import com.hupun.demo.entity.MenuAuthority;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * 菜单权限的对应操作的接口
 */
@FeignClient(value = "MICROSERVICECLOUD-CONFIG-SERVICE-CLIENT")
public interface MenuAuthorityService {
    @RequestMapping(value = "showMenuList",method = RequestMethod.GET)
    List<MenuAuthority> showMenuList();
}
